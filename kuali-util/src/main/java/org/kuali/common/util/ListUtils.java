package org.kuali.common.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class ListUtils {

	public static <T> List<T> newArrayList() {
		return newArrayList(new ArrayList<T>());
	}

	public static <T> List<T> newArrayList(T element) {
		Assert.noNulls(element);
		List<T> list = newArrayList();
		list.add(element);
		return list;
	}

	public static <T> List<T> newArrayList(List<T> list) {
		return newArrayList(list, false);
	}

	public static <T> List<T> newImmutableArrayList(List<T> list) {
		return newArrayList(list, true);
	}

	public static <T> List<T> newArrayList(List<T> list, boolean immutable) {
		Assert.noNulls(list);
		if (immutable) {
			return Collections.unmodifiableList(new ArrayList<T>(list));
		} else {
			return new ArrayList<T>(list);
		}
	}

	/**
	 * This method guarantees 4 things:<br>
	 * 
	 * 1 - The <code>list</code> is not null.<br>
	 * 2 - The <code>list</code> is not empty. (size() > 0)<br>
	 * 3 - The <code>list</code> does not contain <code>null</code>.<br>
	 * 4 - Every element in the <code>list</code> is the exact same runtime type.<br>
	 */
	public static void assertUniformRuntimeType(List<?> list) {
		Assert.noNulls(list);
		Assert.isTrue(list.size() > 0, "list is empty");
		Assert.isFalse(list.contains(null), "list contains null");
		Class<?> previous = list.get(0).getClass();
		for (int i = 1; i < list.size(); i++) {
			Class<?> current = list.get(i).getClass();
			Assert.isTrue(current == previous, "non-uniform runtime types at index " + i);
			previous = current;
		}
	}

	public static boolean equals(List<String> one, List<String> two) {
		return equals(one, two, false);
	}

	public static boolean equalsIgnoreCase(List<String> one, List<String> two) {
		return equals(one, two, true);
	}

	protected static boolean equals(List<String> one, List<String> two, boolean ignoreCase) {

		// Nulls not allowed
		Assert.noNulls(one, two);

		// If the sizes are different they are not equal
		if (one.size() != two.size()) {
			return false;
		}

		// The sizes are the same, just pick one
		int size = one.size();

		// Iterate over both lists comparing each value for equality
		for (int i = 0; i < size; i++) {
			if (!equal(one.get(i), two.get(i), ignoreCase)) {
				return false;
			}
		}

		// All values in both lists match
		return true;
	}

	protected static boolean equal(String one, String two, boolean ignoreCase) {
		if (ignoreCase) {
			return StringUtils.equalsIgnoreCase(one, two);
		} else {
			return StringUtils.equals(one, two);
		}
	}

}
