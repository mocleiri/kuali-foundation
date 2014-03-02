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
package org.kuali.common.util.metainf.spring;

import java.util.List;

import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.metainf.model.MetaInfContext;
import org.kuali.common.util.metainf.service.MetaInfExecutable;
import org.kuali.common.util.metainf.service.MetaInfService;
import org.kuali.common.util.metainf.service.MetaInfUtils;
import org.kuali.common.util.spring.ExecutionConfig;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ MetaInfServiceConfig.class, SpringServiceConfig.class })
public class MetaInfExecutableConfig implements ExecutionConfig {

	@Autowired
	EnvironmentService env;

	@Autowired
	MetaInfContextsConfig contextsConfig;

	@Autowired
	MetaInfService service;

	@Override
	@Bean(initMethod = "execute")
	public Executable executable() {
		boolean skip = env.getBoolean(MetaInfUtils.PROPERTY_PREFIX + ".skip", false);
		List<MetaInfContext> contexts = contextsConfig.metaInfContexts();
		return new MetaInfExecutable(contexts, service, skip);
	}

}
