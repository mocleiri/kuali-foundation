package org.kuali.common.jdbc.execute;

import javax.sql.DataSource;

import org.kuali.common.jdbc.model.context.ConnectionContext;
import org.kuali.common.jdbc.model.context.DatabaseContext;
import org.kuali.common.jdbc.model.meta.Driver;
import org.kuali.common.jdbc.model.meta.JdbcMetaData;
import org.kuali.common.jdbc.model.meta.Product;
import org.kuali.common.jdbc.service.JdbcService;
import org.kuali.common.util.LoggerUtils;
import org.slf4j.Logger;

public class ShowUtils {

	public static void showOpen(Logger logger, DatabaseContext context) {
		ConnectionContext normal = context.getNormal();
		logger.info("------------------------------------------------------------------------");
		logger.info("JDBC Configuration");
		logger.info("------------------------------------------------------------------------");
		logger.info("Vendor - {}", context.getVendor());
		logger.info("URL - {}", normal.getUrl());
		logger.info("Schema - {}", context.getSchema());
		logger.info("User - {}", LoggerUtils.getUsername(normal.getUsername()));
		logger.info("Password - {}", LoggerUtils.getPassword(normal.getUsername(), normal.getPassword()));
	}

	public static void showClose(Logger logger, DatabaseContext context, JdbcService service, DataSource dataSource) {
		logger.info("Driver - {}", context.getDriver());
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

	public static void showDba(Logger logger, ConnectionContext dba) {
		logger.info("DBA URL - {}", dba.getUrl());
		logger.info("DBA User - {}", LoggerUtils.getUsername(dba.getUsername()));
		logger.info("DBA Password - {}", LoggerUtils.getPassword(dba.getUsername(), dba.getPassword()));
	}

}
