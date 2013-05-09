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

package org.kuali.common.impex.service;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.impex.model.Column;
import org.kuali.common.impex.model.Table;
import org.kuali.common.util.CollectionUtils;

/**
 * String utility methods for classes creating sql from schema model data
 */
public class ProducerUtils {

    /**
     * This value was retrieved from the Velocity templates that were generating
     * the schema sqls in previous versions of Impex.
     *
     * No idea on why this value was chosen, though it's safe to guess it ensures
     * a primary key name that is of acceptable length to many DB vendors
     */
    private static final int MAX_TABLE_NAME_SNIPPET_SIZE_FOR_PK = 27;

    private static final String PRIMARY_KEY_SUFFIX = "P1";

    public static String getCsvColumnNames(List<Column> columns) {
        List<String> names = new ArrayList<String>(columns.size());
        for (Column col : CollectionUtils.toEmptyList(columns)) {
            names.add(col.getName());
        }

        return CollectionUtils.getCSV(names);
    }

    public static String getCsvPrimaryKeyColumnNames(Table t) {
        List<String> names = new ArrayList<String>();
        for (Column col : CollectionUtils.toEmptyList(t.getColumns())) {
            if (col.isPrimaryKey()) {
                names.add(col.getName());
            }
        }

        return CollectionUtils.getCSV(names);
    }

    public static String generatePrimaryKeyName(Table t) {
        StringBuilder sb = new StringBuilder();

        // truncate table name to MAX_TABLE_NAME_SNIPPET_SIZE_FOR_PK
        String truncated = t.getName();
        if(truncated.length() > MAX_TABLE_NAME_SNIPPET_SIZE_FOR_PK) {
            truncated = truncated.substring(0, MAX_TABLE_NAME_SNIPPET_SIZE_FOR_PK);
        }

        sb.append(truncated).append(PRIMARY_KEY_SUFFIX);

        return sb.toString();
    }
}
