package org.kuali.common.devops.aws.sysadmin;

import java.util.List;

import org.kuali.common.devops.aws.sysadmin.model.Deployable;
import org.kuali.common.devops.aws.sysadmin.model.InstallZipPackageContext;
import org.kuali.common.devops.aws.sysadmin.model.User;
import org.kuali.common.devops.aws.sysadmin.model.Users;
import org.kuali.common.util.Assert;
import org.kuali.common.util.VersionUtils;
import org.kuali.common.util.channel.api.SecureChannel;
import org.kuali.common.util.channel.util.ChannelExecutable;
import org.kuali.common.util.channel.util.ChannelUtils;

/**
 * Configure Tomcat
 */
public final class ConfigureTomcatExecutable implements ChannelExecutable {

	private final InstallZipPackageContext context;
	private final boolean skip;
	private final String majorVersion;
	private final User tomcat;

	public static class Builder {

		// Required
		private final InstallZipPackageContext context;
		private final String majorVersion;
		private final User tomcat = Users.TOMCAT.getUser();

		// Optional
		private boolean skip = false;

		public Builder(InstallZipPackageContext context) {
			this.context = context;
			this.majorVersion = VersionUtils.getVersion(context.getZipPackage().getArtifact().getVersion()).getMajor();
		}

		public Builder skip(boolean skip) {
			this.skip = skip;
			return this;
		}

		public ConfigureTomcatExecutable build() {
			Assert.noNulls(context, tomcat);
			Assert.noBlanks(majorVersion);
			return new ConfigureTomcatExecutable(this);
		}

	}

	private ConfigureTomcatExecutable(Builder builder) {
		this.context = builder.context;
		this.skip = builder.skip;
		this.majorVersion = builder.majorVersion;
		this.tomcat = builder.tomcat;
	}

	@Override
	public void execute(SecureChannel channel) {
		if (skip) {
			return;
		}
		String webappsDir = context.getInstallDir() + "/webapps";

		String command1 = "rm -rf " + webappsDir + "; mkdir -p " + webappsDir;
		ChannelUtils.exec(channel, command1);
		List<Deployable> deployables = TomcatUtils.getDeployables(context.getInstallDir(), majorVersion);
		for (Deployable deployable : deployables) {
			ChannelUtils.scp(channel, deployable.getSource(), deployable.getDestination());
		}

		boolean tomcatUserExists = channel.exists(tomcat.getHome());
		if (tomcatUserExists) {
			ChannelUtils.exec(channel, "userdel -rf " + tomcat.getLogin());
		}
		String command2 = "groupadd -f " + tomcat.getGroup();
		String command3 = "useradd -g " + tomcat.getGroup() + " " + tomcat.getLogin();
		ChannelUtils.exec(channel, command2, command3);
	}

	public boolean isSkip() {
		return skip;
	}

	public InstallZipPackageContext getContext() {
		return context;
	}

	public String getMajorVersion() {
		return majorVersion;
	}

}
