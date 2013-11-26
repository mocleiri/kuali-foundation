package org.kuali.common.devops.aws.sysadmin.model;

import org.kuali.common.util.Assert;

public final class ServiceOverride {

	private final DevOpsService service;
	private final String configFileOverrideLocation;

	public static class Builder {

		private final DevOpsService service;
		private final String configFileOverrideLocation;

		public Builder(DevOpsService service, String configFileOverrideLocation) {
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

	public DevOpsService getService() {
		return service;
	}

	public String getConfigFileOverrideLocation() {
		return configFileOverrideLocation;
	}

}
