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
public final class CustomizeTomcatExecutable implements ChannelExecutable {

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

		public CustomizeTomcatExecutable build() {
			Assert.noNulls(context, tomcat);
			Assert.noBlanks(majorVersion);
			return new CustomizeTomcatExecutable(this);
		}

	}

	private CustomizeTomcatExecutable(Builder builder) {
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

		// Remove, then recreate the webapps dir to get rid of all the junk that's in there by default (docs, manager app, etc)
		ChannelUtils.exec(channel, "rm -rf " + webappsDir + "; mkdir -p " + webappsDir);

		// Add, update, replace configuration files as needed
		List<Deployable> deployables = TomcatUtils.getDeployables(context.getInstallDir(), majorVersion);
		for (Deployable deployable : deployables) {
			ChannelUtils.scp(channel, deployable.getSource(), deployable.getDestination());
		}

		// Delete the tomcat user (if it exists)
		boolean tomcatUserExists = channel.exists(tomcat.getHome());
		if (tomcatUserExists) {
			ChannelUtils.exec(channel, "userdel -rf " + tomcat.getLogin());
		}

		// Create the tomcat group and user
		String command1 = "groupadd -f " + tomcat.getGroup();
		String command2 = "useradd -g " + tomcat.getGroup() + " " + tomcat.getLogin();

		// Recursively chown everything in /usr/local/tomcat and /home/tomcat to tomcat:tomcat
		String dir1 = context.getInstallDir();
		String dir2 = tomcat.getHome();
		String command3 = "chown -RL " + tomcat.getGroup() + ":" + tomcat.getLogin() + " " + dir1 + " " + dir2;

		// Remove the annoying windows .bat files
		String command4 = "rm " + context.getInstallDir() + "/bin/*.bat";

		// Make everything in the bin directory executable
		String command5 = "chmod -R 755 " + context.getInstallDir() + "/bin";

		// Invoke the commands
		ChannelUtils.exec(channel, command1, command2, command3, command4, command5);
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
