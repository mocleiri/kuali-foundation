package org.kuali.common.util;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class ListUtils {

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

		// Every single value in both lists were the same ignoring case
		return true;
	}

}
