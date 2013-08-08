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

package org.kuali.common.impex.cli.dump;

import org.kuali.common.impex.spring.DumpDatabaseExecutableConfig;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.spring.SpringExecutable;
import org.kuali.common.util.spring.config.annotation.Execute;
import org.kuali.common.util.spring.main.MainContext;
import org.kuali.common.util.spring.main.MainService;
import org.kuali.common.util.spring.main.MainUtils;
import org.kuali.common.util.spring.main.spring.MainServiceConfig;
import org.kuali.common.util.spring.service.PropertySourceContext;
import org.kuali.common.util.spring.service.SpringContext;
import org.kuali.common.util.spring.service.SpringService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.PropertySource;

@Configuration
@Import({ MainServiceConfig.class, SpringServiceConfig.class })
public class DumpDatabase {

	public static void main(String[] args) {
		MainUtils.runAndExit(DumpDatabase.class, args);
	}

	@Autowired
	MainContext context;

	@Autowired
	MainService mainService;

	@Autowired
	SpringService springService;

	@Execute
	public Executable main() {
		PropertySource<?> propertySource = mainService.getPropertySource(context, DumpDatabasePSC.class);
		PropertySourceContext psc = new PropertySourceContext(propertySource, true);
		SpringContext context = new SpringContext(DumpDatabaseExecutableConfig.class, psc);
		return new SpringExecutable(springService, context);
	}

}
