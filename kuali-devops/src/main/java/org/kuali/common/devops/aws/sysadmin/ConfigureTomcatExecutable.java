package org.kuali.common.devops.aws.sysadmin;

import org.kuali.common.devops.aws.sysadmin.model.InstallZipPackageContext;
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

	public static class Builder {

		// Required
		private final InstallZipPackageContext context;
		private final String majorVersion;

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
			Assert.noNulls(context);
			Assert.noBlanks(majorVersion);
			return new ConfigureTomcatExecutable(this);
		}

	}

	private ConfigureTomcatExecutable(Builder builder) {
		this.context = builder.context;
		this.skip = builder.skip;
		this.majorVersion = builder.majorVersion;
	}

	@Override
	public void execute(SecureChannel channel) {
		if (skip) {
			return;
		}
		String installDir = context.getInstallDir();
		String webappsDir = installDir + "/webapps";
		// String serverXml = installDir + "/conf/server.xml";
		// String webXml = installDir + "/conf/web.xml";
		String command1 = "rm -rf " + webappsDir + "; mkdir -p " + webappsDir;

		ChannelUtils.exec(channel, command1);

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
