package org.kuali.common.devops.aws.sysadmin.model;

import java.io.File;

import org.kuali.common.util.Assert;
import org.kuali.common.util.channel.api.SecureChannelService;
import org.kuali.common.util.channel.model.ChannelContext;
import org.kuali.common.util.maven.RepositoryUtils;
import org.kuali.common.util.maven.model.Artifact;

public final class ZipPackageContext {

	private final SecureChannelService service;
	private final ChannelContext context;
	private final Artifact artifact;
	private final File localRepositoryDir;
	private final String packageName;
	private final String remotePackageDir;

	public static class Builder {

		private static final String PACKAGE_DIR = "/usr/local";

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
			this.remotePackageDir = PACKAGE_DIR + "/" + packageName;
		}

		public ZipPackageContext build() {
			Assert.noNulls(artifact, localRepositoryDir);
			Assert.noBlanks(packageName, remotePackageDir);
			return new ZipPackageContext(this);
		}
	}

	private ZipPackageContext(Builder builder) {
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

}
