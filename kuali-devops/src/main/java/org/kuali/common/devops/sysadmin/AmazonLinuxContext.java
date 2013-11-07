package org.kuali.common.devops.sysadmin;

import org.kuali.common.devops.sysadmin.model.User;
import org.kuali.common.util.Assert;
import org.kuali.common.util.channel.api.SecureChannelService;

public final class AmazonLinuxContext {

	private final SecureChannelService service;
	private final User ec2User;
	private final User root;
	private final String dnsName;
	private final String rootVolumeDeviceName;

	public static class Builder {

		// Required
		private final SecureChannelService service;
		private final String dnsName;

		// Optional
		private User ec2User = new User("ec2-user", "/home/ec2-user");
		private User root = new User("root", "/root");
		private String rootVolumeDeviceName = "/dev/xvda1";

		public Builder(SecureChannelService service, String dnsName) {
			this.service = service;
			this.dnsName = dnsName;
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
		this.dnsName = builder.dnsName;
		this.rootVolumeDeviceName = builder.rootVolumeDeviceName;
	}

	public SecureChannelService getService() {
		return service;
	}

	public User getEc2User() {
		return ec2User;
	}

	public User getRoot() {
		return root;
	}

	public String getDnsName() {
		return dnsName;
	}

	public String getRootVolumeDeviceName() {
		return rootVolumeDeviceName;
	}

}
