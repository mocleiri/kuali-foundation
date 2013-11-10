package org.kuali.common.devops.aws.sysadmin.model;

import java.io.File;

import org.kuali.common.util.Assert;
import org.kuali.common.util.channel.api.ChannelService;
import org.kuali.common.util.channel.model.ChannelContext;
import org.kuali.common.util.maven.RepositoryUtils;

public final class InstallZipContext {

	private final ChannelService service;
	private final ChannelContext context;
	private final Zip zip;
	private final File localRepositoryDir;
	private final String remotePackageDir;
	private final String installDir;

	public static class Builder {

		// Required
		private final ChannelService service;
		private final ChannelContext context;
		private final Zip zip;

		// Optional
		private File localRepositoryDir = RepositoryUtils.getDefaultLocalRepository();
		private String remotePackageDir = "/usr/local";

		// Filled in automatically, based off of remotePackageDir + package name
		private String installDir;

		public Builder(ChannelService service, ChannelContext context, Zip zipPackage) {
			this.zip = zipPackage;
			this.service = service;
			this.context = context;
		}

		public Builder localRepositoryDir(File localRepositoryDir) {
			this.localRepositoryDir = localRepositoryDir;
			return this;
		}

		public Builder remotePackageDir(String remotePackageDir) {
			this.remotePackageDir = remotePackageDir;
			return this;
		}

		public InstallZipContext build() {
			Assert.noNulls(service, context, zip, localRepositoryDir);
			Assert.noBlanks(remotePackageDir);
			Assert.exists(RepositoryUtils.getFile(localRepositoryDir, zip.getArtifact()));
			this.installDir = remotePackageDir + "/" + zip.getPackageName();
			return new InstallZipContext(this);
		}
	}

	private InstallZipContext(Builder builder) {
		this.zip = builder.zip;
		this.service = builder.service;
		this.context = builder.context;
		this.localRepositoryDir = builder.localRepositoryDir;
		this.remotePackageDir = builder.remotePackageDir;
		this.installDir = builder.installDir;
	}

	public ChannelService getService() {
		return service;
	}

	public ChannelContext getContext() {
		return context;
	}

	public Zip getZip() {
		return zip;
	}

	public File getLocalRepositoryDir() {
		return localRepositoryDir;
	}

	public String getRemotePackageDir() {
		return remotePackageDir;
	}

	public String getInstallDir() {
		return installDir;
	}

}
