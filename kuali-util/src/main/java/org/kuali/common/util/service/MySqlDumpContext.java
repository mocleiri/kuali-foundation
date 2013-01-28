package org.kuali.common.util.service;

import java.io.File;
import java.util.List;

public class MySqlDumpContext {

	String executable = MySqlDumpService.DEFAULT_EXECUTABLE;
	int port = MySqlDumpService.DEFAULT_PORT;
	String skipLinePrefix = MySqlDumpService.SKIP_LINE_PREFIX;
	String skipLineSuffix = MySqlDumpService.SKIP_LINE_SUFFIX;
	String username;
	String password;
	String hostname;
	String database;
	List<String> tables;
	List<String> options;
	File outputFile;

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

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public File getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(File outputFile) {
		this.outputFile = outputFile;
	}

	public String getExecutable() {
		return executable;
	}

	public void setExecutable(String executable) {
		this.executable = executable;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public List<String> getTables() {
		return tables;
	}

	public void setTables(List<String> tables) {
		this.tables = tables;
	}

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

}
