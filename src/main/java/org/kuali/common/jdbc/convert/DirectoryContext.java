package org.kuali.common.jdbc.convert;

import java.io.File;

public class DirectoryContext {

	File directory;
	SqlConverter converter;
	String include;
	String exclude;

	public File getDirectory() {
		return directory;
	}

	public void setDirectory(File directory) {
		this.directory = directory;
	}

	public SqlConverter getConverter() {
		return converter;
	}

	public void setConverter(SqlConverter converter) {
		this.converter = converter;
	}

	public String getInclude() {
		return include;
	}

	public void setInclude(String include) {
		this.include = include;
	}

	public String getExclude() {
		return exclude;
	}

	public void setExclude(String exclude) {
		this.exclude = exclude;
	}

}
