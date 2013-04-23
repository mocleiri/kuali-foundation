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

import org.kuali.common.jdbc.context.SqlMode;
import org.springframework.core.env.Environment;

public class SqlConfigContext {

	Environment env;
	String type;
	SqlMode mode;
	JdbcCommonConfig commonConfig;
	JdbcDataSourceConfig dataSourceConfig;

	public SqlConfigContext() {
		this(null, null, null, null, null);
	}

	public SqlConfigContext(Environment env, String type, SqlMode mode, JdbcCommonConfig commonConfig, JdbcDataSourceConfig dataSourceConfig) {
		super();
		this.env = env;
		this.type = type;
		this.mode = mode;
		this.commonConfig = commonConfig;
		this.dataSourceConfig = dataSourceConfig;
	}

	public Environment getEnv() {
		return env;
	}

	public void setEnv(Environment env) {
		this.env = env;
	}

	public JdbcCommonConfig getCommonConfig() {
		return commonConfig;
	}

	public void setCommonConfig(JdbcCommonConfig commonConfig) {
		this.commonConfig = commonConfig;
	}

	public JdbcDataSourceConfig getDataSourceConfig() {
		return dataSourceConfig;
	}

	public void setDataSourceConfig(JdbcDataSourceConfig dataSourceConfig) {
		this.dataSourceConfig = dataSourceConfig;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public SqlMode getMode() {
		return mode;
	}

	public void setMode(SqlMode mode) {
		this.mode = mode;
	}

}
