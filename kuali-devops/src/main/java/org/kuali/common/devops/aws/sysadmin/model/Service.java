package org.kuali.common.devops.aws.sysadmin.model;

import java.io.File;

import org.kuali.common.util.Assert;

public final class Service {

	private final String name;
	private final String configFileAbsolutePath;
	private final String configFileName;

	public static class Builder {

		private final String name;
		private final String configFileAbsolutePath;
		private final String configFileName;

		public Builder(String name, String configFileAbsolutePath) {
			this(name, configFileAbsolutePath, new File(configFileAbsolutePath).getName());
		}

		public Builder(String name, String configFileAbsolutePath, String configFileName) {
			this.name = name;
			this.configFileAbsolutePath = configFileAbsolutePath;
			this.configFileName = configFileName;
		}

		public Service build() {
			Assert.noBlanks(name, configFileAbsolutePath, configFileName);
			return new Service(this);
		}

	}

	private Service(Builder builder) {
		this.name = builder.name;
		this.configFileAbsolutePath = builder.configFileAbsolutePath;
		this.configFileName = builder.configFileName;
	}

	public String getName() {
		return name;
	}

	public String getConfigFileAbsolutePath() {
		return configFileAbsolutePath;
	}

	public String getConfigFileName() {
		return configFileName;
	}

}
