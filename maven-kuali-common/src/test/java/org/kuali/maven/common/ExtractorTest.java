package org.kuali.maven.common;

import junit.framework.Assert;

import org.junit.Test;

public class ExtractorTest {
	Extractor extractor = new Extractor();

	@Test
	public void test() {
		String version = "1.0.0";
		String majorVersion = extractor.getMajorVersion(version);
		System.out.println(majorVersion);
		Assert.assertEquals("1.0", majorVersion);
	}

}
