package org.kuali.common.devops.aws.sysadmin.model;

import org.kuali.common.util.Assert;
import org.kuali.common.util.VersionUtils;

/**
 * Customize Tomcat
 */
public final class InstallTomcat7Context {

	private final InstallZipContext zip;
	private final String majorVersion;
	private final User tomcat;
	private final BashrcContext bashrc;

	public static class Builder {

		// Required
		private final InstallZipContext zip;
		private final BashrcContext bashrc;

		// Optional
		private final User tomcat = Users.TOMCAT.getUser();

		// Filled in automatically
		private final String majorVersion;

		public Builder(InstallZipContext context, BashrcContext bashrc) {
			this.zip = context;
			this.bashrc = bashrc;
			this.majorVersion = VersionUtils.getVersion(context.getZip().getArtifact().getVersion()).getMajor();
		}

		public InstallTomcat7Context build() {
			Assert.noNulls(zip, tomcat, bashrc);
			Assert.noBlanks(majorVersion);
			return new InstallTomcat7Context(this);
		}

	}

	private InstallTomcat7Context(Builder builder) {
		this.zip = builder.zip;
		this.majorVersion = builder.majorVersion;
		this.tomcat = builder.tomcat;
		this.bashrc = builder.bashrc;
	}

	public InstallZipContext getZip() {
		return zip;
	}

	public String getMajorVersion() {
		return majorVersion;
	}

	public User getTomcat() {
		return tomcat;
	}

	public BashrcContext getBashrc() {
		return bashrc;
	}

}
