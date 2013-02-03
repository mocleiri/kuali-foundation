package org.kuali.common.jdbc.convert;

import java.io.File;

public class DatabaseContext {

	File oldDir;
	File newDir;
	String artifactId;
	SqlConverter converter;

	public File getOldDir() {
		return oldDir;
	}

	public void setOldDir(File oldDir) {
		this.oldDir = oldDir;
	}

	public File getNewDir() {
		return newDir;
	}

	public void setNewDir(File newDir) {
		this.newDir = newDir;
	}

	public String getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	public SqlConverter getConverter() {
		return converter;
	}

	public void setConverter(SqlConverter converter) {
		this.converter = converter;
	}

}
