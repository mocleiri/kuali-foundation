package org.kuali.common.devops.aws.sysadmin.model;

import java.util.List;

import org.kuali.common.util.Assert;

import com.google.common.collect.ImmutableList;

public final class InstallTomcatContext {

	private final InstallZipPackageContext zipPackage;
	private final List<Deployable> deployables;

	public static class Builder {

		// Required
		private final InstallZipPackageContext zipPackage;

		// Optional
		private final List<Deployable> deployables = ImmutableList.of();

		public Builder(InstallZipPackageContext zipPackage) {
			this.zipPackage = zipPackage;
		}

		public InstallTomcatContext build() {
			Assert.noNulls(zipPackage, deployables);
			return new InstallTomcatContext(this);
		}
	}

	private InstallTomcatContext(Builder builder) {
		this.zipPackage = builder.zipPackage;
		this.deployables = builder.deployables;
	}

	public InstallZipPackageContext getZipPackage() {
		return zipPackage;
	}

}
