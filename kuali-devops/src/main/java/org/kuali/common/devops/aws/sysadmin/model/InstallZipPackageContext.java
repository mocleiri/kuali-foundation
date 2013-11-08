package org.kuali.common.devops.aws.sysadmin.model;

import java.io.File;

import org.kuali.common.util.Assert;
import org.kuali.common.util.channel.api.SecureChannelService;
import org.kuali.common.util.channel.model.ChannelContext;
import org.kuali.common.util.maven.RepositoryUtils;
import org.kuali.common.util.maven.model.Artifact;

public final class InstallZipPackageContext {

	private final SecureChannelService service;
	private final ChannelContext context;
	private final Artifact artifact;
	private final File localRepositoryDir;
	private final String packageName;
	private final String remotePackageDir;

	public static class Builder {

		private static final String USR_LOCAL = "/usr/local";

		// Required
		private final SecureChannelService service;
		private final ChannelContext context;
		private final Artifact artifact;
		private final String packageName;

		// Optional
		private File localRepositoryDir = RepositoryUtils.getDefaultLocalRepository();
		private String remotePackageDir;

		public Builder(SecureChannelService service, ChannelContext context, Artifact artifact, String packageName) {
			this.artifact = artifact;
			this.service = service;
			this.context = context;
			this.packageName = packageName;
			this.remotePackageDir = USR_LOCAL + "/" + packageName;
		}

		public Builder localRepositoryDir(File localRepositoryDir) {
			this.localRepositoryDir = localRepositoryDir;
			return this;
		}

		public Builder remotePackageDir(String remotePackageDir) {
			this.remotePackageDir = remotePackageDir;
			return this;
		}

		public InstallZipPackageContext build() {
			Assert.noNulls(service, context, artifact, localRepositoryDir);
			Assert.noBlanks(packageName, remotePackageDir);
			Assert.exists(RepositoryUtils.getFile(localRepositoryDir, artifact));
			return new InstallZipPackageContext(this);
		}
	}

	private InstallZipPackageContext(Builder builder) {
		this.artifact = builder.artifact;
		this.service = builder.service;
		this.context = builder.context;
		this.localRepositoryDir = builder.localRepositoryDir;
		this.remotePackageDir = builder.remotePackageDir;
		this.packageName = builder.packageName;
	}

	public Artifact getArtifact() {
		return artifact;
	}

	public SecureChannelService getService() {
		return service;
	}

	public ChannelContext getContext() {
		return context;
	}

	public File getLocalRepositoryDir() {
		return localRepositoryDir;
	}

	public String getPackageName() {
		return packageName;
	}

	public String getRemotePackageDir() {
		return remotePackageDir;
	}

}
