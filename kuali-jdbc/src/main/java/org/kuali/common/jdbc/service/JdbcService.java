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
package org.kuali.common.jdbc.service;

import java.util.List;

import javax.sql.DataSource;

import org.kuali.common.jdbc.model.ExecutionResult;
import org.kuali.common.jdbc.model.context.JdbcContext;
import org.kuali.common.jdbc.model.meta.JdbcMetaData;

public interface JdbcService {

	/**
	 * Get JDBC related metadata
	 */
	JdbcMetaData getJdbcMetaData(DataSource dataSource);

	/**
	 * Execute SQL as dictated by the context
	 */
	ExecutionResult executeSql(JdbcContext context);

	/**
	 * Execute a single SQL statement
	 */
	ExecutionResult executeSql(DataSource dataSource, String sql);

	/**
	 * Execute a list of SQL statements
	 */
	ExecutionResult executeSql(DataSource dataSource, List<String> sql);
}
