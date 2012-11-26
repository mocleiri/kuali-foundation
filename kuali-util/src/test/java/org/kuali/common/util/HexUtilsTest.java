package org.kuali.common.util;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HexUtilsTest {

	private static final Logger logger = LoggerFactory.getLogger(HexUtilsTest.class);

	@Test
	public void testRoundTripSimple() throws IOException {
		String encoding = "UTF-8";
		String oldString = "123";
		String hex = HexUtils.toHexString(oldString, encoding);
		logger.info(encoding + " bytes [" + hex + "]");
		String newString = HexUtils.toStringFromHex(hex, encoding);
		logger.info("oldString=" + oldString + " newString=" + newString);
		Assert.assertEquals(oldString, newString);
	}

	@Test
	public void testRoundTrip() throws IOException {
		String encoding = "UTF-8";
		String oldString = "𝟙𝟚𝟛";
		String hex = HexUtils.toHexString(oldString, encoding);
		logger.info(encoding + " bytes [" + hex + "]");
		String newString = HexUtils.toStringFromHex(hex, encoding);
		logger.info("oldString=" + oldString + " newString=" + newString);
		Assert.assertEquals(oldString, newString);
	}

}
