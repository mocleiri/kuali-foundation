package org.kuali.common.devops.aws.sysadmin.model;

import org.kuali.common.devops.model.Node;
import org.kuali.common.util.Assert;

public final class ServiceOverride {

	private final Node service;
	private final String configFileOverrideLocation;

	public static class Builder {

		private final Node service;
		private final String configFileOverrideLocation;

		public Builder(Node service, String configFileOverrideLocation) {
			this.service = service;
			this.configFileOverrideLocation = configFileOverrideLocation;
		}

		public ServiceOverride build() {
			Assert.noBlanks(configFileOverrideLocation);
			Assert.noNulls(service);
			Assert.exists(configFileOverrideLocation);
			return new ServiceOverride(this);
		}

	}

	private ServiceOverride(Builder builder) {
		this.service = builder.service;
		this.configFileOverrideLocation = builder.configFileOverrideLocation;
	}

	public Node getService() {
		return service;
	}

	public String getConfigFileOverrideLocation() {
		return configFileOverrideLocation;
	}

}
