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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.jdbc.context.DatabaseProcessContext;
import org.kuali.common.jdbc.context.DatabaseResetContext;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.LoggerUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.SimpleFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultDatabaseService implements DatabaseService {
	private static final Logger logger = LoggerFactory.getLogger(DefaultDatabaseService.class);

	@Override
	public void reset(DatabaseResetContext context) {
		DatabaseProcessContext dpc = context.getDatabaseProcessContext();
		long start = System.currentTimeMillis();
		logger.info("---------------- Reset Database ----------------");
		logger.info("Vendor - {}", context.getDatabaseProcessContext().getVendor());
		logger.info("URL - {}", context.getDatabaseProcessContext().getUrl());
		logger.info("User - {}", LoggerUtils.getUsername(dpc.getUsername()));
		logger.info("Password - {}", LoggerUtils.getPassword(dpc.getUsername(), dpc.getPassword()));
		logger.info("DBA URL - {}", context.getDatabaseProcessContext().getDbaUrl());
		logger.info("DBA User - {}", LoggerUtils.getUsername(dpc.getDbaUsername()));
		logger.info("DBA Password - {}", LoggerUtils.getPassword(dpc.getDbaUsername(), dpc.getDbaPassword()));
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
		context.getNormalJdbcContext().setShowProgressMin(5000);
		add(metaData, doSQL(context, "data load", context.getDataPropertyPrefix()));
		context.getNormalJdbcContext().setShowProgressMin(50);
		add(metaData, doSQL(context, "constraints", context.getConstraintPropertyPrefix()));
		metaData.setExecutionTime(System.currentTimeMillis() - start);
		logExecution("reset database", metaData, context.getFormatter());
	}

	protected void add(SqlMetaDataList one, SqlMetaDataList two) {
		one.setCount(one.getCount() + two.getCount());
		one.addAll(two);
	}

	protected SqlMetaDataList doDba(DatabaseResetContext context) {
		logger.info("Executing DBA SQL");
		SqlMetaDataList metadata = context.getService().executeSqlStrings(context.getDbaJdbcContext(), Collections.singletonList(context.getDbaSql()));
		logExecution("dba", metadata, context.getFormatter());
		return metadata;
	}

	protected SqlMetaDataList doSQL(DatabaseResetContext context, String type, String prefix) {
		List<String> locations = getLocations(context.getProperties(), prefix, context.getLocationListPattern());
		logger.info("Executing " + type + " SQL");
		SqlMetaDataList metadata = context.getService().executeSql(context.getNormalJdbcContext(), locations, context.getEncoding());
		logExecution(type, metadata, context.getFormatter());
		return metadata;
	}

	protected List<String> getLocations(Properties properties, String prefix, String locationListPattern) {
		List<String> keys = PropertyUtils.getStartsWithKeys(properties, prefix);
		List<String> locations = new ArrayList<String>();
		for (String key : keys) {
			String value = properties.getProperty(key);
			if (isLocationList(key, locationListPattern)) {
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

	protected void logExecution(String executionType, SqlMetaDataList metadata, SimpleFormatter formatter) {
		List<Object> args = new ArrayList<Object>();
		args.add(formatter.getCount(metadata.getCount()));
		args.add(formatter.getCount(metadata.size()));
		args.add(formatter.getTime(metadata.getExecutionTime()));
		logger.info("Total " + executionType + " SQL statements: {}  SQL sources: {}  Total time: {}", CollectionUtils.toObjectArray(args));
	}
}
