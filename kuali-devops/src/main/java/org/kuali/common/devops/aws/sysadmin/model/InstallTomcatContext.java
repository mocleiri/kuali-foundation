package org.kuali.common.devops.aws.sysadmin.model;

import java.io.File;

import org.kuali.common.util.Assert;
import org.kuali.common.util.channel.api.SecureChannelService;
import org.kuali.common.util.channel.model.ChannelContext;
import org.kuali.common.util.maven.RepositoryUtils;
import org.kuali.common.util.maven.model.Artifact;

public final class InstallTomcatContext {

	private final SecureChannelService service;
	private final ChannelContext context;
	private final Artifact artifact;
	private final File localRepositoryDir;
	private final String remoteTomcatDir;

	public static class Builder {

		private static final String GROUP_ID = "org.apache";
		private static final String ARTIFACT_ID = "apache-tomcat";
		private static final String TYPE = "zip";

		// Required
		private final SecureChannelService service;
		private final ChannelContext context;
		private final Artifact artifact;

		// Optional
		private File localRepositoryDir = RepositoryUtils.getDefaultLocalRepository();
		private String remoteTomcatDir = "/usr/local/tomcat";

		public Builder(SecureChannelService service, ChannelContext context, String version) {
			this(service, context, getDefaultArtifact(version));
		}

		public Builder(SecureChannelService service, ChannelContext context, Artifact artifact) {
			this.artifact = artifact;
			this.service = service;
			this.context = context;
		}

		private static Artifact getDefaultArtifact(String version) {
			Assert.noBlanks(version);
			return new Artifact.Builder(GROUP_ID, ARTIFACT_ID, version).type(TYPE).build();
		}

		public InstallTomcatContext build() {
			Assert.noNulls(artifact, localRepositoryDir);
			Assert.noBlanks(remoteTomcatDir);
			return new InstallTomcatContext(this);
		}
	}

	private InstallTomcatContext(Builder builder) {
		this.artifact = builder.artifact;
		this.service = builder.service;
		this.context = builder.context;
		this.localRepositoryDir = builder.localRepositoryDir;
		this.remoteTomcatDir = builder.remoteTomcatDir;
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

	public String getRemoteTomcatDir() {
		return remoteTomcatDir;
	}

}
