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

/**
 * @deprecated
 */
@Deprecated
public abstract class AbstractSqlDbaConfig extends SqlBaseConfig {

	// This indicates before/after
	protected abstract String getPhase();

	// This must return the fully prepared executable to use and must have a unique bean name for the context
	public abstract Executable getDbaPhaseExecutable();

	protected Executable getDbaExecutable() {
		JdbcExecutable exec = new JdbcExecutable();
		exec.setSkip(SpringUtils.getBoolean(env, "jdbc.dba." + getPhase() + ".skip", false));
		exec.setService(commonConfig.jdbcService());
		exec.setContext(getJdbcContext());
		return exec;
	}

	protected JdbcContext getJdbcContext() {
		JdbcContext ctx = new JdbcContext();
		// All dba SQL executes sequentially
		ctx.setMessage("[dba:" + getPhase() + "]");
		ctx.setSkip(SpringUtils.getBoolean(env, "sql.dba." + getPhase() + ".skip", false));
		ctx.setDataSource(dataSourceConfig.jdbcDbaDataSource());
		ctx.setSuppliers(Arrays.asList(getSqlSupplier()));
		ctx.setListener(new LogSqlListener(LoggerLevel.INFO, LogSqlMode.BEFORE));
		return ctx;
	}

	protected SqlSupplier getSqlSupplier() {
		String sql = SpringUtils.getProperty(env, "sql.dba." + getPhase());
		ComplexStringSupplier css = new ComplexStringSupplier();
		css.setReader(commonConfig.jdbcSqlReader());
		css.setStrings(Arrays.asList(sql));
		return css;
	}

}
