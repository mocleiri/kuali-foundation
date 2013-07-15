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
package org.kuali.common.util.config;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.junit.Ignore;
import org.junit.Test;
import org.kuali.common.util.ProjectUtils;
import org.kuali.common.util.PropertyUtils;

public class DefaultProjectConfigServiceTest {

	@Test
	public void testIt() {

		try {
			String groupId = "org.kuali.common";
			String artifactId = "kuali-util";
			String configId = groupId + ":" + artifactId + ":scm";
			Properties project = ProjectUtils.loadProject(groupId, artifactId).getProperties();
			ProjectConfigService service = new DefaultProjectConfigService();
			Properties properties = service.getProperties(project, Arrays.asList(configId));
			PropertyUtils.info(properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Ignore
	public void test() {

		try {
			ContextConfig mpx = new ContextConfig("mpx", Arrays.asList(new Location("${metainf.common}/mpx.properties")));
			ContextConfig sql = new ContextConfig("sql", Arrays.asList(new Location("${metainf.common}/sql.properties")));
			ContextConfig metainf = new ContextConfig("metainf");
			metainf.setChildren(Arrays.asList(mpx, sql));
			List<ContextConfig> contexts = new ArrayList<ContextConfig>();
			contexts.add(metainf);

			List<Location> locations = new ArrayList<Location>();
			locations.add(new Location("${classpath.prefix}/sql.xml"));
			ProjectConfig config = new ProjectConfig();
			config.setGroupId("org.kuali.common");
			config.setArtifactId("kuali-util");
			config.setLocations(locations);
			config.setContexts(contexts);
			DefaultProjectConfigService service = new DefaultProjectConfigService();
			File file = new File("/tmp/metadata.xml");
			service.store(file, config);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
