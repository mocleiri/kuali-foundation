package org.kuali.core.db.torque.service;

import org.apache.commons.lang.StringUtils;
import org.apache.torque.engine.database.model.Column;
import org.apache.torque.engine.database.model.SchemaType;
import org.apache.torque.engine.database.model.Table;
import org.kuali.common.util.CollectionUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OracleImpexReader extends AbstractImpexReader {

    private static final String INSERT_PREFIX = "INSERT ALL\n";
    private static final String INDENT = "  ";
    private static final String INTO_PREFIX = "INTO ";
    private static final String VALUES_PREFIX = "VALUES ";
    private static final String SPACE = " ";
    private static final String LF = "\n";
    private static final String ARG_LIST_START = "(";
    private static final String ARG_LIST_END = ")";
    private static final String DATE_VALUE_PREFIX = "TO_DATE( '";
    private static final String DATE_VALUE_SUFFIX = "', 'YYYYMMDDHH24MISS' )";

    private static final String BATCH_SEPARATOR = "SELECT * FROM DUAL\n/\n";

    private static final String CLOB_PLACEHOLDER = "EMPTY_CLOB()";
    private static final int CLOB_BATCH_SIZE = 4000;

    private static final String CLOB_BATCH_HEADER_PREFIX = "DECLARE    data CLOB; buffer VARCHAR2(32000);\nBEGIN\n    SELECT ";
    private static final String CLOB_BATCH_HEADER_MIDDLE = " INTO data FROM ";
    private static final String CLOB_BATCH_HEADER_SUFFIX = " \n    WHERE \n";
    private static final String EQUALITY_EXPRESSION = " = ";
    private static final String WHERE_CLAUSE_DELIMITER = " AND ";
    private static final String CLOB_DATA_PREFIX = "    \n    FOR UPDATE;        \n    buffer := '";
    private static final String CLOB_DATA_SUFFIX = "';\n" +
            "    DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);\n" +
            "END;\n" +
            "/\n";
    private static final String CLOB_LENGTH_COMMENT = "-- Length: ";
    private static final String CLOB_CHUNKS_COMMENT = "-- Chunks: ";

    @Override
    public String getInsertSql(Table table, BufferedReader reader, ImpexContext context) throws IOException {
        boolean hasClobColumns = false;
        List<Column> columns = ImpexUtils.getColumns(table);
        for(Column col : columns) {
            if(isColumnClobType(col)) {
                hasClobColumns = true;
            }
        }

        Map<RowData, List<DataBean>> longClobMap = null;
        if(hasClobColumns) {
            longClobMap = new HashMap<RowData, List<DataBean>>();
        }

        StringBuilder sqlBuilder = new StringBuilder();
        String line = readLineSkipHeader(reader);
        int rowCount = 0;
        int insertSqlLength = 0;
        // Iterate through the .mpx file
        for (;;) {

            // We hit the end of the .mpx file
            if (line == null) {
                break;
            }

            // Convert the tokens from the .mpx file into RowData
            RowData row = getMpxParser().parseMpxLine(columns, line);

            sqlBuilder.append(buildBatchSql(table, row));

            rowCount++;
            insertSqlLength += sqlBuilder.length();

            if(hasClobColumns) {
                List<DataBean> longClobs = new ArrayList<DataBean>();
                for(DataBean data : row.getDataBeans()) {
                    // if the column is a CLOB type, and it is long enough,
                    // add the data bean to the list of clobs that need to be split up
                    if (isColumnClobType(data.getColumn()) && data.getValue().length() > CLOB_BATCH_SIZE) {
                        longClobs.add(data);
                    }
                }
                longClobMap.put(row, longClobs);
            }

            if(batchLimitReached(rowCount, insertSqlLength, context)) {
                break;
            }

            // read the next line and start the loop over
            line = reader.readLine();
        }

        sqlBuilder.append(BATCH_SEPARATOR);

        if(hasClobColumns && !longClobMap.isEmpty()) {
            sqlBuilder.append(buildClobBatches(table, longClobMap));
        }

        return sqlBuilder.toString();
    }

    private boolean isColumnClobType(Column column) {
        return ImpexUtils.getColumnType(column).equals(SchemaType.CLOB);
    }

    private String buildClobBatches(Table table, Map<RowData, List<DataBean>> longClobMap) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(OUTPUT_DATE_FORMAT);
        StringBuilder sqlBuilder = new StringBuilder();

        // handle the clob data that is too long
        for(Map.Entry<RowData, List<DataBean>> entry: longClobMap.entrySet()) {
            RowData row = entry.getKey();
            List<DataBean> primaryKeys = row.findPrimaryKeyBeans();
            List<DataBean> longClobs = entry.getValue();

            for(DataBean data : longClobs) {

                // the number of 4000 charachter chunks in the data
                List<String> dataChunks = chunkClob(data.getValue());
                sqlBuilder.append(buildClobHeader(dataChunks, data));

                // for each chunk, write sql that will append the chunk into the clob column
                for (String chunk : dataChunks) {
                    sqlBuilder.append(CLOB_BATCH_HEADER_PREFIX).append(data.getColumn().getName()).append(CLOB_BATCH_HEADER_MIDDLE).append(table.getName()).append(CLOB_BATCH_HEADER_SUFFIX);

                    String clauseDelimiter = "";
                    for (DataBean pk : primaryKeys) {
                        sqlBuilder.append(clauseDelimiter);
                        sqlBuilder.append(SPACE).append(pk.getColumn().getName()).append(EQUALITY_EXPRESSION).append(getStringValue(pk, dateFormat));

                        clauseDelimiter = WHERE_CLAUSE_DELIMITER;
                    }

                    sqlBuilder.append(CLOB_DATA_PREFIX).append(chunk).append(CLOB_DATA_SUFFIX);


                }
            }
        }

        return sqlBuilder.toString();

    }

    private String buildClobHeader(List<String> dataChunks, DataBean data) {
        StringBuilder headerBuilder = new StringBuilder();
        headerBuilder.append(CLOB_LENGTH_COMMENT).append(data.getValue().length()).append(LF);
        headerBuilder.append(CLOB_CHUNKS_COMMENT).append(dataChunks.size()).append(LF);

        return headerBuilder.toString();
    }


    /**
     * Split a long data string into clob chunks,
     * which have a maximum size of CLOB_BATCH_SIZE and have all single quotes "'"
     * replaced with the escaped version (two single quotes, "''")
     *
     * @param value the full data string
     * @return a list of strings representing the full data split into chunks
     */
    private List<String> chunkClob(String value) {
        List<String> results = new ArrayList<String>();

        String currentValue = value;
        while (currentValue.length() > CLOB_BATCH_SIZE) {
            String chunk = currentValue.substring(0, CLOB_BATCH_SIZE);

            // escape all single quotes
            chunk = chunk.replace("'", "''");
            results.add(chunk);
            currentValue = currentValue.substring(CLOB_BATCH_SIZE);
        }

        if(StringUtils.isNotEmpty(currentValue)) {
            results.add(currentValue);
        }

        return results;
    }

    private String buildBatchSql(Table table, RowData row) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(OUTPUT_DATE_FORMAT);
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(INSERT_PREFIX);

        List<String> values = new ArrayList<String>(row.getDataBeans().size());
        for(DataBean data : row.getDataBeans()) {
            values.add(getStringValue(data, dateFormat));
        }

        // output looks like "  INSERT INTO FOO_BAR_T (FOO, BAR, BAZ)"
        sqlBuilder.append(INDENT).append(INTO_PREFIX).append(table.getName()).append(SPACE).append(ARG_LIST_START).append(CollectionUtils.getCSV(row.findColumnNames())).append(ARG_LIST_END);
        sqlBuilder.append(LF);
        // output looks like "  VALUES ('Test', 1, 2)"
        sqlBuilder.append(INDENT).append(VALUES_PREFIX).append(ARG_LIST_START).append(CollectionUtils.getCSV(values)).append(ARG_LIST_END);
        sqlBuilder.append(LF);

        return sqlBuilder.toString();
    }

    private String getStringValue(DataBean data, SimpleDateFormat dateFormat) {
        StringBuilder result = new StringBuilder();

        if(data.getDateValue() != null) {
            result.append(DATE_VALUE_PREFIX);
            result.append(dateFormat.format(data.getDateValue()));
            result.append(DATE_VALUE_SUFFIX);
        }
        // if the data type is CLOB, and the data is longer than the batch size, the value should be handled by the CLOB-splitting code
        else if(isColumnClobType(data.getColumn()) && data.getValue().length() > CLOB_BATCH_SIZE) {
            result.append(CLOB_PLACEHOLDER);
        }
        else {
            result.append(data.getValue());
        }

        return result.toString();
    }
}
