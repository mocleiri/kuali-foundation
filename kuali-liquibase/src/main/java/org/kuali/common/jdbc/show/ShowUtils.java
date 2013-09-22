package org.kuali.common.jdbc.show;

import javax.sql.DataSource;

import org.kuali.common.jdbc.model.Credentials;
import org.kuali.common.jdbc.model.context.ConnectionContext;
import org.kuali.common.jdbc.model.context.DatabaseProcessContext;
import org.kuali.common.jdbc.model.meta.Driver;
import org.kuali.common.jdbc.model.meta.JdbcMetaData;
import org.kuali.common.jdbc.model.meta.Product;
import org.kuali.common.jdbc.service.JdbcService;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;

public class ShowUtils {

	public static void showOpen(Logger logger, DatabaseProcessContext context) {
		Credentials auth = context.getConnections().getNormal().getCredentials();
		String url = context.getConnections().getNormal().getUrl();
		logger.info("------------------------------------------------------------------------");
		logger.info("JDBC Configuration");
		logger.info("------------------------------------------------------------------------");
		logger.info("Vendor - {}", context.getVendor());
		logger.info("URL - {}", url);
		logger.info("Schema - {}", context.getSchema());
		logger.info("User - {}", LoggerUtils.getUsername(auth.getUsername()));
		logger.info("Password - {}", LoggerUtils.getPassword(auth.getUsername(), auth.getPassword()));
	}

	public static void showDba(Logger logger, ConnectionContext dba) {
		Credentials auth = dba.getCredentials();
		logger.info("DBA URL - {}", dba.getUrl());
		logger.info("DBA User - {}", LoggerUtils.getUsername(auth.getUsername()));
		logger.info("DBA Password - {}", LoggerUtils.getPassword(auth.getUsername(), auth.getPassword()));
	}

	public static void showClose(Logger logger, DatabaseProcessContext context, JdbcService service, DataSource dataSource) {
		logger.info("Driver - {}", context.getConnections().getDriver().getName());
		logger.info("SQL Encoding - {}", context.getEncoding());
		// Establish a connection to the db to extract more detailed info
		JdbcMetaData metadata = service.getJdbcMetaData(dataSource);
		Product product = metadata.getProduct();
		Driver driver = metadata.getDriver();
		logger.info("Product Name - {}", product.getName());
		logger.info("Product Version - {}", product.getVersion());
		logger.info("Driver Name - {}", driver.getName());
		logger.info("Driver Version - {}", driver.getVersion());
		logger.info("------------------------------------------------------------------------");

	}

}
