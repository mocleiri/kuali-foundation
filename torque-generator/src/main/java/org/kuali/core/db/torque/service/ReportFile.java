package org.kuali.core.db.torque.service;

import java.io.File;

public class ReportFile {

	File baseDir;
	String relativePath;
	File actualFile;

	public File getBaseDir() {
		return baseDir;
	}

	public void setBaseDir(File baseDir) {
		this.baseDir = baseDir;
	}

	public String getRelativePath() {
		return relativePath;
	}

	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}

	public File getActualFile() {
		return actualFile;
	}

	public void setActualFile(File actualFile) {
		this.actualFile = actualFile;
	}

}
