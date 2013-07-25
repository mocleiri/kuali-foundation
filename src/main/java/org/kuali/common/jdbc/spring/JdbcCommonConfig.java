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
package org.kuali.common.jdbc.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.common.jdbc.DefaultJdbcService;
import org.kuali.common.jdbc.DefaultSqlReader;
import org.kuali.common.jdbc.JdbcService;
import org.kuali.common.jdbc.SqlReader;
import org.kuali.common.jdbc.config.JdbcProjectConstants;
import org.kuali.common.jdbc.supplier.LocationSupplier;
import org.kuali.common.jdbc.supplier.LocationSupplierContext;
import org.kuali.common.jdbc.supplier.LocationSupplierSourceBean;
import org.kuali.common.jdbc.supplier.LocationSuppliersFactoryBean;
import org.kuali.common.jdbc.supplier.SqlLocationSupplier;
import org.kuali.common.jdbc.supplier.SqlSupplier;
import org.kuali.common.util.Project;
import org.kuali.common.util.ProjectUtils;
import org.kuali.common.util.property.Constants;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

@Configuration
public class JdbcCommonConfig {

	protected final static String CONTEXT_KEY_SUFFIX = ".context";

	@Autowired
	ConfigurableEnvironment env;

	@Bean
	public SqlReader jdbcSqlReader() {
		return new DefaultSqlReader();
	}

	@Bean
	public JdbcService jdbcService() {
		return new DefaultJdbcService();
	}

	@Bean
	public Map<String, LocationSupplierSourceBean> jdbcExtensionMappings() {
		Project project = ProjectUtils.loadProject(JdbcProjectConstants.GROUP_ID, JdbcProjectConstants.ARTIFACT_ID);

		SqlLocationSupplier sls = new SqlLocationSupplier();
		sls.setReader(jdbcSqlReader());
		sls.setEncoding(project.getEncoding());

		LocationSupplierSourceBean lssb = new LocationSupplierSourceBean();
		lssb.setSupplierClass(SqlLocationSupplier.class);
		lssb.setSupplierInstance(sls);

		Map<String, LocationSupplierSourceBean> map = new HashMap<String, LocationSupplierSourceBean>();
		map.put("sql", lssb);
		return map;
	}

	public List<SqlSupplier> getSqlSuppliers(String propertyKey) {
		String contextValue = SpringUtils.getProperty(env, propertyKey + CONTEXT_KEY_SUFFIX, Constants.NONE);
		LocationSupplierContext context = new LocationSupplierContext();
		context.setValue(contextValue);

		LocationSuppliersFactoryBean factory = new LocationSuppliersFactoryBean();
		factory.setPropertyKey(propertyKey);
		factory.setEnv(env);
		factory.setExtensionMappings(jdbcExtensionMappings());
		factory.setContext(context);
		List<LocationSupplier> list = factory.getObject();
		return new ArrayList<SqlSupplier>(list);
	}
}
