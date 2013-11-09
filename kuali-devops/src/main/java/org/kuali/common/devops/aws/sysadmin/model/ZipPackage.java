package org.kuali.common.devops.aws.sysadmin.model;

import org.kuali.common.util.Assert;
import org.kuali.common.util.maven.model.Artifact;

public final class ZipPackage {

	private final String packageName;
	private final Artifact artifact;

	public static class Builder {

		// Required
		private final String packageName;
		private final Artifact artifact;

		public Builder(String packageName, Artifact artifact) {
			this.packageName = packageName;
			this.artifact = artifact;
		}

		public ZipPackage build() {
			Assert.noNulls(artifact);
			Assert.noBlanks(packageName);
			return new ZipPackage(this);
		}
	}

	private ZipPackage(Builder builder) {
		this.packageName = builder.packageName;
		this.artifact = builder.artifact;
	}

	public String getPackageName() {
		return packageName;
	}

	public Artifact getArtifact() {
		return artifact;
	}

}
