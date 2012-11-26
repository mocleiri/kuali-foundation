package org.kuali.common.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;

/**
 * A few (highly inefficient) methods for flipping Java <code>String's</code> into their hexadecimal equivalent and back again, given an
 * encoding.
 */
public class HexUtils {

	private static final String ZERO = "0";
	private static final int BYTE_MASK = 0x000000ff;

	/**
	 * Convert <code>string</code> into a <code>byte[]</code> using the specified encoding, then convert each <code>byte</code> into its 2
	 * digit hexadecimal form.
	 */
	public static String toHexString(String string, String encoding) throws UnsupportedEncodingException {
		byte[] bytes = encoding == null ? string.getBytes() : string.getBytes(encoding);
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

	/**
	 * Given a string in <code>strictly hex</code> format, return the corresponding <code>byte[]</code>. <code>strictly hex</code> in the
	 * context of this method means that the string:<br>
	 * 1 - Contains only the characters <code>a-f</code>, <code>A-F</code>, and <code>0-9</code><br>
	 * 2 - Its length is an even number.
	 */
	public static final byte[] getBytesFromHexString(String hex) {
		char[] chars = hex.toCharArray();
		int length = chars.length;
		if (length % 2 != 0) {
			throw new IllegalArgumentException("Invalid hex string [" + hex + "]");
		}
		byte[] bytes = new byte[length / 2];
		int byteIndex = 0;
		for (int i = 0; i < length; i += 2) {
			char c1 = chars[i];
			char c2 = chars[i + 1];
			String s = c1 + "" + c2;
			int integer = Integer.parseInt(s, 16);
			int masked = integer & BYTE_MASK;
			byte b = (byte) masked;
			bytes[byteIndex++] = b;
		}
		return bytes;
	}

	/**
	 * Given a string in <code>strictly hex</code> format and the <code>encoding</code> that was used to produce the hex, convert it back to
	 * a Java <code>String</code>.<code>strictly hex</code> in the context of this method means that the string:<br>
	 * 1 - Contains only the characters <code>a-f</code>, <code>A-F</code>, and <code>0-9</code><br>
	 * 2 - Its length is an even number.
	 */
	public static final String toStringFromHex(String hex, String encoding) throws UnsupportedEncodingException {
		byte[] bytes = getBytesFromHexString(hex);
		return StringUtils.toString(bytes, encoding);
	}

}
