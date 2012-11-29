package org.kuali.common.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.jdbc.context.DatabaseInitializeContext;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.SimpleFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultDatabaseService implements DatabaseService {
	private static final Logger logger = LoggerFactory.getLogger(DefaultDatabaseService.class);

	@Override
	public void initialize(DatabaseInitializeContext context) {
		long start = System.currentTimeMillis();
		logger.info("---------------- JDBC Information ----------------");
		logger.info("Vendor - {}", context.getProcess().getVendor());
		logger.info("URL - {}", context.getProcess().getUrl());
		logger.info("User - {}", context.getProcess().getUsername());
		logger.debug("Password - {}", context.getProcess().getPassword());
		logger.info("DBA URL - {}", context.getProcess().getDbaUrl());
		logger.info("DBA User - {}", context.getProcess().getDbaUsername());
		logger.debug("DBA Password - {}", context.getProcess().getDbaPassword());
		JdbcMetaData metadata = context.getService().getJdbcMetaData(context.getDba().getDataSource());
		logger.info("Product Name - {}", metadata.getDatabaseProductName());
		logger.info("Product Version - {}", metadata.getDatabaseProductVersion());
		logger.info("Driver - {}", context.getProcess().getDriver());
		logger.info("Driver Name - {}", metadata.getDriverName());
		logger.info("Driver Version - {}", metadata.getDriverVersion());
		logger.info("SQL Encoding - {}", context.getEncoding());
		logger.info("--------------------------------------------------");
		doDba(context);
		doSchema(context);
		doData(context);
		doConstraints(context);
		logger.info("Total time: {}", context.getFormatter().getTime(System.currentTimeMillis() - start));
	}

	protected void doDba(DatabaseInitializeContext context) {
		logger.info("Executing DBA SQL");
		SqlMetaDataList metadata = context.getService().executeSqlStrings(context.getDba(), context.getDbaSql());
		logExecution("dba", metadata, context.getFormatter());
	}

	protected void doSchema(DatabaseInitializeContext context) {
		doDDL(context, "schema", context.getSchemaPropertyPrefix());
	}

	protected void doConstraints(DatabaseInitializeContext context) {
		doDDL(context, "constraints", context.getConstraintPropertyPrefix());
	}

	protected void doData(DatabaseInitializeContext context) {
		List<String> keys = PropertyUtils.getStartsWithKeys(context.getProperties(), context.getDataPropertyPrefix());
		List<String> locationListings = PropertyUtils.getValues(context.getProperties(), keys);
		List<String> locations = LocationUtils.getLocations(locationListings);
		logger.info("Executing data load SQL");
		context.getNormal().setShowProgress(false);
		SqlMetaDataList metadata = context.getService().executeSql(context.getNormal(), locations, context.getEncoding());
		context.getNormal().setShowProgress(true);
		logExecution("data load", metadata, context.getFormatter());
	}

	protected SqlMetaDataList doDDL(DatabaseInitializeContext context, String type, String prefix) {
		List<String> keys = PropertyUtils.getStartsWithKeys(context.getProperties(), prefix);
		List<String> locations = PropertyUtils.getValues(context.getProperties(), keys);
		SqlMetaDataList metadata = context.getService().executeSql(context.getNormal(), locations, context.getEncoding());
		logExecution(type, metadata, context.getFormatter());
		return metadata;
	}

	protected void logExecution(String executionType, SqlMetaDataList metadata, SimpleFormatter formatter) {
		List<Object> args = new ArrayList<Object>();
		args.add(formatter.getCount(metadata.getCount()));
		args.add(formatter.getCount(metadata.size()));
		args.add(formatter.getTime(metadata.getExecutionTime()));
		logger.info("Total " + executionType + " SQL statements: {}  Sources: {}  Total time: {}", CollectionUtils.toArray(args));
	}

}
