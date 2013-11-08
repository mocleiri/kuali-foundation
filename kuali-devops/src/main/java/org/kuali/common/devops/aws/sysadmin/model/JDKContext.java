package org.kuali.common.devops.aws.sysadmin.model;

import java.io.File;

import org.kuali.common.util.Assert;
import org.kuali.common.util.maven.RepositoryUtils;
import org.kuali.common.util.maven.model.Artifact;

public final class JDKContext {

	private final Artifact artifact;

	public static class Builder {

		private static final String GROUP_ID = "com.oracle";
		private static final String ARTIFACT_ID_PREFIX = "jdk";
		private static final String CLASSIFIER = "linux-x64";
		private static final String TYPE = "zip";

		// Required
		private final Artifact artifact;

		// Optional
		private File localRepositoryDir = RepositoryUtils.getDefaultLocalRepository();

		public Builder(JDKLevel level, String version) {
			this.artifact = new Artifact.Builder(GROUP_ID, ARTIFACT_ID_PREFIX + level.getVersion(), version).classifier(CLASSIFIER).type(TYPE).build();
		}

		public Builder(Artifact artifact) {
			this.artifact = artifact;
		}

		public JDKContext build() {
			Assert.noNulls(artifact, localRepositoryDir);
			Assert.exists(localRepositoryDir);
			Assert.isTrue(RepositoryUtils.exists(localRepositoryDir, artifact));
			return new JDKContext(this);
		}
	}

	private JDKContext(Builder builder) {
		this.artifact = builder.artifact;
	}

	public Artifact getArtifact() {
		return artifact;
	}

}
