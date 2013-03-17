/**
 * Copyright 2010-2012 The Kuali Foundation
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

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProjectUtilsTest {

	private static final Logger logger = LoggerFactory.getLogger(ProjectUtilsTest.class);

	@Test
	public void testGav() {
		logger.debug("");
		String gav = "org.kuali.common:kuali-car";
		Project p = ProjectUtils.getProject(gav);
		Assert.hasText(p.getGroupId());
		Assert.hasText(p.getArtifactId());
	}

	@Test
	public void testLoad() {
		String gav = "org.kuali.common:kuali-car";
		Project p = ProjectUtils.loadProject(gav);
		Assert.hasText(p.getGroupId());
		Assert.hasText(p.getArtifactId());
	}
}
