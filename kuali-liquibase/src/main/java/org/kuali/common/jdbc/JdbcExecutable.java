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
package org.kuali.common.jdbc;

import org.kuali.common.jdbc.context.JdbcContext;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.execute.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * @deprecated
 */
@Deprecated
public class JdbcExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(JdbcExecutable.class);
	public static final JdbcService DEFAULT_JDBC_SERVICE = new DefaultJdbcService();

	JdbcService service = DEFAULT_JDBC_SERVICE;
	JdbcContext context;
	boolean skip;

	public JdbcExecutable() {
		this(DEFAULT_JDBC_SERVICE, null);
	}

	public JdbcExecutable(JdbcContext context) {
		this(DEFAULT_JDBC_SERVICE, context);
	}

	public JdbcExecutable(JdbcService service, JdbcContext context) {
		super();
		this.service = service;
		this.context = context;
	}

	@Override
	public void execute() {
		if (skip) {
			logger.info("Skipping execution");
			return;
		}

		Assert.notNull(service, "service is null");
		Assert.notNull(context, "context is null");

		ExecutionResult result = service.executeSql(context);
		if (result.getStatementsExecuted() > 0) {
			String updates = FormatUtils.getCount(result.getUpdateCount());
			String statements = FormatUtils.getCount(result.getStatementsExecuted());
			String elapsed = FormatUtils.getTime(result.getElapsed());
			Object[] args = { updates, statements, elapsed };
			logger.info("Rows updated: {}  SQL Statements: {}  Total time: {}", args);
		}
	}

	public JdbcService getService() {
		return service;
	}

	public void setService(JdbcService service) {
		this.service = service;
	}

	public JdbcContext getContext() {
		return context;
	}

	public void setContext(JdbcContext context) {
		this.context = context;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

}
