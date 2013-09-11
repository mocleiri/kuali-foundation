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

import org.kuali.common.jdbc.model.ExecutionResult;
import org.kuali.common.jdbc.model.context.JdbcContext;
import org.kuali.common.util.Assert;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.execute.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class JdbcExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(JdbcExecutable.class);

	public static final boolean DEFAULT_SKIP = false;

	private final JdbcService service;
	private final JdbcContext context;
	private final boolean skip;

	public JdbcExecutable(JdbcService service, JdbcContext context) {
		this(service, context, DEFAULT_SKIP);
	}

	public JdbcExecutable(JdbcService service, JdbcContext context, boolean skip) {
		Assert.noNulls(service, context);
		this.service = service;
		this.context = context;
		this.skip = skip;
	}

	@Override
	public void execute() {

		if (skip) {
			return;
		}

		ExecutionResult result = service.executeSql(context);
		if (result.getStatementsExecuted() > 0) {
			showResult(result);
		}
	}

	protected void showResult(ExecutionResult result) {
		String updates = FormatUtils.getCount(result.getUpdateCount());
		String statements = FormatUtils.getCount(result.getStatementsExecuted());
		String elapsed = FormatUtils.getTime(result.getElapsed());
		Object[] args = { updates, statements, elapsed };
		logger.info("Rows updated: {}  SQL Statements: {}  Total time: {}", args);
	}

	public JdbcService getService() {
		return service;
	}

	public JdbcContext getContext() {
		return context;
	}

	public boolean isSkip() {
		return skip;
	}

}
