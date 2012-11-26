package org.kuali.common.util;

import java.nio.charset.Charset;

import org.apache.commons.lang3.StringUtils;

public class HexUtils {

	public static final String ZERO = "0";

	public static String toHex(String s, String charsetName) {
		return toHex(s, Charset.forName(charsetName));
	}

	public static String toHex(String s, Charset charSet) {
		if (s == null) {
			return null;
		}
		int byteMask = 0x000000ff;
		byte[] bytes = s.getBytes(charSet);
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
