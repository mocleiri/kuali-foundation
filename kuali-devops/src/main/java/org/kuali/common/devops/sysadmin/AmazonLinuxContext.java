package org.kuali.common.devops.sysadmin;

import org.kuali.common.util.Assert;
import org.kuali.common.util.channel.api.SecureChannelService;

public final class AmazonLinuxContext {

	private final SecureChannelService service;

	public static class Builder {

		private final SecureChannelService service;

		public Builder(SecureChannelService service) {
			this.service = service;
		}

		public AmazonLinuxContext build() {
			Assert.noNulls(service);
			return new AmazonLinuxContext(this);
		}
	}

	private AmazonLinuxContext(Builder builder) {
		this.service = builder.service;
	}

	public SecureChannelService getService() {
		return service;
	}

}
