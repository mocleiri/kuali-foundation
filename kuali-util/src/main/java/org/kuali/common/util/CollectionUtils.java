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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

public class CollectionUtils {

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
	public static final <T> List<T> toEmpty(List<T> list) {
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
		for (String string : toEmpty(list)) {
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
}
