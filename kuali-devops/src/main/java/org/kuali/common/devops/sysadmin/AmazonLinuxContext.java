package org.kuali.common.devops.sysadmin;

import org.kuali.common.devops.sysadmin.model.User;
import org.kuali.common.util.Assert;
import org.kuali.common.util.channel.api.SecureChannelService;
import org.kuali.common.util.enc.KeyPair;

public final class AmazonLinuxContext {

	private final SecureChannelService service;
	private final User ec2User;
	private final User root;
	private final String dnsName;
	private final String rootVolumeDeviceName;
	private final KeyPair keyPair;

	public static class Builder {

		// Required
		private final SecureChannelService service;
		private final String dnsName;
		private final KeyPair keyPair;

		// Optional
		private User ec2User = new User("ec2-user", "/home/ec2-user");
		private User root = new User("root", "/root");
		private String rootVolumeDeviceName = "/dev/xvda1";

		public Builder(SecureChannelService service, String dnsName, KeyPair keyPair) {
			this.service = service;
			this.dnsName = dnsName;
			this.keyPair = keyPair;
		}

		public AmazonLinuxContext build() {
			Assert.noNulls(service, keyPair);
			Assert.isTrue(keyPair.getPublicKey().isPresent(), "Public key is required");
			Assert.isTrue(keyPair.getPrivateKey().isPresent(), "Private key is required");
			return new AmazonLinuxContext(this);
		}
	}

	private AmazonLinuxContext(Builder builder) {
		this.service = builder.service;
		this.ec2User = builder.ec2User;
		this.root = builder.root;
		this.dnsName = builder.dnsName;
		this.rootVolumeDeviceName = builder.rootVolumeDeviceName;
		this.keyPair = builder.keyPair;
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

	public KeyPair getKeyPair() {
		return keyPair;
	}

}
