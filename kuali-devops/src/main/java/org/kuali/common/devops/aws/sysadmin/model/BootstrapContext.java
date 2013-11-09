package org.kuali.common.devops.aws.sysadmin.model;

import java.util.List;

import org.kuali.common.util.Assert;
import org.kuali.common.util.channel.api.ChannelService;
import org.kuali.common.util.enc.EncUtils;

import com.google.common.collect.ImmutableList;

public final class BootstrapContext {

	private final ChannelService service;
	private final User sshEnabledUser;
	private final User root;
	private final String hostname;
	private final String rootVolumeDeviceName;
	private final String privateKey;
	private final ServiceOverride sshdOverride;
	private final List<String> packages;

	public static class Builder {

		private static final String SSHD_OVERRIDE_CONFIG = "classpath:org/kuali/common/kuali-devops/amazon-linux/2013.09/etc/ssh/sshd_config";
		private static final List<String> PACKAGES = ImmutableList.of("man", "zip", "unzip", "wget", "rsync", "openssh-clients", "subversion", "git");
		private static final String ROOT_VOLUME_DEVICE_NAME = "/dev/xvda1";

		// Required
		private final ChannelService service;
		private final String hostname;
		private final String privateKey;

		// Optional
		private User sshEnabledUser = Users.EC2USER.getUser();
		private User root = Users.ROOT.getUser();
		private String rootVolumeDeviceName = ROOT_VOLUME_DEVICE_NAME;
		private ServiceOverride sshdOverride = new ServiceOverride.Builder(Services.SSHD.getService(), SSHD_OVERRIDE_CONFIG).build();
		private List<String> packages = PACKAGES;

		public Builder(ChannelService service, String hostname, String privateKey) {
			this.service = service;
			this.hostname = hostname;
			this.privateKey = privateKey;
		}

		public Builder sshdOverride(ServiceOverride sshdOverride) {
			this.sshdOverride = sshdOverride;
			return this;
		}

		public Builder rootVolumeDeviceName(String rootVolumeDeviceName) {
			this.rootVolumeDeviceName = rootVolumeDeviceName;
			return this;
		}

		public Builder root(User root) {
			this.root = root;
			return this;
		}

		public Builder sshEnabledUser(User sshEnabledUser) {
			this.sshEnabledUser = sshEnabledUser;
			return this;
		}

		public Builder packages(List<String> packages) {
			this.packages = packages;
			return this;
		}

		public BootstrapContext build() {
			Assert.noNulls(service, root, sshEnabledUser, sshdOverride);
			Assert.noBlanks(hostname, privateKey, rootVolumeDeviceName);
			Assert.isFalse(EncUtils.isEncrypted(privateKey), "Private key is encrypted");
			this.packages = ImmutableList.copyOf(packages);
			return new BootstrapContext(this);
		}
	}

	private BootstrapContext(Builder builder) {
		this.service = builder.service;
		this.sshEnabledUser = builder.sshEnabledUser;
		this.root = builder.root;
		this.hostname = builder.hostname;
		this.rootVolumeDeviceName = builder.rootVolumeDeviceName;
		this.privateKey = builder.privateKey;
		this.sshdOverride = builder.sshdOverride;
		this.packages = builder.packages;
	}

	public ChannelService getService() {
		return service;
	}

	public User getSshEnabledUser() {
		return sshEnabledUser;
	}

	public User getRoot() {
		return root;
	}

	public String getHostname() {
		return hostname;
	}

	public String getRootVolumeDeviceName() {
		return rootVolumeDeviceName;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public List<String> getPackages() {
		return packages;
	}

	public ServiceOverride getSshdOverride() {
		return sshdOverride;
	}

}
