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

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.jdbc.model.context.JdbcContext;
import org.kuali.common.jdbc.project.spring.JdbcProjectConfig;
import org.kuali.common.jdbc.reader.SqlReader;
import org.kuali.common.jdbc.reader.spring.SqlReaderConfig;
import org.kuali.common.jdbc.service.JdbcExecutable;
import org.kuali.common.jdbc.service.JdbcService;
import org.kuali.common.jdbc.service.spring.DataSourceConfig;
import org.kuali.common.jdbc.service.spring.JdbcServiceConfig;
import org.kuali.common.jdbc.show.spring.JdbcShowConfig;
import org.kuali.common.jdbc.sql.spring.DbaContextConfig;
import org.kuali.common.jdbc.sql.spring.JdbcContextsConfig;
import org.kuali.common.jdbc.suppliers.SqlLocationSupplier;
import org.kuali.common.jdbc.suppliers.SqlSupplier;
import org.kuali.common.jdbc.vendor.model.DatabaseVendor;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.ExecutablesExecutable;
import org.kuali.common.util.project.ProjectUtils;
import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.spring.config.annotation.Execute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.Assert;

@Configuration
@Import({ JdbcServiceConfig.class, DbaContextConfig.class, SqlReaderConfig.class, JdbcProjectConfig.class, JdbcShowConfig.class })
public class DropCreateConfig implements JdbcContextsConfig {

	private static final List<String> SCHEMAS = Arrays.asList("ole-rice-sql", "ole-master-sql");

	@Autowired
	DbaContextConfig dba;

	@Autowired
	DatabaseVendor vendor;

	@Autowired
	Project project;

	@Autowired
	SqlReader reader;

	@Autowired
	DataSourceConfig dataSources;

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
		return new ExecutablesExecutable(execs);
	}

	@Override
	@Bean
	public List<JdbcContext> jdbcContexts() {
		JdbcContext before = dba.dbaBeforeContext();
		JdbcContext schemas = schemaJdbcContext();
		JdbcContext data = dataJdbcContext();
		JdbcContext constraints = constraintsJdbcContext();
		JdbcContext after = dba.dbaAfterContext();
		return Collections.unmodifiableList(Arrays.asList(before, schemas, data, constraints, after));
	}

	@Bean
	public JdbcContext schemaJdbcContext() {
		List<SqlSupplier> suppliers = getSuppliers("");
		return getJdbcContext("[schema:concurrent]", suppliers);
	}

	@Bean
	public JdbcContext dataJdbcContext() {
		List<SqlSupplier> suppliers = getDataSuppliers();
		return getJdbcContext("[data:concurrent]", suppliers);
	}

	@Bean
	public JdbcContext constraintsJdbcContext() {
		List<SqlSupplier> suppliers = getSuppliers("-constraints");
		return getJdbcContext("[constraints:concurrent]", suppliers);
	}

	protected JdbcContext getJdbcContext(String message, List<SqlSupplier> suppliers) {
		DataSource dataSource = dataSources.dataSource();
		return new JdbcContext(dataSource, suppliers, message, true);
	}

	protected List<SqlSupplier> getSuppliers(String suffix) {
		List<SqlSupplier> suppliers = new ArrayList<SqlSupplier>();
		for (String schema : SCHEMAS) {
			String location = "classpath:sql/" + vendor.getCode() + "/" + schema + suffix + ".sql";
			String encoding = ProjectUtils.getEncoding(project);
			suppliers.add(new SqlLocationSupplier(location, encoding, reader));
		}
		return suppliers;
	}

	protected List<SqlSupplier> getDataSuppliers() {
		List<SqlSupplier> suppliers = new ArrayList<SqlSupplier>();
		for (String schema : SCHEMAS) {
			String encoding = ProjectUtils.getEncoding(project);
			String location = "classpath:META-INF/sql/" + vendor.getCode() + "/" + schema + ".resources";
			List<String> resources = LocationUtils.getLocations(location, encoding);
			for (String resource : resources) {
				Assert.isTrue(LocationUtils.exists(resource));
				Assert.isTrue(StringUtils.endsWithIgnoreCase(resource, ".sql"));
				suppliers.add(new SqlLocationSupplier(resource, encoding, reader));
			}
		}
		return suppliers;
	}

}
