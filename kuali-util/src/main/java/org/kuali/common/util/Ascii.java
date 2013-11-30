package org.kuali.common.util;

public class Ascii {

	public static boolean isLetter(char c) {
		return isLowerCase(c) || isUpperCase(c);
	}

	public static boolean isNumber(char c) {
		return c >= '0' && c <= '9';
	}

	public static boolean isLowerCase(char c) {
		return c >= 'a' && c <= 'z';
	}

	public static boolean isUpperCase(char c) {
		return c >= 'A' && c <= 'Z';
	}

}
