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
package org.kuali.common.jdbc.context;

import java.sql.Connection;
import java.sql.Statement;

import org.kuali.common.jdbc.SqlSource;

public class SqlSourceExecutionContext {

	JdbcContext jdbcContext;
	Connection connection;
	Statement statement;
	SqlSource source;
	long runningCount;
	int sourceIndex;
	int sourcesCount;
	ProgressContext progressContext;

	public JdbcContext getJdbcContext() {
		return jdbcContext;
	}

	public void setJdbcContext(JdbcContext context) {
		this.jdbcContext = context;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}

	public SqlSource getSource() {
		return source;
	}

	public void setSource(SqlSource source) {
		this.source = source;
	}

	public long getRunningCount() {
		return runningCount;
	}

	public void setRunningCount(long runningCount) {
		this.runningCount = runningCount;
	}

	public int getSourceIndex() {
		return sourceIndex;
	}

	public void setSourceIndex(int index) {
		this.sourceIndex = index;
	}

	public int getSourcesCount() {
		return sourcesCount;
	}

	public void setSourcesCount(int sourcesCount) {
		this.sourcesCount = sourcesCount;
	}

	public ProgressContext getProgressContext() {
		return progressContext;
	}

	public void setProgressContext(ProgressContext progressContext) {
		this.progressContext = progressContext;
	}

}
