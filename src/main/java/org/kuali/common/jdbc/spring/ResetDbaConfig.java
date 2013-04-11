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

import org.kuali.common.jdbc.JdbcExecutable;
import org.kuali.common.jdbc.context.JdbcContext;
import org.kuali.common.jdbc.listener.LogSqlListener;
import org.kuali.common.jdbc.listener.LogSqlMode;
import org.kuali.common.jdbc.supplier.ComplexStringSupplier;
import org.kuali.common.jdbc.supplier.SqlSupplier;
import org.kuali.common.util.LoggerLevel;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResetDbaConfig extends ResetBaseConfig {

	@Bean
	public Executable jdbcDbaExecutable() {
		JdbcExecutable exec = new JdbcExecutable();
		exec.setSkip(SpringUtils.getBoolean(env, "jdbc.dba.skip", false));
		exec.setService(commonConfig.jdbcService());
		exec.setContext(getJdbcContext());
		return exec;
	}

	protected JdbcContext getJdbcContext() {
		JdbcContext ctx = new JdbcContext();
		ctx.setMessage(SpringUtils.getProperty(env, "sql.dba.message"));
		ctx.setSkip(SpringUtils.getBoolean(env, "sql.dba.skip", false));
		ctx.setDataSource(dataSourceConfig.jdbcDbaDataSource());
		ctx.setSuppliers(Arrays.asList(getSqlSupplier()));
		ctx.setListener(new LogSqlListener(LoggerLevel.INFO, LogSqlMode.BEFORE));
		return ctx;
	}

	protected SqlSupplier getSqlSupplier() {
		String validate = SpringUtils.getProperty(env, "sql.validate");
		String drop = SpringUtils.getProperty(env, "sql.drop");
		String create = SpringUtils.getProperty(env, "sql.create");
		ComplexStringSupplier css = new ComplexStringSupplier();
		css.setReader(commonConfig.jdbcSqlReader());
		css.setStrings(Arrays.asList(validate, drop, create));
		return css;
	}

}
