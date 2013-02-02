package org.kuali.common.util.service;

import java.io.File;
import java.io.PrintStream;
import java.util.List;

import org.kuali.common.util.ignore.Ignore;

public class MySqlDumpContext {

	List<Ignore> ignorers;
	String executable = MySqlDumpService.DEFAULT_EXECUTABLE;
	String username;
	String password;
	String hostname;
	int port = MySqlDumpService.DEFAULT_PORT;
	List<String> options;
	String database;
	List<String> tables;
	File outputFile;
	PrintStream out;

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

	public PrintStream getOut() {
		return out;
	}

	public void setOut(PrintStream out) {
		this.out = out;
	}

	public List<Ignore> getIgnorers() {
		return ignorers;
	}

	public void setIgnorers(List<Ignore> ignorers) {
		this.ignorers = ignorers;
	}

}
