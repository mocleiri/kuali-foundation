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
package org.kuali.common.util;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

public class CollectionUtils {

	/**
	 * Remove any Strings from the list that do not match the filter and then sort the ones that remain
	 */
	public static void filterAndSort(List<String> strings, StringFilter filter) {
		filter(strings, filter);
		Collections.sort(strings);
	}

	/**
	 * Remove any Strings from the collection that do not match the filter
	 */
	public static void filter(Collection<String> strings, StringFilter filter) {
		Iterator<String> itr = strings.iterator();
		while (itr.hasNext()) {
			if (!filter.include(itr.next())) {
				itr.remove();
			}
		}
	}

	/**
	 * Null safe method for converting an array of objects into a list. Never returns null.
	 */
	public static List<Object> asList(Object... objects) {
		List<Object> list = new ArrayList<Object>();
		if (objects == null) {
			return list;
		}
		for (Object element : objects) {
			if (element != null) {
				list.add(element);
			}
		}
		return list;
	}

	/**
	 * Null safe method for converting an untyped array of classes into a list. Never returns null.
	 */
	public static List<Class<?>> asList(Class<?>... classes) {
		List<Class<?>> list = new ArrayList<Class<?>>();
		if (classes == null) {
			return list;
		}
		for (Class<?> element : classes) {
			if (element != null) {
				list.add(element);
			}
		}
		return list;
	}

	/**
	 * Return an array of int's that represents as even of a split as possible
	 * 
	 * For example: passing in 100,7 returns 15, 15, 14, 14, 14, 14, 14
	 * 
	 * @param numerator
	 * @param denominator
	 * @return
	 */
	public static int[] getDivideEvenly(int number, int howManyWays) {
		Assert.isTrue(howManyWays > 0, "howManyWays must be a positive integer");
		int quotient = number / howManyWays;
		int remainder = number % howManyWays;

		int[] lengths = new int[howManyWays];
		for (int i = 0; i < howManyWays; i++) {
			int length = i < remainder ? quotient + 1 : quotient;
			lengths[i] = length;
		}
		return lengths;
	}

	/**
	 * Split <code>elements</code> evenly into separate lists divided up <code>howManyWays</code>
	 */
	public static final <T> List<List<T>> splitEvenly(List<T> elements, int howManyWays) {
		// Can't split 2 things 3 ways
		if (howManyWays > elements.size()) {
			howManyWays = elements.size();
		}
		int[] lengths = getDivideEvenly(elements.size(), howManyWays);
		int offset = 0;
		List<List<T>> listOfLists = new ArrayList<List<T>>();
		for (int i = 0; i < lengths.length; i++) {
			int length = lengths[i];
			List<T> sublist = new ArrayList<T>();
			for (int j = offset; j < offset + length; j++) {
				sublist.add(elements.get(j));
			}
			listOfLists.add(sublist);
			offset += length;
		}
		return listOfLists;
	}

	/**
	 * Prefix the strings passed in with their position in the list (left padded with zero's). The padding is the number of digits in the size of the list. A list with 100 elements
	 * will return strings prefixed with 000, 001, etc.
	 */
	public static final List<String> getSequencedStrings(List<String> strings, int initialSequenceNumber) {
		List<String> sequencedStrings = new ArrayList<String>();
		int size = strings.size();
		int length = new Integer(size).toString().length();
		String prefix = StringUtils.repeat("0", length);
		for (String string : strings) {
			String sequence = StringUtils.right(prefix + (initialSequenceNumber++), length);
			String sequencedString = sequence + "-" + string;
			sequencedStrings.add(sequencedString);
		}
		return sequencedStrings;
	}

	/**
	 * Prefix the strings passed in with their position in the list (left padded with zero's). The padding is the number of digits in the size of the list. A list with 100 elements
	 * will return strings prefixed with 000, 001, etc.
	 */
	public static final List<String> getSequencedStrings(List<String> strings) {
		return getSequencedStrings(strings, 0);
	}

	/**
	 * Return a new <code>List</code> containing the unique set of strings from <code>strings</code>
	 */
	public static final List<String> getUniqueStrings(List<String> strings) {
		List<String> unique = new ArrayList<String>();
		for (String string : strings) {
			if (!unique.contains(string)) {
				unique.add(string);
			}
		}
		return unique;
	}

	public static final List<File> getUniqueFiles(List<File> files) {
		List<String> strings = new ArrayList<String>();
		for (File file : files) {
			strings.add(LocationUtils.getCanonicalPath(file));
		}
		List<String> uniqueStrings = getUniqueStrings(strings);
		List<File> uniqueFiles = new ArrayList<File>();
		for (String uniqueString : uniqueStrings) {
			uniqueFiles.add(new File(uniqueString));
		}
		return uniqueFiles;
	}

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
		List<String> newList = getUniqueStrings(list1);
		for (String element : list2) {
			if (!newList.contains(element)) {
				newList.add(element);
			}
		}
		return newList;
	}

	protected static final <T> T getNewInstance(Class<T> c) {
		try {
			return c.newInstance();
		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException(e);
		} catch (InstantiationException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * Create a new list containing new instances of <code>c</code>
	 */
	public static final <T> List<T> getNewList(Class<T> c, int size) {
		List<T> list = new ArrayList<T>();
		for (int i = 0; i < size; i++) {
			T element = getNewInstance(c);
			list.add(element);
		}
		return list;
	}

	/**
	 * Return a list containing only the elements where the corresponding index in the <code>includes</code> list is <code>true</code>. <code>includes</code> and <code>list</code>
	 * must be the same size.
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
	 * Combine the list of lists into a single list
	 */
	public static final <T> List<T> combineLists(List<List<T>> listOfLists) {
		List<T> combined = new ArrayList<T>();
		for (List<T> list : listOfLists) {
			combined.addAll(list);
		}
		return combined;
	}

	/**
	 * Combine the list of maps into a single map
	 */
	public static final <K, V> Map<K, V> combineMaps(List<Map<K, V>> listOfMaps) {
		Map<K, V> combined = new HashMap<K, V>();
		for (Map<K, V> map : listOfMaps) {
			combined.putAll(map);
		}
		return combined;
	}

	/**
	 * Return a combined list where <code>required</code> is always the first element in the list
	 */
	public static final <T> List<T> combine(T element, List<T> list) {
		Assert.notNull(element, "element is required");
		if (list == null) {
			return Collections.singletonList(element);
		} else {
			List<T> combined = new ArrayList<T>();
			// Always insert required as the first element in the list
			combined.add(element);
			// Add the other elements
			for (T optional : list) {
				combined.add(optional);
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

	public static final String getCSV(List<String> strings) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < toEmptyList(strings).size(); i++) {
			if (i != 0) {
				sb.append(",");
			}
			sb.append(strings.get(i));
		}
		return sb.toString();
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
		set.addAll(toEmptyList(list));
		set.addAll(getTrimmedListFromCSV(csv));
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

	/**
	 * Return <code>true</code> if <code>s</code> contains any of the strings from <code>strings</code>
	 */
	public static final boolean containsAny(String s, List<String> strings) {
		for (String string : strings) {
			if (StringUtils.contains(s, string)) {
				return true;
			}
		}
		return false;
	}
}
