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

import java.util.Arrays;

import javax.sql.DataSource;

import org.kuali.common.jdbc.JdbcExecutable;
import org.kuali.common.jdbc.context.JdbcContext;
import org.kuali.common.jdbc.listener.LogSqlListener;
import org.kuali.common.jdbc.listener.LogSqlMode;
import org.kuali.common.jdbc.listener.SqlListener;
import org.kuali.common.jdbc.supplier.ComplexStringSupplier;
import org.kuali.common.jdbc.supplier.SqlSupplier;
import org.kuali.common.util.LoggerLevel;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SqlDbaAfterConfig extends SqlBaseConfig {

	@Bean
	public Executable jdbcDbaCleanupExecutable() {
		boolean skip = SpringUtils.getBoolean(env, "jdbc.cleanup.skip",false);
		DataSource dataSource = dataSourceConfig.jdbcDbaDataSource();
		String sql = SpringUtils.getProperty(env, "sql.dba.cleanup");
		SqlSupplier supplier = new ComplexStringSupplier(sql);
		SqlListener listener = new LogSqlListener(LoggerLevel.INFO, LogSqlMode.BEFORE);

		JdbcContext context = new JdbcContext();
		context.setDataSource(dataSource);
		context.setSuppliers(Arrays.asList(supplier));
		context.setListener(listener);
		context.setSkip(skip);

		return new JdbcExecutable(context);
	}

}
