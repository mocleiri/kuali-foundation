package org.kuali.maven.common;

import junit.framework.Assert;

import org.junit.Test;

public class ExtractorTest {
	Extractor extractor = new Extractor();

	@Test
	public void testGetMajorVersion() {
		String version = "1.0.0";
		String majorVersion = extractor.getMajorVersion(version);
		Assert.assertEquals("1.0", majorVersion);
	}

}
