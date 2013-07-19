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
package org.kuali.common.util.config.service;

import java.util.Properties;

import org.junit.Test;
import org.kuali.common.util.Project;
import org.kuali.common.util.ProjectUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.config.ConfigUtils;

public class SpringConfigServiceTest {

	@Test
	public void testIt() {

		try {
			String groupId = "org.kuali.common";
			String artifactId = "kuali-util";
			String contextId = "scm";
			String configId = ConfigUtils.getConfigId(groupId, artifactId, contextId);
			Project project = ProjectUtils.loadProject(groupId, artifactId);
			ConfigService service = new SpringConfigService();
			Properties properties = service.getProperties(configId, project.getProperties());
			PropertyUtils.info(properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
