package org.kuali.common.devops.aws.sysadmin.model;

import org.kuali.common.util.Assert;

public final class Service {

	private final String instanceId;

	public static class Builder {

		private final String instanceId;

		public Builder(String instanceId) {
			this.instanceId = instanceId;
		}

		public Service build() {
			Assert.noBlanks(instanceId);
			return new Service(this);
		}

	}

	private Service(Builder builder) {
		this.instanceId = builder.instanceId;
	}

	public String getInstanceId() {
		return instanceId;
	}

}
