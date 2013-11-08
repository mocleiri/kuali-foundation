package org.kuali.common.devops.aws.sysadmin;

import java.util.List;

import org.kuali.common.devops.aws.sysadmin.model.SSHD;
import org.kuali.common.devops.aws.sysadmin.model.User;
import org.kuali.common.devops.aws.sysadmin.model.Users;
import org.kuali.common.util.Assert;
import org.kuali.common.util.channel.api.SecureChannelService;
import org.kuali.common.util.enc.EncUtils;

import com.google.common.collect.ImmutableList;

public final class BootstrapContext {

	private final SecureChannelService service;
	private final User sshEnabledUser;
	private final User root;
	private final String dnsName;
	private final String rootVolumeDeviceName;
	private final String privateKey;
	private final SSHD sshd;
	private final List<String> packages;

	public static class Builder {

		// Required
		private final SecureChannelService service;
		private final String dnsName;
		private final String privateKey;

		// Optional
		private User sshEnabledUser = Users.EC2USER.getUser();
		private User root = Users.ROOT.getUser();
		private String rootVolumeDeviceName = "/dev/xvda1";
		private SSHD sshd = new SSHD.Builder("classpath:org/kuali/common/kuali-devops/amazon-linux/2013.09/etc/ssh/sshd_config").build();
		private List<String> packages = ImmutableList.of("man", "zip", "unzip", "wget", "rsync", "openssh-clients", "subversion", "git");

		public Builder(SecureChannelService service, String dnsName, String privateKey) {
			this.service = service;
			this.dnsName = dnsName;
			this.privateKey = privateKey;
		}

		public Builder packages(List<String> packages) {
			this.packages = packages;
			return this;
		}

		public BootstrapContext build() {
			Assert.noNulls(service);
			Assert.noBlanks(privateKey);
			Assert.isFalse(EncUtils.isEncrypted(privateKey), "Private key is encrypted");
			return new BootstrapContext(this);
		}
	}

	private BootstrapContext(Builder builder) {
		this.service = builder.service;
		this.sshEnabledUser = builder.sshEnabledUser;
		this.root = builder.root;
		this.dnsName = builder.dnsName;
		this.rootVolumeDeviceName = builder.rootVolumeDeviceName;
		this.privateKey = builder.privateKey;
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

	public String getPrivateKey() {
		return privateKey;
	}

	public SSHD getSshd() {
		return sshd;
	}

	public List<String> getPackages() {
		return packages;
	}

}
