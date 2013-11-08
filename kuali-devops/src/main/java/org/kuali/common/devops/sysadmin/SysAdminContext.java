package org.kuali.common.devops.sysadmin;

import java.util.List;

import org.kuali.common.devops.sysadmin.model.SSHD;
import org.kuali.common.devops.sysadmin.model.User;
import org.kuali.common.util.Assert;
import org.kuali.common.util.channel.api.SecureChannelService;
import org.kuali.common.util.enc.EncUtils;
import org.kuali.common.util.enc.KeyPair;

import com.google.common.collect.ImmutableList;

public final class SysAdminContext {

	private final SecureChannelService service;
	private final User sshEnabledUser;
	private final User root;
	private final String dnsName;
	private final String rootVolumeDeviceName;
	private final KeyPair keyPair;
	private final SSHD sshd;
	private final List<String> packages;

	public static class Builder {

		// Required
		private final SecureChannelService service;
		private final String dnsName;
		private final KeyPair keyPair;

		// Optional
		private User ec2User = new User("ec2-user", "/home/ec2-user");
		private User root = new User("root", "/root");
		private String rootVolumeDeviceName = "/dev/xvda1";
		private SSHD sshd = new SSHD("classpath:org/kuali/common/kuali-devops/amazon-linux/2013.09/etc/ssh/sshd_config");
		private List<String> packages = ImmutableList.of();

		public Builder(SecureChannelService service, String dnsName, KeyPair keyPair) {
			this.service = service;
			this.dnsName = dnsName;
			this.keyPair = keyPair;
		}

		public Builder packages(List<String> packages) {
			this.packages = packages;
			return this;
		}

		public SysAdminContext build() {
			Assert.noNulls(service, keyPair);
			Assert.isTrue(keyPair.getPublicKey().isPresent(), "Public key is required");
			Assert.isTrue(keyPair.getPrivateKey().isPresent(), "Private key is required");
			Assert.isFalse(EncUtils.isEncrypted(keyPair.getPrivateKey().get()), "Private key is encrypted");
			return new SysAdminContext(this);
		}
	}

	private SysAdminContext(Builder builder) {
		this.service = builder.service;
		this.sshEnabledUser = builder.ec2User;
		this.root = builder.root;
		this.dnsName = builder.dnsName;
		this.rootVolumeDeviceName = builder.rootVolumeDeviceName;
		this.keyPair = builder.keyPair;
		this.sshd = builder.sshd;
		this.packages = builder.packages;
	}

	public SecureChannelService getService() {
		return service;
	}

	public User getSshEnabledUser() {
		return sshEnabledUser;
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

	public SSHD getSshd() {
		return sshd;
	}

	public List<String> getPackages() {
		return packages;
	}

}
