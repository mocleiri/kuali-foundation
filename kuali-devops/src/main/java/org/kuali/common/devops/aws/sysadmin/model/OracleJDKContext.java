package org.kuali.common.devops.aws.sysadmin.model;

import org.kuali.common.util.Assert;
import org.kuali.common.util.maven.model.Artifact;

public final class OracleJDKContext {

	private final Artifact artifact;

	public static class Builder {

		// Required
		private final Artifact artifact;

		public Builder(JDKLevel level, String version) {
			this.artifact = new Artifact.Builder("com.oracle", "jdk" + level.getVersion(), version).classifier("linux-x64").type("zip").build();
		}

		public Builder(Artifact artifact) {
			this.artifact = artifact;
		}

		public OracleJDKContext build() {
			Assert.noNulls(artifact);
			return new OracleJDKContext(this);
		}
	}

	private OracleJDKContext(Builder builder) {
		this.artifact = builder.artifact;
	}

}
