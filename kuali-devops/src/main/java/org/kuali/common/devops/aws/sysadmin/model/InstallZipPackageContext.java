package org.kuali.common.devops.aws.sysadmin.model;

import java.io.File;

import org.kuali.common.util.Assert;
import org.kuali.common.util.channel.api.SecureChannelService;
import org.kuali.common.util.channel.model.ChannelContext;
import org.kuali.common.util.maven.RepositoryUtils;

public final class InstallZipPackageContext {

	private final SecureChannelService service;
	private final ChannelContext context;
	private final ZipPackage zipPackage;
	private final File localRepositoryDir;
	private final String remotePackageDir;

	public static class Builder {

		// Required
		private final SecureChannelService service;
		private final ChannelContext context;
		private final ZipPackage zipPackage;

		// Optional
		private File localRepositoryDir = RepositoryUtils.getDefaultLocalRepository();
		private String remotePackageDir = "/usr/local";

		public Builder(SecureChannelService service, ChannelContext context, ZipPackage zipPackage) {
			this.zipPackage = zipPackage;
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

		public InstallZipPackageContext build() {
			Assert.noNulls(service, context, zipPackage, localRepositoryDir);
			Assert.noBlanks(remotePackageDir);
			Assert.exists(RepositoryUtils.getFile(localRepositoryDir, zipPackage.getArtifact()));
			return new InstallZipPackageContext(this);
		}
	}

	private InstallZipPackageContext(Builder builder) {
		this.zipPackage = builder.zipPackage;
		this.service = builder.service;
		this.context = builder.context;
		this.localRepositoryDir = builder.localRepositoryDir;
		this.remotePackageDir = builder.remotePackageDir;
	}

	public SecureChannelService getService() {
		return service;
	}

	public ChannelContext getContext() {
		return context;
	}

	public ZipPackage getZipPackage() {
		return zipPackage;
	}

	public File getLocalRepositoryDir() {
		return localRepositoryDir;
	}

	public String getRemotePackageDir() {
		return remotePackageDir;
	}

}
