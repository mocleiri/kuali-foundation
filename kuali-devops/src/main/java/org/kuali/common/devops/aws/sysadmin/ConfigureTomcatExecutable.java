package org.kuali.common.devops.aws.sysadmin;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.devops.aws.sysadmin.model.Deployable;
import org.kuali.common.devops.aws.sysadmin.model.InstallZipPackageContext;
import org.kuali.common.devops.project.DevOpsProjectConstants;
import org.kuali.common.util.Assert;
import org.kuali.common.util.VersionUtils;
import org.kuali.common.util.channel.api.SecureChannel;
import org.kuali.common.util.channel.model.RemoteFile;
import org.kuali.common.util.channel.util.ChannelExecutable;
import org.kuali.common.util.channel.util.ChannelUtils;
import org.kuali.common.util.project.ProjectUtils;

import com.google.common.collect.ImmutableList;

/**
 * Configure Tomcat
 */
public final class ConfigureTomcatExecutable implements ChannelExecutable {

	private final InstallZipPackageContext context;
	private final boolean skip;
	private final String majorVersion;
	private final String classpathPrefix;

	public static class Builder {

		// Required
		private final InstallZipPackageContext context;
		private final String majorVersion;
		private final String classpathPrefix;

		// Optional
		private boolean skip = false;

		public Builder(InstallZipPackageContext context) {
			this.context = context;
			this.majorVersion = VersionUtils.getVersion(context.getZipPackage().getArtifact().getVersion()).getMajor();
			this.classpathPrefix = ProjectUtils.getClasspathPrefix(DevOpsProjectConstants.PROJECT_ID) + "/tomcat";
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
		this.classpathPrefix = builder.classpathPrefix;
	}

	@Override
	public void execute(SecureChannel channel) {
		if (skip) {
			return;
		}
		String installDir = context.getInstallDir();
		String webappsDir = installDir + "/webapps";

		List<Deployable> deployables = getDeployables();
		String command1 = "rm -rf " + webappsDir + "; mkdir -p " + webappsDir;

		ChannelUtils.exec(channel, command1);
		for (Deployable deployable : deployables) {
			ChannelUtils.scp(channel, deployable.getSource(), deployable.getDestination());
		}
	}

	protected List<Deployable> getDeployables() {
		List<Deployable> list = new ArrayList<Deployable>();
		list.addAll(getJsp());
		list.addAll(getBin());
		list.addAll(getConf());
		return ImmutableList.copyOf(list);
	}

	protected List<Deployable> getJsp() {
		String[] resources = { "env.jsp", "tail.jsp" };
		List<Deployable> list = new ArrayList<Deployable>();
		for (String resource : resources) {
			String src = classpathPrefix + "/jsps/" + resource;
			String absolutePath = context.getInstallDir() + "/logs/" + resource;
			RemoteFile dst = new RemoteFile.Builder(absolutePath).build();
			list.add(new Deployable(src, dst));
		}
		return list;
	}

	protected List<Deployable> getBin() {
		String[] resources = { "cleanup.sh", "forced-shutdown.sh" };
		List<Deployable> list = new ArrayList<Deployable>();
		for (String resource : resources) {
			String src = classpathPrefix + "/bin/" + resource;
			String absolutePath = context.getInstallDir() + "/bin/" + resource;
			RemoteFile dst = new RemoteFile.Builder(absolutePath).build();
			list.add(new Deployable(src, dst));
		}
		return list;
	}

	protected List<Deployable> getConf() {
		String[] resources = { "server.xml", "web.xml" };
		List<Deployable> list = new ArrayList<Deployable>();
		for (String resource : resources) {
			String src = classpathPrefix + "/" + majorVersion + "/conf/" + resource;
			String absolutePath = context.getInstallDir() + "/conf/" + resource;
			RemoteFile dst = new RemoteFile.Builder(absolutePath).build();
			list.add(new Deployable(src, dst));
		}
		return list;
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
