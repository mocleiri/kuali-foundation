package org.kuali.common.util;

public class Ascii {

	private static final int LETTER_OFFSET = 13;
	private static final int NUMBER_OFFSET = 5;
	private static final char NUMBER_MIDPOINT = '4';
	private static final char LCASE_MIDPOINT = 'm';
	private static final char UCASE_MIDPOINT = 'M';

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

	public static char flip(char c) {
		if (Ascii.isLowerCase(c)) {
			if (c > LCASE_MIDPOINT) {
				return (char) ((int) c - LETTER_OFFSET);
			} else {
				return (char) ((int) c + LETTER_OFFSET);
			}
		} else if (Ascii.isUpperCase(c)) {
			if (c > UCASE_MIDPOINT) {
				return (char) ((int) c - LETTER_OFFSET);
			} else {
				return (char) ((int) c + LETTER_OFFSET);
			}
		} else if (Ascii.isNumber(c)) {
			if (c > NUMBER_MIDPOINT) {
				return (char) ((int) c - NUMBER_OFFSET);
			} else {
				return (char) ((int) c + NUMBER_OFFSET);
			}
		} else {
			return c;
		}
	}

}
