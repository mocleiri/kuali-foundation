package org.kuali.common.devops.aws.sysadmin.model;

import java.io.File;

import org.kuali.common.util.Assert;
import org.kuali.common.util.channel.api.SecureChannelService;
import org.kuali.common.util.maven.RepositoryUtils;
import org.kuali.common.util.maven.model.Artifact;

public final class InstallJDKContext {

	private final Artifact artifact;

	public static class Builder {

		private static final String GROUP_ID = "com.oracle";
		private static final String ARTIFACT_ID_PREFIX = "jdk";
		private static final String CLASSIFIER = "linux-x64";
		private static final String TYPE = "zip";

		// Required
		private final Artifact artifact;
		private final SecureChannelService service;
		private final String privateKey;
		private final String hostname;

		// Optional
		private File localRepositoryDir = RepositoryUtils.getDefaultLocalRepository();
		private String remoteJavaDir = "/usr/local/java";
		private String username = "root";

		public Builder(JDKLevel level, String version) {
			this.artifact = new Artifact.Builder(GROUP_ID, ARTIFACT_ID_PREFIX + level.getVersion(), version).classifier(CLASSIFIER).type(TYPE).build();
		}

		public Builder(Artifact artifact) {
			this.artifact = artifact;
		}

		public InstallJDKContext build() {
			Assert.noNulls(artifact, localRepositoryDir);
			Assert.exists(localRepositoryDir);
			Assert.isTrue(RepositoryUtils.exists(localRepositoryDir, artifact));
			return new InstallJDKContext(this);
		}
	}

	private InstallJDKContext(Builder builder) {
		this.artifact = builder.artifact;
	}

	public Artifact getArtifact() {
		return artifact;
	}

}
