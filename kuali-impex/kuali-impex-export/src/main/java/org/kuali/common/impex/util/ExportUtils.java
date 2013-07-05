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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.impex.data.service.impl.DumpTableContext;
import org.kuali.common.impex.data.service.impl.DumpTableResult;
import org.kuali.common.impex.model.NamedElement;
import org.kuali.common.impex.model.Table;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.Project;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.StringFilter;

public class ExportUtils {

	protected static final String SIZE_PROPERTY_SUFFIX = ".size";
	protected static final String ROWS_PROPERTY_SUFFIX = ".rows";

	public static final int DEFAULT_DATA_THREADS = 15;
	public static final int DEFAULT_ROW_INTERVAL = 50;
	public static final String DEFAULT_DATA_INTERVAL = "50k";
	public static final String DEFAULT_SCHEMA_XML_FILE = "schema.xml";

	public static File getOutputDir(File basedir, Project project) {
		String groupIdPath = project.getProperties().getProperty("project.groupId.base.path");
		String artifactId = project.getArtifactId();

		StringBuilder sb = new StringBuilder();
		sb.append(LocationUtils.getCanonicalPath(basedir));
		sb.append("/");
		sb.append(groupIdPath);
		sb.append("/");
		sb.append(artifactId);

		File file = new File(sb.toString());

		// Let the JVM resolve the canonical path
		// This produces a file that displays pathing according the native preferences of the OS the user is running on
		// ie on Windows it will display "\" instead of "/" for the file separators
		return new File(LocationUtils.getCanonicalPath(file));
	}

	public static File getSchemaFile(File basedir, Project project) {
		return getSchemaFile(getOutputDir(basedir, project));
	}

	public static File getSchemaFile(File directory) {
		return new File(directory, DEFAULT_SCHEMA_XML_FILE);
	}

	public static <T extends NamedElement> List<T> getIncludedElements(StringFilter filter, List<T> elements) {

		List<T> included = new ArrayList<T>();

		for (T element : elements) {
			if (filter.include(element.getName())) {
				included.add(element);
			}
		}

		return included;
	}

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
	 * Store table data size and row count to a file. Use the lowercase table name as the prefix for each key This method should append to any existing statistics found at the
	 * location, if any already exist
	 * 
	 * @param tableResults
	 *            list of exported table data results
	 * @param location
	 *            file path defining where to write the property file
	 */
	public static void storeTableStatistics(List<DumpTableResult> tableResults, String location) {
		Properties tableStatistics;

		if (LocationUtils.exists(location)) {
			tableStatistics = PropertyUtils.loadSilently(location);
		} else {
			tableStatistics = new Properties();
		}

		for (DumpTableResult result : tableResults) {
			String nameKey = result.getTableContext().getTable().getName().toLowerCase();
			tableStatistics.setProperty(nameKey + SIZE_PROPERTY_SUFFIX, Long.toString(result.getSize()));
			tableStatistics.setProperty(nameKey + ROWS_PROPERTY_SUFFIX, Long.toString(result.getRows()));
		}

		PropertyUtils.store(tableStatistics, new File(location));
	}
}
