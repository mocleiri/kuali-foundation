package org.kuali.core.db.torque.service;

import java.io.File;

public class VelocityFiles {

	File schemaFile;
	ReportFile reportFile;
	File contextPropertiesFile;

	public File getSchemaFile() {
		return schemaFile;
	}

	public void setSchemaFile(File schemaFile) {
		this.schemaFile = schemaFile;
	}

	public ReportFile getReportFile() {
		return reportFile;
	}

	public void setReportFile(ReportFile reportFile) {
		this.reportFile = reportFile;
	}

	public File getContextPropertiesFile() {
		return contextPropertiesFile;
	}

	public void setContextPropertiesFile(File contextPropertiesFile) {
		this.contextPropertiesFile = contextPropertiesFile;
	}

}
