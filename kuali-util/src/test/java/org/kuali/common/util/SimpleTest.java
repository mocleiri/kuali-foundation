package org.kuali.common.util;

import java.nio.charset.Charset;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class SimpleTest {

	@Test
	public void testFileEncoding() {
		System.out.println("file.encoding=" + System.getProperty("file.encoding"));
		System.out.println("Charset.defaultCharset().name()=" + Charset.defaultCharset().name());
	}

	@Test
	public void testEncoding() {
		String[] encodings = new String[] { "UTF-8", "UTF-16", "UTF-32" };
		String s1 = "123";
		String s2 = "𝟙𝟚𝟛";
		String s = s1 + s2;
		StringBuilder sb = new StringBuilder();
		for (String encoding : encodings) {
			byte[] bytes = s.getBytes(Charset.forName(encoding));
			char[] chars = s.toCharArray();
			int[] codePoints = getCodePoints(chars);
			sb.append(rpad("s.length()=" + s.length(), 15));
			sb.append(rpad("encoding=" + encoding, 17));
			sb.append(rpad("bytes=" + bytes.length, 10));
			sb.append(rpad("chars=" + chars.length, 10));
			sb.append(rpad(getHex(bytes), 53));
			sb.append(rpad(s, 12));
			sb.append(getString(codePoints));
			sb.append(s.substring(6, 8));
			sb.append("\n");
		}
		System.out.println("\n" + sb);
	}

	protected String getString(int[] codePoints) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < codePoints.length; i++) {
			if (i != 0) {
				sb.append(",");
			}
			sb.append(codePoints[i]);
		}
		sb.append("]");
		return sb.toString();
	}

	protected int[] getCodePoints(char[] chars) {
		int[] codePoints = new int[chars.length];
		for (int i = 0; i < chars.length; i++) {
			int codePoint = Character.codePointAt(chars, i);
			codePoints[i] = codePoint;
		}
		return codePoints;
	}

	protected String getHex(byte[] bytes) {
		int mask = 0x000000ff;
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			int masked = mask & b;
			String hex = Integer.toHexString(masked).toUpperCase();
			String padded = StringUtils.leftPad(hex, 2, "0");
			sb.append(padded);
		}
		return sb.toString();
	}

	protected String rpad(String s, int padding) {
		return StringUtils.rightPad(s, padding, " ");
	}

}
