package org.kuali.common.util;


public class ArrayUtils {

	public static Class<?>[] combine(Class<?> first, Class<?>[] rest) {
		Assert.noNulls(first, rest);
		Class<?>[] newArray = new Class<?>[rest.length + 1];
		newArray[0] = first;
		System.arraycopy(rest, 0, newArray, 1, rest.length);
		return newArray;
	}

}
