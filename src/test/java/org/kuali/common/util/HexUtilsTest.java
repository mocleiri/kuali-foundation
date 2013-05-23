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

import java.io.IOException;

import junit.framework.Assert;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HexUtilsTest {

	private static final Logger logger = LoggerFactory.getLogger(HexUtilsTest.class);

	@Test
	public void testInvalidHex() throws IOException {
		try {
			logger.info(HexUtils.toStringFromHex("3", "UTF-8"));
			Assert.fail("Strings with an odd number of characters should fail");
		} catch (IllegalArgumentException e) {
			logger.info(e.getMessage());
		}
		try {
			logger.info(HexUtils.toStringFromHex("FFA019z7AA", "UTF-8"));
			Assert.fail("Strings with characters outside the range 0-9, a-f, and A-F should fail");
		} catch (IllegalArgumentException e) {
			logger.info(e.getMessage());
		}
	}

	@Test
	public void testRoundTripSimple() throws IOException {
		testString("123");
	}

	@Test
	public void testRoundTrip() throws IOException {
		testString("𝟙𝟚𝟛");
	}

	protected void testString(String s) throws IOException {
		String[] encodings = new String[] { "UTF-8", "UTF-16", "UTF-32" };
		String oldString = s;
		for (String encoding : encodings) {
			String hex = HexUtils.toHexString(oldString, encoding);
			logger.info("Converting '" + oldString + "' into hex using " + StringUtils.rightPad(encoding, 7, " ") + "[" + hex + "]");
			String newString = HexUtils.toStringFromHex(hex, encoding);
			logger.debug("originalString=" + oldString + " newString=" + newString);
			Assert.assertEquals(oldString, newString);
		}
	}
}
