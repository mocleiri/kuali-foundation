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
package org.kuali.common.jdbc.spring.ole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.kuali.common.jdbc.model.context.JdbcContext;
import org.kuali.common.jdbc.project.spring.JdbcProjectConfig;
import org.kuali.common.jdbc.service.JdbcExecutable;
import org.kuali.common.jdbc.service.JdbcService;
import org.kuali.common.jdbc.service.spring.DataSourceConfig;
import org.kuali.common.jdbc.service.spring.JdbcServiceConfig;
import org.kuali.common.jdbc.show.spring.JdbcShowConfig;
import org.kuali.common.jdbc.sql.spring.DbaContextConfig;
import org.kuali.common.jdbc.sql.spring.JdbcContextsConfig;
import org.kuali.common.jdbc.suppliers.ResourcesSupplierFactory;
import org.kuali.common.jdbc.suppliers.SqlSupplier;
import org.kuali.common.jdbc.suppliers.spring.SuppliersFactoryConfig;
import org.kuali.common.jdbc.vendor.model.DatabaseVendor;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.impl.ExecutablesExecutable;
import org.kuali.common.util.spring.config.annotation.Execute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ JdbcServiceConfig.class, DbaContextConfig.class, SuppliersFactoryConfig.class, JdbcProjectConfig.class, JdbcShowConfig.class })
public class DropCreateConfig implements JdbcContextsConfig {

	private static final List<String> SCHEMAS = Arrays.asList("ole-rice-sql", "ole-master-sql");

	@Autowired
	DbaContextConfig dba;

	@Autowired
	DatabaseVendor vendor;

	@Autowired
	DataSourceConfig dataSources;

	@Autowired
	ResourcesSupplierFactory factory;

	@Autowired
	JdbcShowConfig show;

	@Autowired
	JdbcService service;

	@Execute
	public Executable executable() {
		List<Executable> execs = new ArrayList<Executable>();
		execs.add(show.showDbaConfigExecutable());
		for (JdbcContext context : jdbcContexts()) {
			execs.add(new JdbcExecutable(service, context));
		}
		return new ExecutablesExecutable(execs, false, true);
	}

	@Override
	@Bean
	public List<JdbcContext> jdbcContexts() {
		JdbcContext before = dba.dbaBeforeContext();
		JdbcContext schemas = schemaJdbcContext();
		JdbcContext data = dataJdbcContext();
		JdbcContext other = otherJdbcContext();
		JdbcContext constraints = constraintsJdbcContext();
		JdbcContext after = dba.dbaAfterContext();
		return Collections.unmodifiableList(Arrays.asList(before, schemas, data, other, constraints, after));
	}

	@Bean
	public JdbcContext schemaJdbcContext() {
		List<SqlSupplier> suppliers = getSuppliers("");
		return getJdbcContext("[schema:concurrent]", suppliers);
	}

	@Bean
	public JdbcContext otherJdbcContext() {
		String location = "classpath:META-INF/org/kuali/ole/sql/" + vendor.getCode() + "/ole-liquibase-sql.resources";
		List<SqlSupplier> suppliers = factory.getSuppliers(location);
		DataSource dataSource = dataSources.dataSource();
		return new JdbcContext.Builder(dataSource, suppliers).message("[other:sequential]").multithreaded(true).threads(1).build();
	}

	@Bean
	public JdbcContext dataJdbcContext() {
		List<String> locations = getDataLocations();
		List<SqlSupplier> suppliers = factory.getSuppliers(locations);
		return getJdbcContext("[data:concurrent]", suppliers);
	}

	@Bean
	public JdbcContext constraintsJdbcContext() {
		List<SqlSupplier> suppliers = getSuppliers("-constraints");
		return getJdbcContext("[constraints:concurrent]", suppliers);
	}

	protected JdbcContext getJdbcContext(String message, List<SqlSupplier> suppliers) {
		DataSource dataSource = dataSources.dataSource();
		return new JdbcContext.Builder(dataSource, suppliers).message(message).multithreaded(true).build();
	}

	protected List<SqlSupplier> getSuppliers(String suffix) {
		List<SqlSupplier> suppliers = new ArrayList<SqlSupplier>();
		for (String schema : SCHEMAS) {
			String location = "classpath:sql/" + vendor.getCode() + "/" + schema + suffix + ".sql";
			suppliers.add(factory.getSupplier(location));
		}
		return suppliers;
	}

	protected List<String> getDataLocations() {
		List<String> locations = new ArrayList<String>();
		for (String schema : SCHEMAS) {
			locations.add("classpath:META-INF/sql/" + vendor.getCode() + "/" + schema + ".resources");
		}
		return locations;
	}
}
