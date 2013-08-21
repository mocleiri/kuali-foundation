package org.kuali.common.util;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class ListUtils {

	/**
	 * This method completing successfully guarantees 4 things:<br>
	 * 
	 * 1 - The <code>list</code> is not null.<br>
	 * 2 - The <code>list</code> is not empty. (size() > 0)<br>
	 * 3 - The <code>list</code> does not contain <code>null</code>.<br>
	 * 4 - Every element in <code>list</code> is the exact same runtime type.<br>
	 */
	public static void assertUniformRuntimeType(List<?> list) {
		Assert.noNulls(list);
		Assert.isFalse(CollectionUtils.isEmpty(list), "list is empty");
		Assert.isFalse(list.contains(null), "list contains null");
		Class<?> previous = list.get(0).getClass();
		for (int i = 1; i < list.size(); i++) {
			Class<?> current = list.get(i).getClass();
			Assert.isTrue(current == previous, "non-uniform runtime types near index " + i);
			previous = current;
		}
	}

	public static boolean equalsIgnoreCase(List<String> one, List<String> two) {

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
			if (!StringUtils.equalsIgnoreCase(one.get(i), two.get(i))) {
				return false;
			}
		}

		// All values in both lists are the same ignoring case
		return true;
	}

}
