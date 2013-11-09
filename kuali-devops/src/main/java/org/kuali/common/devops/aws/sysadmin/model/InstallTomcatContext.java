package org.kuali.common.devops.aws.sysadmin.model;

import org.kuali.common.util.Assert;

public final class InstallTomcatContext {

	private final InstallZipPackageContext zipPackage;

	public static class Builder {

		// Required
		private final InstallZipPackageContext zipPackage;

		// Optional

		public Builder(InstallZipPackageContext zipPackage) {
			this.zipPackage = zipPackage;
		}

		public InstallTomcatContext build() {
			Assert.noNulls(zipPackage);
			return new InstallTomcatContext(this);
		}
	}

	private InstallTomcatContext(Builder builder) {
		this.zipPackage = builder.zipPackage;
	}

	public InstallZipPackageContext getZipPackage() {
		return zipPackage;
	}

}
