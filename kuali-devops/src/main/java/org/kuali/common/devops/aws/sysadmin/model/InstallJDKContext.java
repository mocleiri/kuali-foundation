package org.kuali.common.devops.aws.sysadmin.model;

import java.io.File;

import org.kuali.common.util.Assert;
import org.kuali.common.util.channel.api.SecureChannelService;
import org.kuali.common.util.channel.model.ChannelContext;
import org.kuali.common.util.maven.RepositoryUtils;
import org.kuali.common.util.maven.model.Artifact;

public final class InstallJDKContext {

	private final SecureChannelService service;
	private final ChannelContext context;
	private final Artifact artifact;
	private final File localRepositoryDir;
	private final String remoteJavaDir;

	public static class Builder {

		private static final String GROUP_ID = "com.oracle";
		private static final String ARTIFACT_ID_PREFIX = "jdk";
		private static final String CLASSIFIER = "linux-x64";
		private static final String TYPE = "zip";

		// Required
		private final SecureChannelService service;
		private final ChannelContext context;
		private final Artifact artifact;

		// Optional
		private File localRepositoryDir = RepositoryUtils.getDefaultLocalRepository();
		private String remoteJavaDir = "/usr/local/java";

		public Builder(SecureChannelService service, ChannelContext context, JDKLevel level, String version) {
			this(service, context, getDefaultArtifact(level, version));
		}

		public Builder(SecureChannelService service, ChannelContext context, Artifact artifact) {
			this.artifact = artifact;
			this.service = service;
			this.context = context;
		}

		private static Artifact getDefaultArtifact(JDKLevel level, String version) {
			Assert.noNulls(level);
			Assert.noBlanks(version);
			return new Artifact.Builder(GROUP_ID, ARTIFACT_ID_PREFIX + level.getVersion(), version).classifier(CLASSIFIER).type(TYPE).build();
		}

		public InstallJDKContext build() {
			Assert.noNulls(artifact, localRepositoryDir);
			Assert.noBlanks(remoteJavaDir);
			return new InstallJDKContext(this);
		}
	}

	private InstallJDKContext(Builder builder) {
		this.artifact = builder.artifact;
		this.service = builder.service;
		this.context = builder.context;
		this.localRepositoryDir = builder.localRepositoryDir;
		this.remoteJavaDir = builder.remoteJavaDir;
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

	public String getRemoteJavaDir() {
		return remoteJavaDir;
	}

}
