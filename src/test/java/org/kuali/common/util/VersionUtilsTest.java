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

import org.junit.Assert;
import org.junit.Test;

public class VersionUtilsTest {

	@Test
	public void testIsSnapshot() {
		Assert.assertTrue(VersionUtils.isSnapshot("1.0.0-SNAPSHOT"));
		Assert.assertTrue(VersionUtils.isSnapshot("1.0-SNAPSHOT"));
		Assert.assertTrue(VersionUtils.isSnapshot("1.0.0-sNAPSHOt"));
		Assert.assertTrue(VersionUtils.isSnapshot("1.0.0-snapshot"));
		Assert.assertFalse(VersionUtils.isSnapshot("1.0.0SNAPSHOT"));
		Assert.assertFalse(VersionUtils.isSnapshot("SNAPSHOT"));
	}

	@Test
	public void testTrimSnapshot() {
		Assert.assertEquals("1.0.0", VersionUtils.trimSnapshot("1.0.0-SNAPSHOT"));
		Assert.assertEquals("1.0.0", VersionUtils.trimSnapshot("1.0.0"));
		Assert.assertEquals("1.0.0-SNAPSHO", VersionUtils.trimSnapshot("1.0.0-SNAPSHO"));
	}

	@Test
	public void testGetVersionNormal() {
		Version version = VersionUtils.getVersion("1.0.0-SNAPSHOT");
		Assert.assertTrue(version.isSnapshot());
		Assert.assertEquals("1", version.getMajor());
		Assert.assertEquals("0", version.getMinor());
		Assert.assertEquals("0", version.getIncremental());
		Assert.assertEquals("1.0.0", version.getTrimmed());
		Assert.assertNull(version.getQualifier());
	}

	@Test
	public void testGetVersion2Digits() {
		Version version = VersionUtils.getVersion("1.0-SNAPSHOT");
		Assert.assertTrue(version.isSnapshot());
		Assert.assertEquals("1", version.getMajor());
		Assert.assertEquals("0", version.getMinor());
		Assert.assertNull(version.getIncremental());
		Assert.assertEquals("1.0", version.getTrimmed());
		Assert.assertNull(version.getQualifier());
	}

	@Test
	public void testGetVersion1Digit() {
		Version version = VersionUtils.getVersion("1-SNAPSHOT");
		Assert.assertTrue(version.isSnapshot());
		Assert.assertEquals("1", version.getMajor());
		Assert.assertNull(version.getMinor());
		Assert.assertNull(version.getIncremental());
		Assert.assertEquals("1", version.getTrimmed());
		Assert.assertNull(version.getQualifier());
	}

	@Test
	public void testGetVersionSpring() {
		Version version = VersionUtils.getVersion("3.1.3.RELEASE");
		Assert.assertFalse(version.isSnapshot());
		Assert.assertEquals("3", version.getMajor());
		Assert.assertEquals("1", version.getMinor());
		Assert.assertEquals("3", version.getIncremental());
		Assert.assertEquals("3.1.3.RELEASE", version.getTrimmed());
		Assert.assertEquals("RELEASE", version.getQualifier());
	}

	@Test
	public void testGetVersionWithQualifier() {
		Version version = VersionUtils.getVersion("1.0.0-M4-SNAPSHOT");
		Assert.assertTrue(version.isSnapshot());
		Assert.assertEquals("1", version.getMajor());
		Assert.assertEquals("0", version.getMinor());
		Assert.assertEquals("0", version.getIncremental());
		Assert.assertEquals("1.0.0-M4", version.getTrimmed());
		Assert.assertEquals("M4", version.getQualifier());
	}

	@Test
	public void testGetVersionWithDoubleQualifier() {
		Version version = VersionUtils.getVersion("1.0.0-beta-1-SNAPSHOT");
		Assert.assertTrue(version.isSnapshot());
		Assert.assertEquals("1", version.getMajor());
		Assert.assertEquals("0", version.getMinor());
		Assert.assertEquals("0", version.getIncremental());
		Assert.assertEquals("1.0.0-beta-1", version.getTrimmed());
		Assert.assertEquals("beta-1", version.getQualifier());
	}

}
