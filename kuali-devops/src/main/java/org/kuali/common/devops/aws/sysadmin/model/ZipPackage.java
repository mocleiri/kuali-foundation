package org.kuali.common.devops.aws.sysadmin.model;

import org.kuali.common.util.Assert;
import org.kuali.common.util.maven.model.Artifact;

/**
 * <p>
 * A named software package compressed into a zip file containing the version number as part of the filename. eg <code>apache-tomcat-7.0.47.zip</code>
 * </p>
 * 
 * <p>
 * When unzipped it must create a new directory that exactly matches the artifactId + version number. For example, unzipping <code>apache-tomcat-7.0.47.zip</code> into
 * <code>/usr/local</code> will produce <code>/usr/local/apache-tomcat-7.0.47</code>
 * </p>
 */
public final class ZipPackage {

	private final String name;
	private final Artifact artifact;

	public static class Builder {

		private static final String ZIP = "zip";

		// Required
		private final String name;
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
		public Builder(String name, Artifact artifact) {
			this.name = name;
			this.artifact = artifact;
		}

		public ZipPackage build() {
			Assert.noNulls(artifact);
			Assert.noBlanks(name);
			Assert.isTrue(artifact.getType().equalsIgnoreCase(ZIP));
			return new ZipPackage(this);
		}
	}

	private ZipPackage(Builder builder) {
		this.name = builder.name;
		this.artifact = builder.artifact;
	}

	public String getName() {
		return name;
	}

	public Artifact getArtifact() {
		return artifact;
	}

}
