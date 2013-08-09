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

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.junit.Ignore;
import org.junit.Test;

@Deprecated
public class DefaultProjectConfigServiceTest {

	@Test
	public void testIt() {

		try {
			String groupId = "org.kuali.common";
			String artifactId = "kuali-util";
			String contextId = "scm";
			String configId = org.kuali.common.util.config.ConfigUtils.getConfigId(groupId, artifactId, contextId);
			org.kuali.common.util.Project project = org.kuali.common.util.ProjectUtils.loadProject(groupId, artifactId);
			ConfigService service = new DefaultConfigService();
			Properties properties = service.getProperties(configId, project.getProperties());
			System.out.println("scm.service=" + properties.getProperty("scm.service"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Ignore
	public void test() {

		try {
			org.kuali.common.util.config.ContextConfig mpx = new org.kuali.common.util.config.ContextConfig("mpx", Arrays.asList(new org.kuali.common.util.config.Location("${metainf.common}/mpx.properties")));
			org.kuali.common.util.config.ContextConfig sql = new org.kuali.common.util.config.ContextConfig("sql", Arrays.asList(new org.kuali.common.util.config.Location("${metainf.common}/sql.properties")));
			org.kuali.common.util.config.ContextConfig metainf = new org.kuali.common.util.config.ContextConfig("metainf");
			metainf.setContexts(Arrays.asList(mpx, sql));
			List<org.kuali.common.util.config.ContextConfig> contexts = new ArrayList<org.kuali.common.util.config.ContextConfig>();
			contexts.add(metainf);

			List<org.kuali.common.util.config.Location> locations = new ArrayList<org.kuali.common.util.config.Location>();
			locations.add(new org.kuali.common.util.config.Location("${classpath.prefix}/sql.xml"));
			org.kuali.common.util.config.ProjectConfigContainer config = new org.kuali.common.util.config.ProjectConfigContainer();
			config.setGroupId("org.kuali.common");
			config.setArtifactId("kuali-util");
			config.setLocations(locations);
			config.setContexts(contexts);
			DefaultConfigService service = new DefaultConfigService();
			File file = new File("/tmp/metadata.xml");
			service.store(file, config);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
