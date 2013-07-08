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
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.kuali.common.impex.model.Column;
import org.kuali.common.impex.model.ForeignKey;
import org.kuali.common.impex.model.NamedElement;
import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.model.Sequence;
import org.kuali.common.impex.model.Table;
import org.kuali.common.impex.model.View;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LogTableContext;
import org.kuali.common.util.LoggerUtils;
import org.kuali.common.util.StringFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class ModelUtils {

	private static final Logger logger = LoggerFactory.getLogger(ModelUtils.class);

	public static void log(Schema schema) {
		Assert.notNull(schema, "schema is null");
		List<String> columns = Arrays.asList("element", "name");
		List<Object[]> rows = new ArrayList<Object[]>();
		for (Table table : CollectionUtils.toEmptyList(schema.getTables())) {
			rows.add(new Object[] { "table", table.getName() });
		}
		for (Sequence sequence : CollectionUtils.toEmptyList(schema.getSequences())) {
			rows.add(new Object[] { "sequence", sequence.getName() });
		}
		for (View view : CollectionUtils.toEmptyList(schema.getViews())) {
			rows.add(new Object[] { "view", view.getName() });
		}
		for (ForeignKey foreignKey : CollectionUtils.toEmptyList(schema.getForeignKeys())) {
			rows.add(new Object[] { "foreign key", foreignKey.getName() });
		}
		if (!CollectionUtils.isEmpty(rows)) {
			LogTableContext context = new LogTableContext(columns, rows, logger);
			LoggerUtils.logTable(context);
		}
	}

	public static void fillInSchema(Schema schema) {
		for (Table table : CollectionUtils.toEmptyList(schema.getTables())) {
			for (Column column : CollectionUtils.toEmptyList(table.getColumns())) {
				column.setTable(table);
			}
		}
	}

	public static Schema filter(Schema schema, StringFilter nameFilter) {
		List<Table> excludedTables = ModelUtils.filterAndSortElements(schema.getTables(), nameFilter);
		List<View> excludedViews = ModelUtils.filterAndSortElements(schema.getViews(), nameFilter);
		List<Sequence> excludedSequences = ModelUtils.filterAndSortElements(schema.getSequences(), nameFilter);
		List<ForeignKey> excludedForeignKeys = ModelUtils.filterAndSortElements(schema.getForeignKeys(), nameFilter);

		Schema excludedSchema = new Schema();
		excludedSchema.setName(schema.getName());
		excludedSchema.setTables(excludedTables);
		excludedSchema.setViews(excludedViews);
		excludedSchema.setSequences(excludedSequences);
		excludedSchema.setForeignKeys(excludedForeignKeys);
		return excludedSchema;
	}

	public static List<String> getPrimaryKeyColumnNames(Table t) {
		List<String> names = new ArrayList<String>();
		for (Column col : CollectionUtils.toEmptyList(t.getColumns())) {
			if (col.isPrimaryKey()) {
				names.add(col.getName());
			}
		}
		return names;
	}

	public static String getCsvPrimaryKeyColumnNames(Table t) {
		List<String> names = getPrimaryKeyColumnNames(t);
		if (names.isEmpty()) {
			return "";
		} else {
			return CollectionUtils.getCSV(names);
		}
	}

	public static Map<String, Column> getColumnNameMap(Table t) {
		Map<String, Column> columns = new HashMap<String, Column>();
		for (Column c : t.getColumns()) {
			columns.put(c.getName(), c);
		}
		return columns;
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

	/**
	 * Alter the <code>List</code> passed in by removing any elements that don't belong and then sorting the ones that remain by name
	 */
	public static <T extends NamedElement> List<T> filterAndSortElements(List<T> elements, StringFilter filter) {

		// Remove elements that don't belong
		List<T> excluded = filterElements(elements, filter);

		// Sort the elements by name
		Collections.sort(elements, NamedElementComparator.getInstance());

		// Return an object detailing the elements that were excluded
		return excluded;
	}

	/**
	 * Remove elements from the list that should not be there
	 */
	public static <T extends NamedElement> List<T> filterElements(List<T> elements, StringFilter filter) {

		// No filter, nothing to do
		if (filter == null) {
			return Collections.<T> emptyList();
		}

		// Make sure we are configured correctly
		Assert.notNull(elements, "elements is null");

		// Iterate over the elements, removing elements that shouldn't be there
		List<T> excluded = new ArrayList<T>();
		Iterator<T> itr = elements.iterator();
		while (itr.hasNext()) {
			T element = itr.next();
			String name = element.getName();
			if (!filter.include(name)) {
				excluded.add(element);
				itr.remove();
			}
		}
		return excluded;
	}

}
