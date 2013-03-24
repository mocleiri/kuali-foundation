package org.kuali.common.jdbc;

import javax.sql.DataSource;

import org.kuali.common.jdbc.context.DatabaseProcessContext;
import org.kuali.common.util.LoggerUtils;
import org.kuali.common.util.execute.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class ShowConfigExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(ShowConfigExecutable.class);

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

		logger.info("------------------------------------------------------------------------");
		logger.info("JDBC Configuration");
		logger.info("------------------------------------------------------------------------");
		logger.info("Vendor - {}", context.getVendor());
		logger.info("URL - {}", context.getUrl());
		logger.info("User - {}", LoggerUtils.getUsername(context.getUsername()));
		logger.info("Password - {}", LoggerUtils.getPassword(context.getUsername(), context.getPassword()));
		logger.info("DBA URL - {}", context.getDbaUrl());
		logger.info("DBA User - {}", LoggerUtils.getUsername(context.getDbaUsername()));
		logger.info("DBA Password - {}", LoggerUtils.getPassword(context.getDbaUsername(), context.getDbaPassword()));
		logger.info("Driver - {}", context.getDriver());
		logger.info("SQL Encoding - {}", context.getEncoding());
		// Establish a connection to the db to extract more detailed info
		JdbcMetaData metadata = service.getJdbcMetaData(dataSource);
		logger.info("Product Name - {}", metadata.getDatabaseProductName());
		logger.info("Product Version - {}", metadata.getDatabaseProductVersion());
		logger.info("Driver Name - {}", metadata.getDriverName());
		logger.info("Driver Version - {}", metadata.getDriverVersion());
		logger.info("------------------------------------------------------------------------");
	}

	public DatabaseProcessContext getContext() {
		return context;
	}

	public void setContext(DatabaseProcessContext context) {
		this.context = context;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public JdbcService getService() {
		return service;
	}

	public void setService(JdbcService service) {
		this.service = service;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

}
