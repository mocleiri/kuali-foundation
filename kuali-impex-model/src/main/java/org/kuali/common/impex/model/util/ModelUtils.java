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

package org.kuali.common.impex.model.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.common.impex.model.Column;
import org.kuali.common.impex.model.NamedElement;
import org.kuali.common.impex.model.Table;
import org.kuali.common.util.CollectionUtils;

public class ModelUtils {

    public static String getCsvPrimaryKeyColumnNames(Table t) {
        List<String> names = new ArrayList<String>();
        for (Column col : CollectionUtils.toEmptyList(t.getColumns())) {
            if (col.isPrimaryKey()) {
                names.add(col.getName());
            }
        }

        if (names.isEmpty()) {
            return "";
        }
        else {
            return CollectionUtils.getCSV(names);
        }
    }

    public static Map<String, Column> getColumnNameMap(Table t) {
        Map<String, Column> result = new HashMap<String, Column>();

        for (Column c : t.getColumns()) {
            result.put(c.getName(), c);
        }

        return result;
    }

    public static String getCsvColumnNames(List<Column> columns) {
        List<String> names = new ArrayList<String>(columns.size());
        for (Column col : CollectionUtils.toEmptyList(columns)) {
            names.add(col.getName());
        }

        return CollectionUtils.getCSV(names);
    }

    public static <T extends NamedElement> Map<String, T> buildNameMap(List<T> namedElements) {
        Map<String, T> results = new HashMap<String, T>(namedElements.size());

        for (T n : namedElements) {
            results.put(n.getName(), n);
        }

        return results;
    }

}
