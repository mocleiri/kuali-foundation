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

package org.kuali.common.impex.util;

import java.io.File;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.impex.data.service.impl.DumpTableContext;
import org.kuali.common.impex.data.service.impl.DumpTableResult;
import org.kuali.common.impex.model.Table;
import org.kuali.common.util.PropertyUtils;

public class DumpUtils {

	protected static final String SIZE_PROPERTY_SUFFIX = ".size";
	protected static final String ROWS_PROPERTY_SUFFIX = ".rows";

	public static final int DEFAULT_DATA_THREADS = 15;
	public static final int DEFAULT_ROW_INTERVAL = 50;
	public static final String DEFAULT_DATA_INTERVAL = "50k";

	/**
	 * Populate table data size and row count from properties. Use the lowercase table name as the prefix for each key
	 * 
	 * @param tableStatistics
	 *            a properties object
	 * @param table
	 *            the Table
	 * @param context
	 *            the ExportTableContext to populate
	 */
	public static void populateTableStatistics(Properties tableStatistics, Table table, DumpTableContext context) {
		if (tableStatistics != null) {
			String tableNameKeyFormat = table.getName().toLowerCase();
			String sizeVal = tableStatistics.getProperty(tableNameKeyFormat + SIZE_PROPERTY_SUFFIX);
			String rowCountVal = tableStatistics.getProperty(tableNameKeyFormat + ROWS_PROPERTY_SUFFIX);

			if (StringUtils.isNotBlank(sizeVal)) {
				context.setSize(Long.parseLong(sizeVal));
			}
			if (StringUtils.isNotBlank(rowCountVal)) {
				context.setRowCount(Long.parseLong(rowCountVal));
			}
		}
	}

	/**
	 * Store table data size and row count to a file. Use the lowercase table name as the prefix for each key.
	 * 
	 * @param tableResults
	 *            list of exported table data results
	 * @param location
	 *            file path defining where to write the property file
	 */
	public static void storeTableStatistics(List<DumpTableResult> tableResults, String location) {
		Properties props = new Properties();
		for (DumpTableResult result : tableResults) {
			String nameKey = result.getTableContext().getTable().getName().toLowerCase();
			props.setProperty(nameKey + SIZE_PROPERTY_SUFFIX, Long.toString(result.getSize()));
			props.setProperty(nameKey + ROWS_PROPERTY_SUFFIX, Long.toString(result.getRows()));
		}

		PropertyUtils.storeSilently(props, new File(location));
	}
}
