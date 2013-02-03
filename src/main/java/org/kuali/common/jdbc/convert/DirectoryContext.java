package org.kuali.common.jdbc.convert;

import java.io.File;

public class DirectoryContext {

	String database;
	File directory;
	String artifactId;
	SqlConverter converter;

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public File getDirectory() {
		return directory;
	}

	public void setDirectory(File directory) {
		this.directory = directory;
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
