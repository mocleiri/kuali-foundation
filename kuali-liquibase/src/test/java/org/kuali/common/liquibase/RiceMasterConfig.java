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
package org.kuali.common.liquibase;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.kuali.common.jdbc.model.context.JdbcContext;
import org.kuali.common.jdbc.service.spring.JdbcServiceConfig;
import org.kuali.common.jdbc.sql.spring.DbaContextConfig;
import org.kuali.common.jdbc.sql.spring.JdbcContextsConfig;
import org.kuali.common.jdbc.sql.spring.JdbcContextsExecutableConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ JdbcServiceConfig.class, DbaContextConfig.class, JdbcContextsExecutableConfig.class })
public class RiceMasterConfig implements JdbcContextsConfig {

	@Autowired
	DbaContextConfig config;

	@Override
	@Bean
	public List<JdbcContext> jdbcContexts() {
		JdbcContext before = config.dbaBeforeContext();
		JdbcContext after = config.dbaAfterContext();
		return Collections.unmodifiableList(Arrays.asList(before, after));
	}
}
