package org.kuali.common.impex.service;

import java.io.File;

public class VelocityAntTaskContext {

	File workingDir;
	String reportFileRelativePath;
	File reportFileActualFile;
	String reportFileCanonicalPath;
	File reportFileCanonicalFile;
	File contextPropertiesFile;
	String contextPropertiesCanonicalPath;
	File schemaFile;
	String schemaFileCanonicalPath;

	public File getWorkingDir() {
		return workingDir;
	}

	public void setWorkingDir(File workingDir) {
		this.workingDir = workingDir;
	}

	public String getReportFileRelativePath() {
		return reportFileRelativePath;
	}

	public void setReportFileRelativePath(String reportFileRelativePath) {
		this.reportFileRelativePath = reportFileRelativePath;
	}

	public File getReportFileActualFile() {
		return reportFileActualFile;
	}

	public void setReportFileActualFile(File reportFileActualFile) {
		this.reportFileActualFile = reportFileActualFile;
	}

	public File getContextPropertiesFile() {
		return contextPropertiesFile;
	}

	public void setContextPropertiesFile(File contextPropertiesFile) {
		this.contextPropertiesFile = contextPropertiesFile;
	}

	public File getSchemaFile() {
		return schemaFile;
	}

	public void setSchemaFile(File schemaFile) {
		this.schemaFile = schemaFile;
	}

	public String getReportFileCanonicalPath() {
		return reportFileCanonicalPath;
	}

	public void setReportFileCanonicalPath(String reportFileCanonicalPath) {
		this.reportFileCanonicalPath = reportFileCanonicalPath;
	}

	public String getContextPropertiesCanonicalPath() {
		return contextPropertiesCanonicalPath;
	}

	public void setContextPropertiesCanonicalPath(String contextPropertiesCanonicalPath) {
		this.contextPropertiesCanonicalPath = contextPropertiesCanonicalPath;
	}

	public File getReportFileCanonicalFile() {
		return reportFileCanonicalFile;
	}

	public void setReportFileCanonicalFile(File reportFileCanonicalFile) {
		this.reportFileCanonicalFile = reportFileCanonicalFile;
	}

	public String getSchemaFileCanonicalPath() {
		return schemaFileCanonicalPath;
	}

	public void setSchemaFileCanonicalPath(String schemaFileCanonicalPath) {
		this.schemaFileCanonicalPath = schemaFileCanonicalPath;
	}

}
