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
