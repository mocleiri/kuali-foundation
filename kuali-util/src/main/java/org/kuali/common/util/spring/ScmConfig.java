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
package org.kuali.common.util.spring;

import org.kuali.common.util.ScmContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ScmConfig {

	private static final String VENDOR_KEY = "scm.vendor";
	private static final String URL_KEY = "scm.url";
	private static final String SERVICE_KEY = "scm.service";

	@Autowired
	Environment env;

	@Bean
	public ScmContext scmContext() {
		ScmContext context = new ScmContext();
		context.setVendor(SpringUtils.getProperty(env, VENDOR_KEY));
		context.setUrl(SpringUtils.getProperty(env, URL_KEY));
		context.setService(SpringUtils.getProperty(env, SERVICE_KEY));
		return context;
	}

}
