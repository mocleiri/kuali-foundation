package org.kuali.common.jdbc;

import javax.sql.DataSource;

import org.kuali.common.jdbc.context.DatabaseProcessContext;
import org.kuali.common.util.execute.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class LogConfigExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(LogConfigExecutable.class);

	DatabaseProcessContext context;
	DataSource dataSource;
	JdbcService service;
	boolean skip;

	@Override
	public void execute() {
		if (skip) {
			logger.info("Skipping execution");
			return;
		}

		Assert.notNull(service, "service is null");
		Assert.notNull(context, "context is null");
		Assert.notNull(dataSource, "dataSource is null");

	}

}
