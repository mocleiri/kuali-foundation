package org.kuali.common.devops.aws.sysadmin.model;

import java.util.List;

import org.kuali.common.util.Assert;

import com.google.common.collect.ImmutableList;

/**
 * 
 */
public final class InstallTomcat7Context {

	private final String packageName;
	private final List<String> javaOpts;
	private final String installDir;

	public static class Builder {

		// Required
		private final String packageName = Packages.TOMCAT7.getName();
		private final String installDir = "/usr/share/tomcat7";
		private final List<String> javaOpts = ImmutableList.of();

		public InstallTomcat7Context build() {
			Assert.noBlanks(packageName, installDir);
			Assert.noNulls(javaOpts);
			return new InstallTomcat7Context(this);
		}

	}

	private InstallTomcat7Context(Builder builder) {
		this.packageName = builder.packageName;
		this.javaOpts = builder.javaOpts;
		this.installDir = builder.installDir;

	}

	public String getPackageName() {
		return packageName;
	}

	public List<String> getJavaOpts() {
		return javaOpts;
	}

	public String getInstallDir() {
		return installDir;
	}

}
