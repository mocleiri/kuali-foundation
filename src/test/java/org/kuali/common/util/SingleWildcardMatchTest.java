/**
 * Copyright 2010-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.common.util;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import junit.framework.Assert;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.kuali.common.util.property.Constants;
import org.springframework.util.PropertyPlaceholderHelper;

public class SingleWildcardMatchTest {
	@Test
	public void testWildcards() {
		Assert.assertTrue(PropertyUtils.isSingleWildcardMatch((String) null, (String) null));
		Assert.assertFalse(PropertyUtils.isSingleWildcardMatch(null, ""));
		Assert.assertFalse(PropertyUtils.isSingleWildcardMatch("", (String) null));
		Assert.assertFalse(PropertyUtils.isSingleWildcardMatch(null, "*"));
		Assert.assertTrue(PropertyUtils.isSingleWildcardMatch("", "*"));
		Assert.assertFalse(PropertyUtils.isSingleWildcardMatch("*", ""));
		Assert.assertTrue(PropertyUtils.isSingleWildcardMatch("*", "*"));
	}

	@Test
	public void testWildcards1() {
		List<String> includes = Arrays.asList("*.home");
		Properties properties = PropertyUtils.duplicate(System.getProperties());
		PropertyUtils.trim(properties, includes, null);
		PropertyUtils.info(properties);
	}

	@Test
	public void testWildcards2() {
		List<String> includes = Arrays.asList("java.*.version");
		Properties properties = PropertyUtils.duplicate(System.getProperties());
		PropertyUtils.trim(properties, includes, null);
		PropertyUtils.info(properties);
	}

	@Test
	public void testUserHomeWildcard() {
		List<String> includes = Arrays.asList("user.*.home");
		Properties properties = PropertyUtils.duplicate(System.getProperties());
		PropertyUtils.trim(properties, includes, null);
		int size = properties.size();
		Assert.assertEquals(1, size);
	}

	@Test
	public void testUserHome() {
		List<String> includes = Arrays.asList("user.home");
		Properties properties = PropertyUtils.duplicate(System.getProperties());
		PropertyUtils.trim(properties, includes, null);
		int size = properties.size();
		Assert.assertEquals(1, size);
	}

	@Test
	public void testMultipleWildcards() {
		List<String> includes = Arrays.asList("java.*vm*.version");
		Properties properties = PropertyUtils.duplicate(System.getProperties());
		try {
			PropertyUtils.trim(properties, includes, null);
			Assert.fail("Should fail on multiple wildcards");
		} catch (IllegalArgumentException ignored) {
			; // ignore
		}
	}

	@Test
	public void testPropertyKeysContainingWildardsAndNormalSearchIsUsed() {
		List<String> includes = Arrays.asList("user.home");
		Properties properties = PropertyUtils.duplicate(System.getProperties());
		properties.setProperty("user.*.home", "foo");
		// Non-wildcard searches on property keys containing wildcards is ok
		PropertyUtils.trim(properties, includes, null);
		int size = properties.size();
		Assert.assertEquals(1, size);
	}

	// @Test
	public void testPropertyPlaceholderHelper() {
		Properties properties = new Properties();
		// properties.setProperty("foo", "bar");
		PropertyPlaceholderHelper helper = Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER;
		String original = "I went to the \\${foo:mall}";
		String resolved = helper.replacePlaceholders(original, properties);
		System.out.println(resolved);
	}

	// @Test
	public void testDefaultFileEncoding() {
		System.out.println("file.encoding=" + System.getProperty("file.encoding"));
		System.out.println("Charset.defaultCharset().name()=" + Charset.defaultCharset().name());
	}

	// @Test
	public void testEncoding() {
		String[] encodings = new String[] { "UTF-8", "UTF-16", "UTF-32" };
		String s1 = "123";
		String s2 = "𝟙𝟚𝟛";
		String s = s1 + s2;
		StringBuilder sb = new StringBuilder();
		sb.append(rpad("s=" + s, 15));
		sb.append(rpad("s.length()=" + s.length(), 15));
		sb.append(rpad("s.substring(4, 6)=" + s.substring(4, 6), 25));
		sb.append("\n\n");
		StringBuilder characters = new StringBuilder();
		for (String encoding : encodings) {
			byte[] bytes = s.getBytes(Charset.forName(encoding));
			char[] chars = s.toCharArray();
			int[] codePoints = getCodePoints(chars);
			sb.append(rpad("encoding=" + encoding, 20));
			sb.append(rpad("bytes=" + bytes.length, 15));
			sb.append(rpad("chars=" + chars.length, 15));
			sb.append(rpad(HexUtils.toHexString(bytes), 55));
			characters.append(getString(codePoints) + "\n");
			sb.append("\n");
		}
		System.out.println("\n" + sb + "\n" + characters);
	}

	protected String getString(int[] codePoints) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < codePoints.length; i++) {
			if (i != 0) {
				sb.append(",");
			}
			int cp = codePoints[i];
			int charCount = Character.charCount(cp);
			sb.append(cp + ":charCount=" + charCount);
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
