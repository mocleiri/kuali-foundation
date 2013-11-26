package org.kuali.common.devops.aws.sysadmin.model;

import java.util.List;

import org.kuali.common.aws.ec2.model.Distro;
import org.kuali.common.devops.project.DevOpsProjectConstants;
import org.kuali.common.util.Assert;
import org.kuali.common.util.channel.api.ChannelService;
import org.kuali.common.util.enc.EncUtils;
import org.kuali.common.util.project.ProjectUtils;

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
	private final Distro distro;
	private final String bootstrapCompletedAbsolutePath;

	public static class Builder {

		private static final String DISTROS = ProjectUtils.getClasspathPrefix(DevOpsProjectConstants.PROJECT_ID) + "/distros";

		// Required
		private final ChannelService service;
		private final String hostname;
		private final String privateKey;
		private final User sshEnabledUser = Users.EC2USER.getUser();
		private final User root = Users.ROOT.getUser();
		private final String bootstrapCompletedAbsolutePath = root.getHome() + "/.bootstrap/bootstrap.completed";
		private final Distro distro = Distro.AMAZON;

		// Optional (default values are usually ok)
		private String rootVolumeDeviceName = "/dev/xvda1";
		private List<String> packages = Packages.of(Packages.BASIC);

		// Filled in automatically by build();
		private ServiceOverride sshdOverride;

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

		public Builder packages(List<String> packages) {
			this.packages = packages;
			return this;
		}

		public BootstrapContext build() {
			Assert.noNulls(service, sshEnabledUser, root, distro, packages);
			Assert.noBlanks(hostname, privateKey, rootVolumeDeviceName);
			Assert.isFalse(EncUtils.isEncrypted(privateKey), "Private key is encrypted");
			this.packages = ImmutableList.copyOf(packages);

			Service sshd = Services.SSHD.getService();
			String configFileOverrideLocation = DISTROS + "/" + distro.getName() + sshd.getConfigFileAbsolutePath();
			this.sshdOverride = new ServiceOverride.Builder(sshd, configFileOverrideLocation).build();

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
		this.distro = builder.distro;
		this.bootstrapCompletedAbsolutePath = builder.bootstrapCompletedAbsolutePath;
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

	public Distro getDistro() {
		return distro;
	}

	public String getBootstrapCompletedAbsolutePath() {
		return bootstrapCompletedAbsolutePath;
	}

}
