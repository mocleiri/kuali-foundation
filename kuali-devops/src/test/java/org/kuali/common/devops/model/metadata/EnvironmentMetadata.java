package org.kuali.common.devops.model.metadata;

import java.util.Map;
import java.util.Properties;

public class EnvironmentMetadata {

	MetadataUrl<String> tomcatVersion;
	MetadataUrl<Long> tomcatStartup;
	MetadataUrl<Map<String, String>> manifest;
	MetadataUrl<Properties> systemProperties;
	MetadataUrl<Properties> projectProperties;
	MetadataUrl<Properties> configProperties;

	public MetadataUrl<String> getTomcatVersion() {
		return tomcatVersion;
	}

	public void setTomcatVersion(MetadataUrl<String> tomcatVersion) {
		this.tomcatVersion = tomcatVersion;
	}

	public MetadataUrl<Long> getTomcatStartup() {
		return tomcatStartup;
	}

	public void setTomcatStartup(MetadataUrl<Long> tomcatStartup) {
		this.tomcatStartup = tomcatStartup;
	}

	public MetadataUrl<Map<String, String>> getManifest() {
		return manifest;
	}

	public void setManifest(MetadataUrl<Map<String, String>> manifest) {
		this.manifest = manifest;
	}

	public MetadataUrl<Properties> getSystemProperties() {
		return systemProperties;
	}

	public void setSystemProperties(MetadataUrl<Properties> systemProperties) {
		this.systemProperties = systemProperties;
	}

	public MetadataUrl<Properties> getEnvironmentProperties() {
		return environmentProperties;
	}

	public void setEnvironmentProperties(MetadataUrl<Properties> environmentProperties) {
		this.environmentProperties = environmentProperties;
	}

	public MetadataUrl<Properties> getProjectProperties() {
		return projectProperties;
	}

	public void setProjectProperties(MetadataUrl<Properties> projectProperties) {
		this.projectProperties = projectProperties;
	}

	public MetadataUrl<Properties> getConfigProperties() {
		return configProperties;
	}

	public void setConfigProperties(MetadataUrl<Properties> configProperties) {
		this.configProperties = configProperties;
	}

}
