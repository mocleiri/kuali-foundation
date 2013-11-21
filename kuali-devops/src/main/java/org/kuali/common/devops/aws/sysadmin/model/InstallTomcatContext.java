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
	private final String usrShareDir;

	public static class Builder {

		// Required
		private final ChannelService service;
		private final ChannelContext context;

		// Optional
		private String packageName = Packages.TOMCAT7.getName();
		private List<String> javaOpts = ImmutableList.of();
		private String usrShareDir = "/usr/share";

		public Builder(ChannelService service, ChannelContext context) {
			this.service = service;
			this.context = context;
		}

		public InstallTomcatContext build() {
			Assert.noBlanks(packageName, usrShareDir);
			Assert.noNulls(javaOpts, service, context);
			return new InstallTomcatContext(this);
		}

	}

	private InstallTomcatContext(Builder builder) {
		this.service = builder.service;
		this.context = builder.context;
		this.packageName = builder.packageName;
		this.javaOpts = builder.javaOpts;
		this.usrShareDir = builder.usrShareDir;
	}

	public String getPackageName() {
		return packageName;
	}

	public List<String> getJavaOpts() {
		return javaOpts;
	}

	public ChannelService getService() {
		return service;
	}

	public ChannelContext getContext() {
		return context;
	}

	public String getUsrShareDir() {
		return usrShareDir;
	}

}
