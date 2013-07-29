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
package org.kuali.common.util.project.spring;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.common.util.project.KualiUtilProjectConstants;
import org.kuali.common.util.project.Project;
import org.kuali.common.util.project.ProjectIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = KualiUtilProjectConfig.class)
public class AutomaticProjectConfigTest {

	private static final Logger logger = LoggerFactory.getLogger(AutomaticProjectConfigTest.class);

	protected static final ProjectIdentifier ID = KualiUtilProjectConstants.PROJECT_IDENTIFIER;

	@Autowired
	Project project;

	@Test
	public void test() {
		logger.info("Constants: [" + ID.getGroupId() + ":" + ID.getArtifactId() + "]");
		logger.info("   Loaded: [" + project.getGroupId() + ":" + project.getArtifactId() + "]");
		Assert.assertEquals(ID.getGroupId(), project.getGroupId());
		Assert.assertEquals(ID.getArtifactId(), project.getArtifactId());
		// PropertyUtils.info(project.getProperties());
	}

}
