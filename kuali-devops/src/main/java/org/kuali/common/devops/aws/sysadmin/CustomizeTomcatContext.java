package org.kuali.common.devops.aws.sysadmin;

import org.kuali.common.devops.aws.sysadmin.model.InstallZipPackageContext;
import org.kuali.common.devops.aws.sysadmin.model.User;
import org.kuali.common.devops.aws.sysadmin.model.Users;
import org.kuali.common.util.Assert;
import org.kuali.common.util.VersionUtils;

/**
 * Customize Tomcat
 */
public final class CustomizeTomcatContext {

	private final InstallZipPackageContext zip;
	private final String majorVersion;
	private final User tomcat;

	public static class Builder {

		// Required
		private final InstallZipPackageContext zip;
		private final String majorVersion;
		private final User tomcat = Users.TOMCAT.getUser();

		public Builder(InstallZipPackageContext context) {
			this.zip = context;
			this.majorVersion = VersionUtils.getVersion(context.getZipPackage().getArtifact().getVersion()).getMajor();
		}

		public CustomizeTomcatContext build() {
			Assert.noNulls(zip, tomcat);
			Assert.noBlanks(majorVersion);
			return new CustomizeTomcatContext(this);
		}

	}

	private CustomizeTomcatContext(Builder builder) {
		this.zip = builder.zip;
		this.majorVersion = builder.majorVersion;
		this.tomcat = builder.tomcat;
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

}
