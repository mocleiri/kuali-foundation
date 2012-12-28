/**
 * Copyright 2010-2012 The Kuali Foundation
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
package org.kuali.common.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

public class CollectionUtils {

	public static final List<String> getLines(String s) {
		if (s == null) {
			return Collections.<String> emptyList();
		}
		try {
			return IOUtils.readLines(new StringReader(s));
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * Return a new list containing the unique set of strings contained in both lists
	 */
	public static final List<String> combineStringsUniquely(List<String> list1, List<String> list2) {
		List<String> newList = new ArrayList<String>(list1);
		for (String element : list2) {
			if (!newList.contains(element)) {
				newList.add(element);
			}
		}
		return newList;
	}

	/**
	 * Return a list containing only the elements where the corresponding index in the <code>includes</code> list is <code>true</code>.
	 * <code>includes</code> and <code>list</code> must be the same size.
	 */
	public static final <T> List<T> getList(List<Boolean> includes, List<T> list) {
		Assert.isTrue(includes.size() == list.size());
		List<T> included = new ArrayList<T>();
		for (int i = 0; i < includes.size(); i++) {
			if (includes.get(i)) {
				included.add(list.get(i));
			}
		}
		return included;
	}

	/**
	 * Return a combined list where <code>required</code> is always the first element in the list
	 */
	public static final <T> List<T> combine(T required, List<T> optional) {
		Assert.notNull(required);
		if (optional == null) {
			return Collections.singletonList(required);
		} else {
			List<T> combined = new ArrayList<T>();
			// Always insert required as the first element in the list
			combined.add(required);
			// Add the other elements
			for (T element : optional) {
				combined.add(element);
			}
			return combined;
		}
	}

	/**
	 * If <code>o==null</code> return an empty list otherwise return a singleton list.
	 */
	public static final <T> List<T> toEmptyList(T o) {
		if (o == null) {
			return Collections.<T> emptyList();
		} else {
			return Collections.singletonList(o);
		}
	}

	/**
	 * If <code>list==null</code> return an empty list otherwise return <code>list</code>
	 */
	public static final <T> List<T> toEmptyList(List<T> list) {
		if (list == null) {
			return Collections.<T> emptyList();
		} else {
			return list;
		}
	}

	public static final <T> List<T> toNullIfEmpty(List<T> list) {
		if (isEmpty(list)) {
			return null;
		} else {
			return list;
		}
	}

	public static final <T> Collection<T> toNullIfEmpty(Collection<T> c) {
		if (isEmpty(c)) {
			return null;
		} else {
			return c;
		}
	}

	public static final <T> List<T> getPreFilledList(int size, T value) {
		if (value == null || size < 1) {
			return Collections.<T> emptyList();
		} else {
			List<T> list = new ArrayList<T>(size);
			for (int i = 0; i < size; i++) {
				list.add(value);
			}
			return list;
		}
	}

	public static final String getSpaceSeparatedString(List<?> list) {
		list = toEmptyList(list);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			if (i != 0) {
				sb.append(" ");
			}
			sb.append(list.get(i).toString());
		}
		return sb.toString();
	}

	public static final Object[] toObjectArray(List<Object> objects) {
		return objects.toArray(new Object[objects.size()]);
	}

	public static final String[] toStringArray(List<String> strings) {
		return strings.toArray(new String[strings.size()]);
	}

	public static final boolean isEmpty(Collection<?> c) {
		return c == null || c.size() == 0;
	}

	public static final List<String> sortedMerge(List<String> list, String csv) {
		Set<String> set = new TreeSet<String>();
		for (String string : toEmptyList(list)) {
			set.add(string);
		}
		for (String string : getTrimmedListFromCSV(csv)) {
			set.add(string);
		}
		return new ArrayList<String>(set);
	}

	public static final List<String> getTrimmedListFromCSV(String csv) {
		if (StringUtils.isBlank(csv)) {
			return Collections.<String> emptyList();
		}
		List<String> list = new ArrayList<String>();
		String[] tokens = Str.splitAndTrimCSV(csv);
		list.addAll(Arrays.asList(tokens));
		return list;
	}

	public static final List<String> combineStrings(List<String> list1, List<String> list2, List<String> list3) {
		List<String> combined = new ArrayList<String>();
		nullSafeAdd(combined, list1);
		nullSafeAdd(combined, list2);
		nullSafeAdd(combined, list3);
		return combined;
	}

	/**
	 * Return a new list containing all of the strings from both lists with string added in between the strings from both lists
	 */
	public static final List<String> combineStrings(List<String> list1, String string, List<String> list2) {
		return combineStrings(list1, toEmptyList(string), list2);
	}

	/**
	 * Return a new list containing all of the strings from both lists
	 */
	public static final List<String> combineStrings(List<String> list1, List<String> list2) {
		return combineStrings(list1, (String) null, list2);
	}

	public static final <T> void nullSafeAdd(List<T> list1, List<T> list2) {
		if (list2 != null) {
			list1.addAll(list2);
		}
	}
}
