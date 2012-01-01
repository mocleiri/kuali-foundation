/**
 * Copyright 2004-2012 The Kuali Foundation
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
