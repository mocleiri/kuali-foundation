/**
 * Copyright 2011 The Kuali Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.kuali.common.impex.service.schema.impl.oracle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.impex.model.Column;
import org.kuali.common.impex.model.DataType;
import org.kuali.common.impex.model.Index;
import org.kuali.common.impex.model.Table;
import org.kuali.common.impex.model.TypeSize;
import org.kuali.common.impex.model.UniqueConstraint;
import org.kuali.common.impex.service.ProducerUtils;
import org.kuali.common.impex.service.schema.impl.AbstractTableSqlProducer;
import org.kuali.common.impex.service.schema.impl.DataTypeMapping;
import org.kuali.common.util.CollectionUtils;

public class OracleTableSqlProducer extends AbstractTableSqlProducer {

    protected static final String DROP_TABLE_HEADER = "DECLARE temp NUMBER;\n" +
            "BEGIN\n" +
            "\tSELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = '";

    protected static final String DROP_TABLE_MIDDLE = "';\n" +
            "\tIF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE ";

    protected static final String DROP_TABLE_FOOTER = " CASCADE CONSTRAINTS PURGE'; END IF;\n" +
            "END;\n/";

    protected static final String CREATE_TABLE_HEADER = "CREATE TABLE ";
    protected static final String CREATE_TABLE_FOOTER = ")\n/";

    protected static final String COLUMN_SECTION_PREFIX = "\n(";
    protected static final String COLUMN_SECTION_SUFFIX = "\n";

    protected static final String UNIQUE_PREFIX = "CONSTRAINT ";
    protected static final String UNIQUE_MIDDLE = " UNIQUE (";
    protected static final String UNIQUE_SUFFIX = ")";

    protected static final String PRIMARY_KEY_HEADER = "ALTER TABLE ";
    protected static final String PRIMARY_KEY_ADD_CONSTRAINT = "\n\tADD CONSTRAINT ";
    protected static final String PRIMARY_KEY_LISTING_PREFIX = "\nPRIMARY KEY (";
    protected static final String PRIMARY_KEY_FOOTER = ")\n/";

    protected static final String INDEX_HEADER = "CREATE INDEX ";
    protected static final String INDEX_UNIQUE_HEADER = "CREATE UNIQUE INDEX ";
    protected static final String INDEX_TABLE_NAME_PREFIX = "\n\tON ";
    protected static final String INDEX_COLUMN_LIST_PREFIX = "\n\t(";
    protected static final String INDEX_FOOTER = ")\n/";

    protected static final String TABLE_DESCRIPTION_PREFIX = "\n\nCOMMENT ON TABLE ";
    protected static final String DESCRIPTION_IS_KEYWORD = " IS '";
    protected static final String TABLE_DESCRIPTION_SUFFIX = "'\n/";

    protected static final String COLUMN_DESCRIPTION_PREFIX = "\n\nCOMMENT ON COLUMN ";
    protected static final String COLUMN_DESCRIPTION_SUFFIX = "'\n/";

    protected static final String SPACE = " ";
    protected static final String TYPE_SIZE_PREFIX = "(";
    protected static final String COMMA = ",";
    protected static final String DOT = ".";
    protected static final String TYPE_SIZE_SUFFIX = ")";
    protected static final String NOT_NULL = "NOT NULL";
    protected static final String NEWLINE_TAB = "\n\t";
    protected static final String NEWLINE = "\n";

    /**
     * Oracle data types
     */
    protected static final String ORACLE_VARCHAR = "VARCHAR2".intern();

    protected static final Map<DataType, String> ORACLE_DATAYPE_MAP = new HashMap<DataType, String>();


    static {
        ORACLE_DATAYPE_MAP.put(DataType.STRING, ORACLE_VARCHAR);
    }

    public List<String> getTablesSql(List<Table> tables) {
        List<String> results;

        if (tables == null || tables.isEmpty()) {
            return Collections.emptyList();
        }

        results = new ArrayList<String>();

        for (Table t : tables) {
            results.addAll(generateCreateTableStatement(t));
        }

        return results;
    }

    protected List<String> generateCreateTableStatement(Table t) {
        List<String> results = new ArrayList<String>();


        results.add(generateDropTableStatement(t));

        StringBuilder sb = new StringBuilder();

        sb.append(CREATE_TABLE_HEADER);
        sb.append(t.getName());

        sb.append(COLUMN_SECTION_PREFIX);
        sb.append(generateColumns(t));
        sb.append(generateUniqueConstraintDefinitions(t));
        sb.append(COLUMN_SECTION_SUFFIX);

        sb.append(CREATE_TABLE_FOOTER);

        results.add(sb.toString());

        String pk = generatePrimaryKeyConstraint(t);
        if(StringUtils.isNotEmpty(pk)) {
            results.add(pk);
        }

        results.addAll(generateIndices(t));

        results.addAll(generateComments(t));

        return results;
    }

    protected String generateColumns(Table t) {
        StringBuilder sb = new StringBuilder();

        boolean firstColumn = true;
        for (Column column : t.getColumns()) {
            if (firstColumn) {
                firstColumn = false;
                sb.append(NEWLINE_TAB);
            } else {
                sb.append(COMMA);
                sb.append(NEWLINE_TAB);
            }
            sb.append(generateColumnDefinition(column));
        }

        return sb.toString();
    }

    protected String generateColumnDefinition(Column column) {
        StringBuilder sb = new StringBuilder();

        DataTypeMapping mapping = getMappingProvider().getDataTypeMapping(column);

        if (mapping != null) {
            sb.append(generateColumnDefinition(column, mapping));
        } else {

            // build the definition as <column name> <column type> <default value> <null/not null>
            sb.append(column.getName());
            sb.append(SPACE);

            // column type
            sb.append(translateDataType(column.getDataType()));

            TypeSize typeSize = column.getTypeSize();
            if(typeSize != null) {
                sb.append(TYPE_SIZE_PREFIX);
                sb.append(typeSize.getSize());
                if(typeSize.hasScale()) {
                    sb.append(COMMA);
                    sb.append(typeSize.getScale());
                }
                sb.append(TYPE_SIZE_SUFFIX);
            }

            // default value
            if(StringUtils.isNotEmpty(column.getDefaultValue())) {
                sb.append(SPACE);
                if(column.isTextType()) {
                    sb.append('\'').append(column.getDefaultValue()).append('\'');
                }
                else {
                    sb.append(column.getDefaultValue());
                }
            }

            if(!column.isNullable()) {
                sb.append(SPACE).append(NOT_NULL);
            }

        }

        return sb.toString();
    }

    protected String generateUniqueConstraintDefinitions(Table t) {
        StringBuilder sb = new StringBuilder();

        for(UniqueConstraint unique : t.getUniqueConstraints()) {
            sb.append(COMMA);
            sb.append(NEWLINE_TAB);
            sb.append(UNIQUE_PREFIX);
            sb.append(unique.getName());
            sb.append(UNIQUE_MIDDLE);
            sb.append(ProducerUtils.getCsvColumnNames(unique.getColumns()));
            sb.append(UNIQUE_SUFFIX);
        }

        return sb.toString();
    }

    protected String translateDataType(DataType dataType) {
        if(ORACLE_DATAYPE_MAP.containsKey(dataType)) {
            return ORACLE_DATAYPE_MAP.get(dataType);
        }

        return dataType.name();
    }

    protected String generateColumnDefinition(Column column, DataTypeMapping mapping) {
        return "";
    }

    protected String generateDropTableStatement(Table t) {
        StringBuilder sb = new StringBuilder();

        sb.append(DROP_TABLE_HEADER);
        sb.append(t.getName());
        sb.append(DROP_TABLE_MIDDLE);
        sb.append(t.getName());
        sb.append(DROP_TABLE_FOOTER);

        return sb.toString();
    }

    protected String generatePrimaryKeyConstraint(Table t) {
        String primaryKeysString = ProducerUtils.getCsvPrimaryKeyColumnNames(t);

        // If there are no primary keys, return an empty string
        if (StringUtils.isEmpty(primaryKeysString)) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        sb.append(PRIMARY_KEY_HEADER);
        sb.append(t.getName());
        sb.append(PRIMARY_KEY_ADD_CONSTRAINT);
        sb.append(ProducerUtils.generatePrimaryKeyName(t));
        sb.append(PRIMARY_KEY_LISTING_PREFIX);
        sb.append(primaryKeysString);
        sb.append(PRIMARY_KEY_FOOTER);

        return sb.toString();
    }

    protected List<String> generateIndices(Table t) {
        List<String> results = new ArrayList<String>();

        List<Index> indices = CollectionUtils.toEmptyList(t.getIndices());

        if (!indices.isEmpty()) {
            StringBuilder sb;
            for (Index index : indices) {
                sb = new StringBuilder();

                if (index.isUnique()) {
                    sb.append(INDEX_UNIQUE_HEADER);
                } else {
                    sb.append(INDEX_HEADER);
                }

                sb.append(index.getName());
                sb.append(INDEX_TABLE_NAME_PREFIX);
                sb.append(t.getName());
                sb.append(INDEX_COLUMN_LIST_PREFIX);
                sb.append(ProducerUtils.getCsvColumnNames(index.getColumns()));
                sb.append(INDEX_FOOTER);

                results.add(sb.toString());
            }
        }

        return results;
    }

    protected List<String> generateComments(Table t) {

        List<String> results = new ArrayList<String>();

        StringBuilder sb = new StringBuilder();

        if (StringUtils.isNotEmpty(t.getDescription())) {
            sb.append(TABLE_DESCRIPTION_PREFIX);
            sb.append(t.getName());
            sb.append(DESCRIPTION_IS_KEYWORD);
            sb.append(t.getDescription());
            sb.append(TABLE_DESCRIPTION_SUFFIX);

            results.add(sb.toString());
        }

        for (Column c : t.getColumns()) {
            if (StringUtils.isNotEmpty(c.getDescription())) {
                sb = new StringBuilder();

                sb.append(COLUMN_DESCRIPTION_PREFIX);
                sb.append(t.getName());
                sb.append(DOT);
                sb.append(c.getName());
                sb.append(DESCRIPTION_IS_KEYWORD);
                sb.append(COLUMN_DESCRIPTION_SUFFIX);

                results.add(sb.toString());
            }
        }

        return results;
    }
}
