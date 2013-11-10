package org.kuali.common.devops.aws.sysadmin.model;

import org.kuali.common.util.Assert;
import org.kuali.common.util.VersionUtils;

/**
 * Customize Tomcat
 */
public final class CustomizeTomcatContext {

	private final InstallZipPackageContext zip;
	private final String majorVersion;
	private final User tomcat;
	private final Bashrc bashrc;

	public static class Builder {

		// Required
		private final InstallZipPackageContext zip;
		private final Bashrc bashrc;

		// Optional
		private final User tomcat = Users.TOMCAT.getUser();

		// Filled in automatically
		private final String majorVersion;

		public Builder(InstallZipPackageContext context, Bashrc bashrc) {
			this.zip = context;
			this.bashrc = bashrc;
			this.majorVersion = VersionUtils.getVersion(context.getZipPackage().getArtifact().getVersion()).getMajor();
		}

		public CustomizeTomcatContext build() {
			Assert.noNulls(zip, tomcat, bashrc);
			Assert.noBlanks(majorVersion);
			return new CustomizeTomcatContext(this);
		}

	}

	private CustomizeTomcatContext(Builder builder) {
		this.zip = builder.zip;
		this.majorVersion = builder.majorVersion;
		this.tomcat = builder.tomcat;
		this.bashrc = builder.bashrc;
	}

	public InstallZipPackageContext getZip() {
		return zip;
	}

	public String getMajorVersion() {
		return majorVersion;
	}

	public User getTomcat() {
		return tomcat;
	}

	public Bashrc getBashrc() {
		return bashrc;
	}

}
