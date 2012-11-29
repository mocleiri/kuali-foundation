package org.kuali.common.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.jdbc.context.DatabaseResetContext;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.SimpleFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultDatabaseService implements DatabaseService {
	private static final Logger logger = LoggerFactory.getLogger(DefaultDatabaseService.class);

	@Override
	public void reset(DatabaseResetContext context) {
		long start = System.currentTimeMillis();
		logger.info("---------------- Reset Database ----------------");
		logger.info("Vendor - {}", context.getDatabaseProcessContext().getVendor());
		logger.info("URL - {}", context.getDatabaseProcessContext().getUrl());
		logger.info("User - {}", context.getDatabaseProcessContext().getUsername());
		logger.debug("Password - {}", context.getDatabaseProcessContext().getPassword());
		logger.info("DBA URL - {}", context.getDatabaseProcessContext().getDbaUrl());
		logger.info("DBA User - {}", context.getDatabaseProcessContext().getDbaUsername());
		logger.debug("DBA Password - {}", context.getDatabaseProcessContext().getDbaPassword());
		JdbcMetaData metadata = context.getService().getJdbcMetaData(context.getDbaJdbcContext().getDataSource());
		logger.info("Product Name - {}", metadata.getDatabaseProductName());
		logger.info("Product Version - {}", metadata.getDatabaseProductVersion());
		logger.info("Driver - {}", context.getDatabaseProcessContext().getDriver());
		logger.info("Driver Name - {}", metadata.getDriverName());
		logger.info("Driver Version - {}", metadata.getDriverVersion());
		logger.info("SQL Encoding - {}", context.getEncoding());
		logger.info("------------------------------------------------");
		SqlMetaDataList metaData = new SqlMetaDataList();
		add(metaData, doDba(context));
		add(metaData, doSQL(context, "schema", context.getSchemaPropertyPrefix()));
		add(metaData, doSQL(context, "data load", context.getDataPropertyPrefix()));
		add(metaData, doSQL(context, "constraints", context.getConstraintPropertyPrefix()));
		metaData.setExecutionTime(System.currentTimeMillis() - start);
		logExecution("initialize", metaData, context.getFormatter());
	}

	protected void add(SqlMetaDataList one, SqlMetaDataList two) {
		one.setCount(one.getCount() + two.getCount());
		one.addAll(two);
	}

	protected SqlMetaDataList doDba(DatabaseResetContext context) {
		logger.info("Executing DBA SQL");
		SqlMetaDataList metadata = context.getService().executeSqlStrings(context.getDbaJdbcContext(), context.getDbaSql());
		logExecution("dba", metadata, context.getFormatter());
		return metadata;
	}

	protected SqlMetaDataList doData(DatabaseResetContext context) {
		List<String> keys = PropertyUtils.getStartsWithKeys(context.getProperties(), context.getDataPropertyPrefix());
		List<String> locationListings = PropertyUtils.getValues(context.getProperties(), keys);
		List<String> locations = LocationUtils.getLocations(locationListings);
		logger.info("Executing data load SQL");
		context.getNormalJdbcContext().setShowProgress(false);
		SqlMetaDataList metadata = context.getService().executeSql(context.getNormalJdbcContext(), locations, context.getEncoding());
		context.getNormalJdbcContext().setShowProgress(true);
		logExecution("data load", metadata, context.getFormatter());
		return metadata;
	}

	protected List<String> getLocations(DatabaseResetContext context, String prefix) {
		List<String> keys = PropertyUtils.getStartsWithKeys(context.getProperties(), prefix);
		List<String> locations = new ArrayList<String>();
		for (String key : keys) {
			String value = context.getProperties().getProperty(key);
			if (isLocationList(key, context.getLocationListPattern())) {
				List<String> list = LocationUtils.getLocations(value);
				locations.addAll(list);
			} else {
				locations.add(value);
			}
		}
		return locations;
	}

	protected boolean isLocationList(String key, String pattern) {
		return StringUtils.contains(key, pattern);
	}

	protected SqlMetaDataList doSQL(DatabaseResetContext context, String type, String prefix) {
		List<String> locations = getLocations(context, prefix);
		logger.info("Executing " + type + " SQL");
		SqlMetaDataList metadata = context.getService().executeSql(context.getNormalJdbcContext(), locations, context.getEncoding());
		logExecution(type, metadata, context.getFormatter());
		return metadata;
	}

	protected SqlMetaDataList doDDL(DatabaseResetContext context, String type, String prefix) {
		List<String> locations = getLocations(context, prefix);
		logger.info("Executing " + type + " SQL");
		SqlMetaDataList metadata = context.getService().executeSql(context.getNormalJdbcContext(), locations, context.getEncoding());
		logExecution(type, metadata, context.getFormatter());
		return metadata;
	}

	protected void logExecution(String executionType, SqlMetaDataList metadata, SimpleFormatter formatter) {
		List<Object> args = new ArrayList<Object>();
		args.add(formatter.getCount(metadata.getCount()));
		args.add(formatter.getCount(metadata.size()));
		args.add(formatter.getTime(metadata.getExecutionTime()));
		logger.info("Total " + executionType + " SQL statements: {}  SQL sources: {}  Total time: {}", CollectionUtils.toArray(args));
	}

}
