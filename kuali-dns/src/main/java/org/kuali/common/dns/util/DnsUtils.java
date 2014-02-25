package org.kuali.common.dns.util;

import static com.google.common.base.Preconditions.checkArgument;
import static org.kuali.common.util.base.Precondition.checkNotBlank;

import java.util.List;

import org.kuali.common.util.Ascii;

import com.google.common.base.Splitter;

public class DnsUtils {

	private static final char DOT = '.';
	private static final char HYPHEN = '-';
	private static final int MAX_FQDN_LENGTH = 253;
	private static final int MAX_LABEL_LENGTH = 63;
	private static final Splitter SPLITTER = Splitter.on(DOT);

	/**
	 * <p>
	 * Verify <code>fqdn</code> is a syntactically valid DNS name.
	 * </p>
	 * 
	 * <p>
	 * See http://en.wikipedia.org/wiki/Domain_Name_System#Domain_name_syntax
	 * </p>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>fqdn</code> is not a syntactically valid DNS name.
	 */
	public static void validateFQDN(String fqdn) {

		// Null, the empty string, and pure whitespace are not allowed
		checkNotBlank(fqdn, "fqdn");

		// Max length is 253 characters
		checkArgument(fqdn.length() <= MAX_FQDN_LENGTH, "[%s] is %s characters long.  Max is %s", fqdn, fqdn.length(), MAX_FQDN_LENGTH);

		// Split up the string using dot as a separator
		List<String> labels = SPLITTER.splitToList(fqdn);

		// Validate each portion of the dns name
		for (String label : labels) {
			validateLabel(label);
		}
	}

	protected static void validateLabel(String label) {

		// Null, the empty string, and pure whitespace are not allowed
		checkNotBlank(label, "label");

		// Max length for an individual label is 63 characters
		checkArgument(label.length() <= MAX_LABEL_LENGTH, "[%s] is %s characters long.  Max is %s", label, label.length(), MAX_LABEL_LENGTH);

		// Can't begin or end with a hyphen
		checkArgument(label.charAt(0) != HYPHEN, "[%s] begins with %s", label, HYPHEN);
		checkArgument(label.charAt(label.length() - 1) != HYPHEN, "[%s] ends with %s", label, HYPHEN);

		// Only characters allowed are a..z, A..Z, 0..9, and the hyphen
		checkArgument(isLetterDigitHyphen(label), "Only a..z, A..Z, 0..9, and the hyphen character are allowed");
	}

	protected static boolean isLetterDigitHyphen(String label) {
		char[] chars = label.toCharArray();
		for (char c : chars) {
			if (!isLetterDigitHyphen(c)) {
				return false;
			}
		}
		return true;
	}

	protected static boolean isLetterDigitHyphen(char c) {
		return Ascii.isLetter(c) || c == HYPHEN || Ascii.isDigit(c);
	}

}
