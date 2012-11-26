package org.kuali.common.util;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

public class HexUtilsTest {

	@Test
	public void testRoundTripSimple() throws IOException {
		String encoding = "UTF-8";
		String oldString = "123";
		String hex = HexUtils.toHexString(oldString, encoding);
		System.out.println("hex=" + hex);
		String newString = HexUtils.toStringFromHex(hex, encoding);
		System.out.println("oldString=" + oldString + " newString=" + newString);
		Assert.assertEquals(oldString, newString);
	}

	@Test
	public void testRoundTrip() throws IOException {
		String encoding = "UTF-8";
		String oldString = "𝟙𝟚𝟛";
		String hex = HexUtils.toHexString(oldString, encoding);
		System.out.println("hex=" + hex);
		String newString = HexUtils.toStringFromHex(hex, encoding);
		System.out.println("oldString=" + oldString + " newString=" + newString);
		Assert.assertEquals(oldString, newString);
	}

}
