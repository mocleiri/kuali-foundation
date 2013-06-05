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

package org.kuali.common.jalc.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.jalc.data.ExportTableContext;
import org.kuali.common.jalc.model.NamedElement;
import org.kuali.common.jalc.model.Table;
import org.kuali.common.util.StringFilter;

public class ExportUtils {

    protected static final String SIZE_PROPERTY_SUFFIX = ".size";
    protected static final String ROWS_PROPERTY_SUFFIX = ".rows";
    public static final Integer DEFAULT_DATA_THREADS = 15;
    public static final Integer DEFAULT_ROW_INTERVAL = 50;
    public static final Integer DEFAULT_DATA_INTERVAL = 50 * 1024;

    public static <T extends NamedElement> Collection<T> getIncludedElements(StringFilter stringFilter, List<T> elements) {

        Collection<T> results = new ArrayList<T>();

        for (T named : elements) {
            if (stringFilter.include(named.getName())) {
                results.add(named);
            }
        }

        return results;
    }

    /**
     * Populate table data size and row count from properties
     *
     * @param tableStatistics a properties object
     * @param table the Table
     * @param context the ExportTableContext to populate
     */
    public static void populateTableStatistics(Properties tableStatistics, Table table, ExportTableContext context) {
        if(tableStatistics != null) {
            String sizeVal = tableStatistics.getProperty(table.getName() + SIZE_PROPERTY_SUFFIX);
            String rowCountVal = tableStatistics.getProperty(table.getName() + ROWS_PROPERTY_SUFFIX);

            if (StringUtils.isNotBlank(sizeVal)) {
                context.setSize(Long.parseLong(sizeVal));
            }
            if (StringUtils.isNotBlank(rowCountVal)) {
                context.setRowCount(Long.parseLong(rowCountVal));
            }
        }
    }
}
