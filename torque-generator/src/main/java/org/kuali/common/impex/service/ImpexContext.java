package org.kuali.common.impex.service;

import java.io.File;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.torque.engine.platform.Platform;

public class ImpexContext {

	public static final String DATA_CONTROL_TEMPLATE = "data/Control.vm";
	public static final String MPX_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZZ";

	boolean processTables = true;
	boolean processSequences = true;
	boolean processViews = true;
	boolean printMetaInfLists = false;
	boolean antCompatibilityMode = false;
	int metaDataThreads = 5;
	int dataThreads = 15;
	String controlTemplate = DATA_CONTROL_TEMPLATE;
	String comment = "Generated by impex-db-dump";
	String encoding = "UTF-8";
	String dateFormat = MPX_DATE_FORMAT;
	int rowCountInterval = 50;
	int dataSizeInterval = 50 * 1024;
	DataHandler dataHandler = new DefaultDataHandler();
	// oracle, mysql, etc
	String databaseVendor;
	File schemaXmlFile;
	String schema;
	String username;
	String password;
	String driver;
	String url;
	String artifactId;
	List<String> tableIncludes;
	List<String> tableExcludes;
	List<String> viewIncludes;
	List<String> viewExcludes;
	List<String> sequenceIncludes;
	List<String> sequenceExcludes;
	File workingDir;
	File buildDir;
	File baseDir;
	DataSource dataSource;
	Platform platform;
	Properties databaseTableProperties;
	String databaseTablePropertiesLocation;
	boolean storeDatabaseTableProperties;

	public boolean isProcessTables() {
		return processTables;
	}

	public void setProcessTables(boolean processTables) {
		this.processTables = processTables;
	}

	public boolean isProcessSequences() {
		return processSequences;
	}

	public void setProcessSequences(boolean processSequences) {
		this.processSequences = processSequences;
	}

	public boolean isProcessViews() {
		return processViews;
	}

	public void setProcessViews(boolean processViews) {
		this.processViews = processViews;
	}

	public boolean isPrintMetaInfLists() {
		return printMetaInfLists;
	}

	public void setPrintMetaInfLists(boolean printMetaInfLists) {
		this.printMetaInfLists = printMetaInfLists;
	}

	public boolean isAntCompatibilityMode() {
		return antCompatibilityMode;
	}

	public void setAntCompatibilityMode(boolean antCompatibilityMode) {
		this.antCompatibilityMode = antCompatibilityMode;
	}

	public int getMetaDataThreads() {
		return metaDataThreads;
	}

	public void setMetaDataThreads(int metaDataThreads) {
		this.metaDataThreads = metaDataThreads;
	}

	public int getDataThreads() {
		return dataThreads;
	}

	public void setDataThreads(int dataThreads) {
		this.dataThreads = dataThreads;
	}

	public String getControlTemplate() {
		return controlTemplate;
	}

	public void setControlTemplate(String controlTemplate) {
		this.controlTemplate = controlTemplate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public String getDatabaseVendor() {
		return databaseVendor;
	}

	public void setDatabaseVendor(String databaseVendor) {
		this.databaseVendor = databaseVendor;
	}

	public File getSchemaXmlFile() {
		return schemaXmlFile;
	}

	public void setSchemaXmlFile(File schemaXmlFile) {
		this.schemaXmlFile = schemaXmlFile;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	public List<String> getTableIncludes() {
		return tableIncludes;
	}

	public void setTableIncludes(List<String> tableIncludes) {
		this.tableIncludes = tableIncludes;
	}

	public List<String> getTableExcludes() {
		return tableExcludes;
	}

	public void setTableExcludes(List<String> tableExcludes) {
		this.tableExcludes = tableExcludes;
	}

	public List<String> getViewIncludes() {
		return viewIncludes;
	}

	public void setViewIncludes(List<String> viewIncludes) {
		this.viewIncludes = viewIncludes;
	}

	public List<String> getViewExcludes() {
		return viewExcludes;
	}

	public void setViewExcludes(List<String> viewExcludes) {
		this.viewExcludes = viewExcludes;
	}

	public List<String> getSequenceIncludes() {
		return sequenceIncludes;
	}

	public void setSequenceIncludes(List<String> sequenceIncludes) {
		this.sequenceIncludes = sequenceIncludes;
	}

	public List<String> getSequenceExcludes() {
		return sequenceExcludes;
	}

	public void setSequenceExcludes(List<String> sequenceExcludes) {
		this.sequenceExcludes = sequenceExcludes;
	}

	public File getWorkingDir() {
		return workingDir;
	}

	public void setWorkingDir(File workingDir) {
		this.workingDir = workingDir;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

	public DataHandler getDataHandler() {
		return dataHandler;
	}

	public void setDataHandler(DataHandler dataHandler) {
		this.dataHandler = dataHandler;
	}

	public int getRowCountInterval() {
		return rowCountInterval;
	}

	public void setRowCountInterval(int rowCountInterval) {
		this.rowCountInterval = rowCountInterval;
	}

	public int getDataSizeInterval() {
		return dataSizeInterval;
	}

	public void setDataSizeInterval(int dataSizeInterval) {
		this.dataSizeInterval = dataSizeInterval;
	}

	public File getBuildDir() {
		return buildDir;
	}

	public void setBuildDir(File buildDir) {
		this.buildDir = buildDir;
	}

	public File getBaseDir() {
		return baseDir;
	}

	public void setBaseDir(File baseDir) {
		this.baseDir = baseDir;
	}

	public Properties getDatabaseTableProperties() {
		return databaseTableProperties;
	}

	public void setDatabaseTableProperties(Properties databaseTableProperties) {
		this.databaseTableProperties = databaseTableProperties;
	}

	public String getDatabaseTablePropertiesLocation() {
		return databaseTablePropertiesLocation;
	}

	public void setDatabaseTablePropertiesLocation(String databaseTablePropertiesLocation) {
		this.databaseTablePropertiesLocation = databaseTablePropertiesLocation;
	}

	public boolean isStoreDatabaseTableProperties() {
		return storeDatabaseTableProperties;
	}

	public void setStoreDatabaseTableProperties(boolean storeDatabaseTableProperties) {
		this.storeDatabaseTableProperties = storeDatabaseTableProperties;
	}
}