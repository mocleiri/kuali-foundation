package org.kuali.maven.mojo.context;

public class JobContext {
	String name;
	String type;
	String template;
	String workingDir;
	String localFile;
	String timestampFormat;

	public String getWorkingDir() {
		return workingDir;
	}

	public void setWorkingDir(String configDir) {
		this.workingDir = configDir;
	}

	public String getLocalFile() {
		return localFile;
	}

	public void setLocalFile(String filename) {
		this.localFile = filename;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getTimestampFormat() {
		return timestampFormat;
	}

	public void setTimestampFormat(String timestampFormat) {
		this.timestampFormat = timestampFormat;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
