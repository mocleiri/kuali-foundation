package org.kuali.common.devops.aws.sysadmin.model;

import org.kuali.common.util.Assert;
import org.kuali.common.util.maven.model.Artifact;

/**
 * <p>
 * A named software package compressed into a zip file containing the version number as part of the filename. eg <code>apache-tomcat-7.0.47.zip</code>
 * </p>
 * 
 * <p>
 * When unzipped it must create a new directory that exactly matches the artifactId + version number. For example, unzipping <code>apache-tomcat-7.0.47.zip</code> into the
 * <code>/usr/local</code> directory will produce <code>/usr/local/apache-tomcat-7.0.47</code>
 * </p>
 */
public final class Zip {

	private final String packageName;
	private final Artifact artifact;

	public static class Builder {

		private static final String ZIP = "zip";

		// Required
		private final String packageName;
		private final Artifact artifact;

		/**
		 * Use this constructor only if the package name exactly matches the artifact id
		 */
		public Builder(Artifact artifact) {
			this(artifact.getArtifactId(), artifact);
		}

		/**
		 * Use this constructor if the package name is different than the artifact id
		 */
		public Builder(String packageName, Artifact artifact) {
			this.packageName = packageName;
			this.artifact = artifact;
		}

		public Zip build() {
			Assert.noNulls(artifact);
			Assert.noBlanks(packageName);
			Assert.isTrue(artifact.getType().equalsIgnoreCase(ZIP));
			return new Zip(this);
		}
	}

	private Zip(Builder builder) {
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
