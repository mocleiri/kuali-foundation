package org.kuali.common.impex.data.impl.mysql;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.impex.data.MpxHeaderData;
import org.kuali.common.impex.data.impl.AbstractSqlProducer;
import org.kuali.common.impex.data.impl.DataBean;
import org.kuali.common.impex.data.impl.MpxParser;
import org.kuali.common.impex.model.Column;
import org.kuali.common.impex.model.Table;
import org.kuali.common.util.CollectionUtils;

public class MySqlProducer extends AbstractSqlProducer {

    public static final String SUPPORTED_VENDOR = "mysql";

    private static final String BATCH_SEPARATOR = ",";
    private static final String ARG_LIST_START = "(";
    private static final String ARG_LIST_END = ")";
    private static final String SPACE = " ";

    private static final String DATE_VALUE_PREFIX = "STR_TO_DATE('";
    public static final String DATE_VALUE_SUFFIX = "','%Y%m%d%H%i%s')";

    private static final String PREFIX_START = "INSERT INTO ";
    private static final String PREFIX_END = " VALUES ";

    /**
     * Read data lines from the .mpx file and combine them into batched up, INSERT INTO sql statements. Individual data lines are merged together into SQL statements 50 lines at a
     * time or 50K in length whichever comes first.
     */
    @Override
    public List<String> getSql(Table table, MpxHeaderData headerData, BufferedReader reader) throws IOException {

        // Extract the columns into a list
        List<Column> columns = table.getColumns();

        // Setup some storage
        StringBuilder sb = new StringBuilder();

        // INSERT INTO FOO (BAR1,BAR2) VALUES
        sb.append(getPrefix(table, headerData));

        // Track rows processed
        int rows = 0;

        // read the first line
        String line = reader.readLine();

        // Iterate through the .mpx file
        for (; ; ) {

            // We hit the end of the .mpx file
            if (line == null) {
                break;
            }

            List<DataBean> rowBeans = buildRowData(columns, MpxParser.parseMpxLine(line), headerData);

            if (rows != 0) {
                // Need to add a comma, unless this is the first set of values
                sb.append(BATCH_SEPARATOR);
            }

            sb.append(buildBatchSql(rowBeans));

            // increment our counters
            rows++;

            // Have we exceeded any of our limits?
            if (batchLimitReached(rows, sb.length())) {
                break;
            }

            // read the next line and start the loop over
            line = reader.readLine();
        }

        // return null to indicate no rows were processed
        if (rows == 0) {
            return null;
        } else {
            return Collections.singletonList(sb.toString());
        }
    }

    protected String buildBatchSql(List<DataBean> rowBeans) {
        StringBuilder batchBuilder = new StringBuilder();
        SimpleDateFormat sqlDateFormatter = new SimpleDateFormat(OUTPUT_DATE_FORMAT);
        List<String> sqlValues = new ArrayList<String>(rowBeans.size());
        for (DataBean d : rowBeans) {
            sqlValues.add(getSqlValue(d, sqlDateFormatter));
        }
        batchBuilder.append(ARG_LIST_START);
        batchBuilder.append(CollectionUtils.getCSV(sqlValues));
        batchBuilder.append(ARG_LIST_END);
        return batchBuilder.toString();
    }

    protected String getSqlValue(DataBean data, SimpleDateFormat dateFormat) {
        StringBuilder result = new StringBuilder();

        if (data.getDateValue() != null) {
            result.append(DATE_VALUE_PREFIX);
            result.append(dateFormat.format(data.getDateValue()));
            result.append(DATE_VALUE_SUFFIX);
        } else {
            result.append(data.getValue());
        }

        return result.toString();
    }

    // INSERT INTO FOO (BAR1,BAR2) VALUES
    protected String getPrefix(Table table, MpxHeaderData headerData) {
        String columnNamesCSV = CollectionUtils.getCSV(headerData.getColumnNames());
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_START).append(table.getName()).append(SPACE);
        sb.append(ARG_LIST_START).append(columnNamesCSV).append(ARG_LIST_END);
        sb.append(PREFIX_END);
        return sb.toString();
    }

    @Override
    protected String getEscapedValue(Column column, String token) {
        String escaped1 = StringUtils.replace(token, "\\", "\\\\");
        String escaped2 = StringUtils.replace(escaped1, "'", "\\'");
        String escaped3 = StringUtils.replace(escaped2, "\n", "\\n");
        return "'" + escaped3 + "'";
    }
}
