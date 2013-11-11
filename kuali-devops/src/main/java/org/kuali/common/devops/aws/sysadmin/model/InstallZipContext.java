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
	private final boolean skipIfInstalled;

	public static class Builder {

		// Required
		private final ChannelService service;
		private final ChannelContext context;
		private final Zip zip;

		// Optional
		private File localRepositoryDir = RepositoryUtils.getDefaultLocalRepository();
		private String remotePackageDir = "/usr/local";
		private boolean skipIfInstalled = true;

		// Filled in automatically, based off of remotePackageDir + package name
		private String installDir;

		public Builder(ChannelService service, ChannelContext context, Zip zip) {
			this.zip = zip;
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

		public Builder skipIfInstalled(boolean skipIfInstalled) {
			this.skipIfInstalled = skipIfInstalled;
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
		this.skipIfInstalled = builder.skipIfInstalled;
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

	public boolean isSkipIfInstalled() {
		return skipIfInstalled;
	}

}
