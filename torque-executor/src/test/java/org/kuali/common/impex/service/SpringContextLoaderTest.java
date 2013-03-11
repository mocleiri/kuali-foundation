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
package org.kuali.common.impex.service;

import java.util.Properties;

import org.junit.Test;
import org.kuali.common.util.service.DefaultSpringService;
import org.kuali.common.util.service.SpringService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpringContextLoaderTest {

	private static final Logger logger = LoggerFactory.getLogger(SpringContextLoaderTest.class);

	@Test
	public void test() {
		try {
			logger.debug("");

			Properties p = new Properties();
			p.setProperty("kuali.groupId", "org.kuali");
			p.setProperty("kuali.encoding", "UTF-8");
			p.setProperty("project.groupId", "org.kuali.student.web");
			p.setProperty("project.artifactId", "ks-with-rice-bundled");
			p.setProperty("project.version", "2.0.0-SNAPSHOT");
			p.setProperty("project.classifier", "");
			SpringService ss = new DefaultSpringService();
			// System.setProperty("jdbc.data.skip", "true");
			ss.load("classpath:org/kuali/student/ks-reset-context.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
