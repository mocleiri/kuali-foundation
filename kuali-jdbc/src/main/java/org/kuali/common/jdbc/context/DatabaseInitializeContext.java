package org.kuali.common.jdbc.context;

import java.util.List;
import java.util.Properties;

import org.kuali.common.jdbc.DefaultJdbcService;
import org.kuali.common.jdbc.JdbcService;
import org.kuali.common.util.SimpleFormatter;

public class DatabaseInitializeContext {

	public static final String DEFAULT_SCHEMA_PROPERTY_PREFIX = "sql.schema.loc";
	public static final String DEFAULT_DATA_PROPERTY_PREFIX = "sql.data.loc";
	public static final String DEFAULT_CONSTRAINT_PROPERTY_PREFIX = "sql.constraints.loc";

	JdbcService service = new DefaultJdbcService();
	SimpleFormatter formatter = new SimpleFormatter();
	String schemaPropertyPrefix = DEFAULT_SCHEMA_PROPERTY_PREFIX;
	String dataPropertyPrefix = DEFAULT_DATA_PROPERTY_PREFIX;
	String constraintPropertyPrefix = DEFAULT_CONSTRAINT_PROPERTY_PREFIX;

	String encoding;
	Properties properties;
	DatabaseProcessContext databaseProcessContext;
	JdbcContext normal;
	JdbcContext dba;
	List<String> dbaSql;

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

	public JdbcContext getNormal() {
		return normal;
	}

	public void setNormal(JdbcContext normal) {
		this.normal = normal;
	}

	public JdbcContext getDba() {
		return dba;
	}

	public void setDba(JdbcContext dba) {
		this.dba = dba;
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

	public List<String> getDbaSql() {
		return dbaSql;
	}

	public void setDbaSql(List<String> dbaSql) {
		this.dbaSql = dbaSql;
	}

}
