package org.kuali.common.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.SimpleFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseInitializerContext {

	private static final Logger logger = LoggerFactory.getLogger(DatabaseInitializerContext.class);

	String encoding = null;

	SimpleFormatter formatter = null;

	Properties properties = null;

	DatabaseProcessContext process = null;

	JdbcService service = null;

	JdbcContext normal = null;

	JdbcContext dba = null;

	String schemaPropertyPrefix = null;

	String dataPropertyPrefix = null;

	String constraintPropertyPrefix = null;

	List<String> dbaSql = null;

	public void initialize() {
		long start = System.currentTimeMillis();
		logger.info("---------------- JDBC Information ----------------");
		logger.info("Vendor - {}", process.getVendor());
		logger.info("URL - {}", process.getUrl());
		logger.info("User - {}", process.getUsername());
		logger.debug("Password - {}", process.getPassword());
		logger.info("DBA URL - {}", process.getDbaUrl());
		logger.info("DBA User - {}", process.getDbaUsername());
		logger.debug("DBA Password - {}", process.getDbaPassword());
		JdbcMetaData metadata = service.getJdbcMetaData(dba.getDataSource());
		logger.info("Product Name - {}", metadata.getDatabaseProductName());
		logger.info("Product Version - {}", metadata.getDatabaseProductVersion());
		logger.info("Driver - {}", process.getDriver());
		logger.info("Driver Name - {}", metadata.getDriverName());
		logger.info("Driver Version - {}", metadata.getDriverVersion());
		logger.info("SQL Encoding - {}", encoding);
		logger.info("--------------------------------------------------");
		doDba(service, dba, dbaSql);
		doSchema(service, normal, properties, encoding);
		doData(service, normal, properties, encoding);
		doConstraints(service, normal, properties, encoding);
		logger.info("Total time: {}", formatter.getTime(System.currentTimeMillis() - start));
	}

	protected void doDba(JdbcService service, JdbcContext context, List<String> dbaSql) {
		logger.info("Executing DBA SQL");
		SqlMetaDataList metadata = service.executeSqlStrings(context, dbaSql);
		logExecution("dba", metadata);
	}

	protected void doSchema(JdbcService service, JdbcContext context, Properties properties, String encoding) {
		doDDL(service, context, properties, "schema", schemaPropertyPrefix, encoding);
	}

	protected void doConstraints(JdbcService service, JdbcContext context, Properties properties, String encoding) {
		doDDL(service, context, properties, "constraints", constraintPropertyPrefix, encoding);
	}

	protected void doData(JdbcService service, JdbcContext context, Properties properties, String encoding) {
		List<String> keys = PropertyUtils.getStartsWithKeys(properties, dataPropertyPrefix);
		List<String> locationListings = PropertyUtils.getValues(properties, keys);
		List<String> locations = LocationUtils.getLocations(locationListings);
		logger.info("Executing data load SQL");
		context.setShowProgress(false);
		SqlMetaDataList metadata = service.executeSql(context, locations, encoding);
		context.setShowProgress(true);
		logExecution("data load", metadata);
	}

	protected SqlMetaDataList doDDL(JdbcService service, JdbcContext context, Properties properties, String type, String prefix, String encoding) {
		List<String> keys = PropertyUtils.getStartsWithKeys(properties, prefix);
		List<String> locations = PropertyUtils.getValues(properties, keys);
		SqlMetaDataList metadata = service.executeSql(context, locations, encoding);
		logExecution(type, metadata);
		return metadata;
	}

	protected void logExecution(String executionType, SqlMetaDataList metadata) {
		List<Object> args = new ArrayList<Object>();
		args.add(formatter.getCount(metadata.getCount()));
		args.add(formatter.getCount(metadata.size()));
		args.add(formatter.getTime(metadata.getExecutionTime()));
		logger.info("Total " + executionType + " SQL statements: {}  Sources: {}  Total time: {}", CollectionUtils.toArray(args));
	}

}
