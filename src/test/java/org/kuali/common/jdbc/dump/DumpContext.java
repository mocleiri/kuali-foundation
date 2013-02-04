package org.kuali.common.jdbc.dump;

import java.io.File;
import java.util.List;

public class DumpContext {

	File schemaXmlFile;
	String schemaName;
	String username;
	String password;
	String driver;
	String url;
	String artifactId;
	String vendor;
	List<String> tableIncludes;
	List<String> tableExcludes;
	List<String> viewIncludes;
	List<String> viewExcludes;
	List<String> sequenceIncludes;
	List<String> sequenceExcludes;
	String comment;
	String dateFormat;
	File dataXMLDir;
	File buildDirectory;
	boolean processTables = true;
	boolean processSequences = true;
	boolean processViews = true;
	boolean printMetaInfLists = true;

	public File getSchemaXmlFile() {
		return schemaXmlFile;
	}

	public void setSchemaXmlFile(File schemaXmlFile) {
		this.schemaXmlFile = schemaXmlFile;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
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

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public File getDataXMLDir() {
		return dataXMLDir;
	}

	public void setDataXMLDir(File dataXMLDir) {
		this.dataXMLDir = dataXMLDir;
	}

	public File getBuildDirectory() {
		return buildDirectory;
	}

	public void setBuildDirectory(File buildDirectory) {
		this.buildDirectory = buildDirectory;
	}

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

}
