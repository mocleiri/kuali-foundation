package org.kuali.core.db.torque.service;

import java.io.File;

public class VelocityCrap {

	File workingDir;
	String reportFileRelativePath;
	File reportFileActualFile;
	File contextPropertiesFile;
	File schemaFile;

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
}
