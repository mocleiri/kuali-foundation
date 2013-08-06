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
import org.kuali.common.util.metainf.service.DefaultMetaInfService;
import org.kuali.common.util.metainf.service.MetaInfExecutable;
import org.kuali.common.util.metainf.service.MetaInfService;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class MetaInfConfig {

	private static final String PREFIX = MetaInfCommonConfig.FEATURE_ID.getFeatureId();

	@Autowired
	Environment env;

	@Autowired
	MetaInfContextsConfig metaInfContextsConfig;

	public MetaInfService metaInfService() {
		return new DefaultMetaInfService();
	}

	@Bean
	public Executable metaInfExecutable() {
		boolean skip = SpringUtils.getBoolean(env, PREFIX + ".skip", false);
		List<MetaInfContext> contexts = metaInfContextsConfig.metaInfContexts();
		MetaInfService service = metaInfService();
		return new MetaInfExecutable(contexts, service, skip);
	}

}
