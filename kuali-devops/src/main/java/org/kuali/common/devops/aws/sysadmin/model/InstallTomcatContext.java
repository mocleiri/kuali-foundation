package org.kuali.common.devops.aws.sysadmin.model;

import java.util.List;

import org.kuali.common.util.Assert;
import org.kuali.common.util.channel.api.ChannelService;
import org.kuali.common.util.channel.model.ChannelContext;

import com.google.common.collect.ImmutableList;

/**
 * 
 */
public final class InstallTomcatContext {

	private final ChannelService service;
	private final ChannelContext context;
	private final String packageName;
	private final List<String> javaOpts;
	private final String installDir;

	public static class Builder {

		// Required
		private final ChannelService service;
		private final ChannelContext context;

		// Optional
		private String packageName = Packages.TOMCAT7.getName();
		private String installDir = "/usr/share/tomcat7";
		private List<String> javaOpts = ImmutableList.of();

		public Builder(ChannelService service, ChannelContext context) {
			this.service = service;
			this.context = context;
		}

		public InstallTomcatContext build() {
			Assert.noBlanks(packageName, installDir);
			Assert.noNulls(javaOpts, service, context);
			return new InstallTomcatContext(this);
		}

	}

	private InstallTomcatContext(Builder builder) {
		this.service = builder.service;
		this.context = builder.context;
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

	public ChannelService getService() {
		return service;
	}

	public ChannelContext getContext() {
		return context;
	}

}
