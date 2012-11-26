package org.kuali.common.util;

import java.nio.charset.Charset;

import org.apache.commons.lang3.StringUtils;

public class HexUtils {

	public static final String ZERO = "0";

	/**
	 * Convert <code>string</code> into a <code>byte[]</code> using the specified character set, then convert each <code>byte</code> into
	 * its hexadecimal form.
	 */
	public static String toHex(String string, String charsetName) {
		return toHex(string, Charset.forName(charsetName));
	}

	/**
	 * Convert <code>string</code> into a <code>byte[]</code> using the specified character set, then convert each <code>byte</code> into
	 * its hexadecimal form.
	 */
	public static String toHex(String string, Charset charSet) {
		if (string == null) {
			return null;
		}
		byte[] bytes = string.getBytes(charSet);
		return toHex(bytes);
	}

	/**
	 * Convert each <code>byte</code> into its hexadecimal form.
	 */
	public static String toHex(byte[] bytes) {
		int byteMask = 0x000000ff;
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			int masked = byteMask & b;
			String hex = Integer.toHexString(masked).toUpperCase();
			String padded = StringUtils.leftPad(hex, 2, ZERO);
			sb.append(padded);
		}
		return sb.toString();
	}

}
