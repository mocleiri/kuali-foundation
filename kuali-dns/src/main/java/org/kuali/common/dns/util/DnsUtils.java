package org.kuali.common.dns.util;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Assert;

public class DnsUtils {

	private static final String DOT = ".";
	private static final char HYPHEN = '.';
	private static final int MAX_FQDN_LENGTH = 253;
	private static final int MAX_LABEL_LENGTH = 63;

	/**
	 * Verify that <code>fqdn</code> is a syntactically valid DNS name. See http://en.wikipedia.org/wiki/Domain_Name_System#Domain_name_syntax
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>fqdn</code> is not a a syntactically valid DNS name.
	 */
	public static void validateFQDN(String fqdn) {
		// Null, the empty string, or all whitespace are not valid DNS names
		Assert.noBlanks(fqdn);

		// Max length is 253 characters
		Assert.isTrue(fqdn.length() <= MAX_FQDN_LENGTH, "[" + fqdn + "] is " + fqdn.length() + " characters long.  Max is " + MAX_LABEL_LENGTH);

		// Split up the string using dot as a separator
		String[] labels = StringUtils.splitPreserveAllTokens(fqdn, DOT);
		for (String label : labels) {

			// Can't have a dot followed immediately by another dot
			Assert.noBlanks(label);

			// Max length for an individual label is 63 characters
			int len = label.length();
			Assert.isTrue(len <= MAX_LABEL_LENGTH, "[" + label + "] is " + len + " characters long.  Max is " + MAX_FQDN_LENGTH);

			// Can't begin or end with a hyphen
			Assert.isFalse(label.charAt(0) == HYPHEN, "[" + label + "] begins with " + HYPHEN);
			Assert.isFalse(label.charAt(len - 1) == HYPHEN, "[" + label + "] begins with " + HYPHEN);

			// Only characters allowed are a..z, A..Z, 0..9, and the hyphen
			boolean ldh = containsOnlyLettersDigitsOrHyphen(label);
			Assert.isTrue(ldh, "Only a..z, A..Z, 0..9, and the hyphen character are allowed");
		}
	}

	protected static boolean containsOnlyLettersDigitsOrHyphen(String label) {
		char[] chars = label.toCharArray();
		for (char c : chars) {
			if (!isLetterDigitHyphen(c)) {
				return false;
			}
		}
		return true;
	}

	protected static boolean isLetterDigitHyphen(char c) {
		return isLetter(c) || c == HYPHEN || isDigit(c);
	}

	protected static boolean isDigit(char c) {
		return c >= '0' && c <= '9';
	}

	protected static boolean isLetter(char c) {
		return isLowerCaseLetter(c) || isUpperCaseLetter(c);
	}

	protected static boolean isLowerCaseLetter(char c) {
		return c >= 'a' && c <= 'z';
	}

	protected static boolean isUpperCaseLetter(char c) {
		return c >= 'A' && c <= 'Z';
	}
}
