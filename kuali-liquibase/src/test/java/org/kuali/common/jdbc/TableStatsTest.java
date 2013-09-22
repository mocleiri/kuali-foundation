/**
 * Copyright 2010-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.common.jdbc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.codehaus.plexus.util.StringUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.kuali.common.util.Assert;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.LoggerUtils;
import org.kuali.common.util.PropertyUtils;

public class TableStatsTest {

	@Test
	@Ignore
	public void test() {
		try {
			String location = "/Users/jcaddel/sts/3.1.0.RELEASE/workspace/ks-sandbox/ks-deployments/ks-deployment-resources/src/main/resources/org/kuali/student/ks-source-db/tablestats.properties";

			Properties p = PropertyUtils.load(location);

			// Should always be an even number of properties
			Assert.isEven(p.size(), "There must always be exactly 2 keys per table");

			List<TableStats> tables = getTables(p);
			sortTables(tables);

			List<String> columns = Arrays.asList("name", "rows", "size");
			List<Object[]> rows = getRows(tables);

			LoggerUtils.logTable(columns, rows);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected List<Object[]> getRows(List<TableStats> tables) {
		List<Object[]> rows = new ArrayList<Object[]>();
		for (TableStats table : tables) {
			String size = FormatUtils.getCount(table.getRows());
			String rowCount = FormatUtils.getSize(table.getSize());
			Object[] row = { table.getName(), size, rowCount };
			rows.add(row);
		}
		return rows;
	}

	protected void sortTables(List<TableStats> tables) {
		Collections.sort(tables, new TableStatsSizeComparator());
		Collections.reverse(tables);
	}

	protected List<TableStats> getTables(Properties p) {
		List<String> keys = PropertyUtils.getSortedKeys(p);
		List<TableStats> tables = new ArrayList<TableStats>();
		for (int i = 0; i < keys.size(); i += 2) {
			String rowsKey = keys.get(i);
			String sizeKey = keys.get(i + 1);
			long rows = Long.parseLong(p.getProperty(rowsKey));
			long size = Long.parseLong(p.getProperty(sizeKey));
			String name = StringUtils.substring(rowsKey, 0, rowsKey.indexOf("."));
			TableStats table = new TableStats();
			table.setRows(rows);
			table.setSize(size);
			table.setName(name);
			if (rows > 0) {
				tables.add(table);
			}
		}
		return tables;
	}
}
