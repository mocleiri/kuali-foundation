package org.kuali.common.jdbc;

import java.io.File;

public class SqlSource {

	String string;
	String location;
	File file;
	SqlSourceType type;

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public SqlSourceType getType() {
		return type;
	}

	public void setType(SqlSourceType type) {
		this.type = type;
	}

}
