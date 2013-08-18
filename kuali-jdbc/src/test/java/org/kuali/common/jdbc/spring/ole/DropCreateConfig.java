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

import org.kuali.common.jdbc.listeners.LogSqlListener;
import org.kuali.common.jdbc.listeners.SqlListener;
import org.kuali.common.jdbc.model.LogSqlMode;
import org.kuali.common.jdbc.model.context.JdbcContext;
import org.kuali.common.jdbc.project.spring.JdbcProjectConfig;
import org.kuali.common.jdbc.reader.SqlReader;
import org.kuali.common.jdbc.reader.spring.SqlReaderConfig;
import org.kuali.common.jdbc.service.spring.DataSourceConfig;
import org.kuali.common.jdbc.service.spring.JdbcServiceConfig;
import org.kuali.common.jdbc.sql.spring.DbaContextConfig;
import org.kuali.common.jdbc.sql.spring.JdbcContextsConfig;
import org.kuali.common.jdbc.sql.spring.JdbcContextsExecutableConfig;
import org.kuali.common.jdbc.suppliers.SqlLocationSupplier;
import org.kuali.common.jdbc.suppliers.SqlSupplier;
import org.kuali.common.jdbc.vendor.model.DatabaseVendor;
import org.kuali.common.util.log.LoggerLevel;
import org.kuali.common.util.project.ProjectUtils;
import org.kuali.common.util.project.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ JdbcServiceConfig.class, DbaContextConfig.class, JdbcContextsExecutableConfig.class, SqlReaderConfig.class, JdbcProjectConfig.class })
public class DropCreateConfig implements JdbcContextsConfig {

	@Autowired
	DbaContextConfig config;

	@Autowired
	DatabaseVendor vendor;

	@Autowired
	Project project;

	@Autowired
	SqlReader reader;

	@Autowired
	DataSourceConfig dataSourceConfig;

	@Override
	@Bean
	public List<JdbcContext> jdbcContexts() {
		JdbcContext before = config.dbaBeforeContext();
		JdbcContext schema = schemaJdbcContext();
		JdbcContext after = config.dbaAfterContext();
		return Collections.unmodifiableList(Arrays.asList(before, schema, after));
	}

	@Bean
	public JdbcContext schemaJdbcContext() {
		String message = "[schema:concurrent]";
		DataSource dataSource = dataSourceConfig.dataSource();
		List<SqlSupplier> suppliers = getSuppliers(getSchemas());
		SqlListener listener = new LogSqlListener(LoggerLevel.INFO, LogSqlMode.AFTER);
		return new JdbcContext(dataSource, suppliers, message, listener);
	}

	protected List<SqlSupplier> getSuppliers(List<String> schemas) {
		List<SqlSupplier> suppliers = new ArrayList<SqlSupplier>();
		for (String schema : schemas) {
			String location = "classpath:sql/" + vendor.getCode() + "/" + schema + ".sql";
			String encoding = ProjectUtils.getEncoding(project);
			suppliers.add(new SqlLocationSupplier(location, encoding, reader));
		}
		return suppliers;
	}

	protected List<String> getSchemas() {
		return Arrays.asList("ole-rice-sql", "ole-master-sql");
	}

}
