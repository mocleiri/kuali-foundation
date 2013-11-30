package org.kuali.common.util;

public class Ascii {

	private static final int LETTER_OFFSET = 13;
	private static final int NUMBER_OFFSET = 5;
	private static final char NUMBER_MIDPOINT = '4';
	private static final char LCASE_MIDPOINT = 'm';
	private static final char UCASE_MIDPOINT = 'M';

	/**
	 * Return true if the character is in the range A-Z or a-z
	 */
	public static boolean isLetter(char c) {
		return isLowerCase(c) || isUpperCase(c);
	}

	/**
	 * Return true if the character is in the range 0-9
	 */
	public static boolean isDigit(char c) {
		return c >= '0' && c <= '9';
	}

	/**
	 * Return true if the character is in the range a-z
	 */
	public static boolean isLowerCase(char c) {
		return c >= 'a' && c <= 'z';
	}

	/**
	 * Return true if the character is in the range A-Z
	 */
	public static boolean isUpperCase(char c) {
		return c >= 'A' && c <= 'Z';
	}

	/**
	 * <p>
	 * If the character is a letter or digit apply the flip algorithm to it, otherwise leave it alone.
	 * </p>
	 * 
	 * The flip algorithm makes the character in the top row become the character in the bottom row, and vice versa.
	 * 
	 * <pre>
	 *  01234 abcdefghijklm ABCDEFGHIJKLM
	 *  56789 nopqrstuvwxyz NOPQRSTUVWXYZ
	 * </pre>
	 */
	public static char flip(char c) {
		if (isLowerCase(c)) {
			if (c > LCASE_MIDPOINT) {
				return (char) ((int) c - LETTER_OFFSET);
			} else {
				return (char) ((int) c + LETTER_OFFSET);
			}
		} else if (isUpperCase(c)) {
			if (c > UCASE_MIDPOINT) {
				return (char) ((int) c - LETTER_OFFSET);
			} else {
				return (char) ((int) c + LETTER_OFFSET);
			}
		} else if (isDigit(c)) {
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
