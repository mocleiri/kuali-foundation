package org.kuali.common.devops.aws.sysadmin.model;

import java.io.File;

import org.kuali.common.util.Assert;

public final class Service {

	private final String name;
	private final String configFileLocation;
	private final String configFileName;

	public static class Builder {

		private final String name;
		private final String configFileLocation;
		private final String configFileName;

		public Builder(String name, String configFileLocation) {
			this(name, configFileLocation, new File(configFileLocation).getName());
		}

		public Builder(String name, String configFileLocation, String configFileName) {
			this.name = name;
			this.configFileLocation = configFileLocation;
			this.configFileName = configFileName;
		}

		public Service build() {
			Assert.noBlanks(name, configFileLocation, configFileName);
			return new Service(this);
		}

	}

	private Service(Builder builder) {
		this.name = builder.name;
		this.configFileLocation = builder.configFileLocation;
		this.configFileName = builder.configFileName;
	}

	public String getName() {
		return name;
	}

	public String getConfigFileLocation() {
		return configFileLocation;
	}

	public String getConfigFileName() {
		return configFileName;
	}

}
