package org.kuali.common.devops.sysadmin;

import org.kuali.common.devops.sysadmin.model.User;
import org.kuali.common.util.Assert;
import org.kuali.common.util.channel.api.SecureChannelService;

public final class AmazonLinuxContext {

	private final SecureChannelService service;
	private final User ec2User;
	private final User root;

	public static class Builder {

		// Required
		private final SecureChannelService service;

		// Optional
		private User ec2User = new User("ec2-user", "/home/ec2-user");
		private User root = new User("root", "/root");

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
		this.ec2User = builder.ec2User;
		this.root = builder.root;
	}

	public SecureChannelService getService() {
		return service;
	}

}
