package org.kuali.common.util;

import java.nio.charset.Charset;

import org.apache.commons.lang3.StringUtils;

public class HexUtils {

	private static final String ZERO = "0";
	private static final int BYTE_MASK = 0x000000ff;

	/**
	 * Convert <code>string</code> into a <code>byte[]</code> using the specified character set, then convert each <code>byte</code> into
	 * its 2 digit hexadecimal form.
	 */
	public static String toHexString(String string, String charsetName) {
		if (charsetName == null) {
			return toHexString(string, Charset.defaultCharset());
		} else {
			return toHexString(string, Charset.forName(charsetName));
		}
	}

	/**
	 * Convert <code>string</code> into a <code>byte[]</code> using the specified character set, then convert each <code>byte</code> into
	 * its 2 digit hexadecimal form.
	 */
	public static String toHexString(String string, Charset charset) {
		byte[] bytes = charset == null ? string.getBytes() : string.getBytes(charset);
		return toHexString(bytes);
	}

	/**
	 * Convert each <code>byte</code> into its 2 digit hexadecimal form.
	 */
	public static String toHexString(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			int masked = BYTE_MASK & b;
			String hex = Integer.toHexString(masked).toUpperCase();
			String padded = StringUtils.leftPad(hex, 2, ZERO);
			sb.append(padded);
		}
		return sb.toString();
	}

}
