package org.kuali.common.deploy.context;

import java.io.File;
import java.util.List;

import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.NoOpExecutable;

public class ApplicationContext {

	String groupId;
	String artifactId;
	String version;
	List<File> configFiles;
	File war;
	File jdbcDriver;
	Executable databaseResetExecutable = new NoOpExecutable();

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public List<File> getConfigFiles() {
		return configFiles;
	}

	public void setConfigFiles(List<File> configFiles) {
		this.configFiles = configFiles;
	}

	public File getWar() {
		return war;
	}

	public void setWar(File war) {
		this.war = war;
	}

	public File getJdbcDriver() {
		return jdbcDriver;
	}

	public void setJdbcDriver(File jdbcDriver) {
		this.jdbcDriver = jdbcDriver;
	}

}
