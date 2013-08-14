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
package org.kuali.common.jdbc.execute;

import javax.sql.DataSource;

import org.kuali.common.jdbc.model.context.DatabaseContext;
import org.kuali.common.jdbc.service.JdbcService;
import org.kuali.common.util.Assert;
import org.kuali.common.util.execute.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ShowConfigExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(ShowConfigExecutable.class);
	public static final boolean DEFAULT_SKIP = false;

	public ShowConfigExecutable(DatabaseContext context, DataSource dataSource, JdbcService service) {
		this(context, dataSource, service, DEFAULT_SKIP);
	}

	public ShowConfigExecutable(DatabaseContext context, DataSource dataSource, JdbcService service, boolean skip) {
		Assert.noNulls(context, dataSource, service);
		this.context = context;
		this.dataSource = dataSource;
		this.service = service;
		this.skip = skip;
	}

	private final DatabaseContext context;
	private final DataSource dataSource;
	private final JdbcService service;
	private final boolean skip;

	@Override
	public void execute() {

		if (skip) {
			return;
		}

		ShowUtils.showOpen(logger, context);
		ShowUtils.showClose(logger, context, service, dataSource);
	}

	public DatabaseContext getContext() {
		return context;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public JdbcService getService() {
		return service;
	}

	public boolean isSkip() {
		return skip;
	}

}
