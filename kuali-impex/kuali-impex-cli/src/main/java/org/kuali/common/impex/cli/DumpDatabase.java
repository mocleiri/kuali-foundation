/**
 * Copyright 2011 The Kuali Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.kuali.common.impex.cli;

import java.util.List;

import org.kuali.common.impex.DumpCLIProjectContext;
import org.kuali.common.impex.DumpProjectContext;
import org.kuali.common.impex.spring.DumpDatabaseExecutableConfig;
import org.kuali.common.jdbc.JdbcProjectContext;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.Mode;
import org.kuali.common.util.ProjectContext;
import org.kuali.common.util.execute.SpringExecutable;
import org.kuali.common.util.property.ProjectProperties;
import org.kuali.common.util.service.SpringContext;
import org.kuali.common.util.spring.ConfigUtils;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.core.env.PropertySource;

public class DumpDatabase {

	public static void main(String[] args) {

		try {
			ProjectContext jdbc = new JdbcProjectContext();
			ProjectContext dump = new DumpProjectContext();
			List<ProjectProperties> others = ConfigUtils.getProjectProperties(jdbc, dump);

			ProjectProperties pp = ConfigUtils.getProjectProperties(new DumpCLIProjectContext());
			pp.getPropertiesContext().setMissingLocationsMode(Mode.INFORM);

			PropertySource<?> ps = SpringUtils.getGlobalPropertySource(pp, others);
			SpringContext sc = SpringUtils.getSinglePropertySourceContext(ps);
			sc.setAnnotatedClasses(CollectionUtils.asList(DumpDatabaseExecutableConfig.class));

			SpringExecutable executable = new SpringExecutable(sc);
			executable.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
