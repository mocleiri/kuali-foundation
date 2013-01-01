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
package org.kuali.common.jdbc.context;

import java.util.Properties;

import org.kuali.common.jdbc.DefaultJdbcService;
import org.kuali.common.jdbc.JdbcService;
import org.kuali.common.util.SimpleFormatter;

public class DatabaseResetContext {

	public static final String DEFAULT_SCHEMA_PROPERTY_PREFIX = "sql.schema.loc";
	public static final String DEFAULT_DATA_PROPERTY_PREFIX = "sql.data.loc";
	public static final String DEFAULT_CONSTRAINT_PROPERTY_PREFIX = "sql.constraints.loc";
	public static final String DEFAULT_LOCATION_LIST_PATTERN = ".loc.list";

	JdbcService service = new DefaultJdbcService();
	SimpleFormatter formatter = new SimpleFormatter();
	String locationListPattern = DEFAULT_LOCATION_LIST_PATTERN;
	String schemaPropertyPrefix = DEFAULT_SCHEMA_PROPERTY_PREFIX;
	String dataPropertyPrefix = DEFAULT_DATA_PROPERTY_PREFIX;
	String constraintPropertyPrefix = DEFAULT_CONSTRAINT_PROPERTY_PREFIX;

	String encoding;
	Properties properties;
	DatabaseProcessContext databaseProcessContext;
	JdbcContext normalJdbcContext;
	JdbcContext dbaJdbcContext;
	String dbaSql;

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public SimpleFormatter getFormatter() {
		return formatter;
	}

	public void setFormatter(SimpleFormatter formatter) {
		this.formatter = formatter;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public DatabaseProcessContext getDatabaseProcessContext() {
		return databaseProcessContext;
	}

	public void setDatabaseProcessContext(DatabaseProcessContext process) {
		this.databaseProcessContext = process;
	}

	public JdbcService getService() {
		return service;
	}

	public void setService(JdbcService service) {
		this.service = service;
	}

	public JdbcContext getNormalJdbcContext() {
		return normalJdbcContext;
	}

	public void setNormalJdbcContext(JdbcContext normal) {
		this.normalJdbcContext = normal;
	}

	public JdbcContext getDbaJdbcContext() {
		return dbaJdbcContext;
	}

	public void setDbaJdbcContext(JdbcContext dba) {
		this.dbaJdbcContext = dba;
	}

	public String getSchemaPropertyPrefix() {
		return schemaPropertyPrefix;
	}

	public void setSchemaPropertyPrefix(String schemaPropertyPrefix) {
		this.schemaPropertyPrefix = schemaPropertyPrefix;
	}

	public String getDataPropertyPrefix() {
		return dataPropertyPrefix;
	}

	public void setDataPropertyPrefix(String dataPropertyPrefix) {
		this.dataPropertyPrefix = dataPropertyPrefix;
	}

	public String getConstraintPropertyPrefix() {
		return constraintPropertyPrefix;
	}

	public void setConstraintPropertyPrefix(String constraintPropertyPrefix) {
		this.constraintPropertyPrefix = constraintPropertyPrefix;
	}

	public String getLocationListPattern() {
		return locationListPattern;
	}

	public void setLocationListPattern(String locationListSuffix) {
		this.locationListPattern = locationListSuffix;
	}

	public String getDbaSql() {
		return dbaSql;
	}

	public void setDbaSql(String dbaSql) {
		this.dbaSql = dbaSql;
	}

}
