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
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.kuali.common.impex.model.Column;
import org.kuali.common.impex.model.NamedElement;
import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.model.Table;
import org.kuali.common.impex.model.TypeSize;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.StringFilter;
import org.springframework.util.Assert;

public class ModelUtils {

	public static Schema clone(Schema original, StringFilter nameFilter) {
		Schema clone = clone(original);
		ModelUtils.filterAndSortElements(clone.getTables(), nameFilter);
		ModelUtils.filterAndSortElements(clone.getViews(), nameFilter);
		ModelUtils.filterAndSortElements(clone.getSequences(), nameFilter);
		ModelUtils.filterAndSortElements(clone.getForeignKeys(), nameFilter);
		return clone;
	}

	public static Schema clone(Schema original) {
		Schema clone = new Schema();
		clone.setName(original.getName());
		clone.getTables().addAll(original.getTables());
		clone.getSequences().addAll(original.getSequences());
		clone.getViews().addAll(original.getViews());
		clone.getForeignKeys().addAll(original.getForeignKeys());
		return clone;
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

	/**
	 * Alter the <code>List</code> passed in by removing any elements that don't belong and then sorting the ones that remain by name
	 */
	public static <T extends NamedElement> void filterAndSortElements(List<T> elements, StringFilter filter) {

		// Remove elements that don't belong
		filterElements(elements, filter);

		// Sort the elements by name
		Collections.sort(elements, NamedElementComparator.getInstance());
	}

	/**
	 * Remove elements from the list that should not be there
	 */
	public static <T extends NamedElement> void filterElements(List<T> elements, StringFilter filter) {

		// No filter, nothing to do
		if (filter == null) {
			return;
		}

		// Make sure we are configured correctly
		Assert.notNull(elements, "elements is null");

		// Iterate over the elements, removing elements that shouldn't be there
		Iterator<T> itr = elements.iterator();
		while (itr.hasNext()) {
			NamedElement element = itr.next();
			String name = element.getName();
			if (!filter.include(name)) {
				itr.remove();
			}
		}
	}

}
