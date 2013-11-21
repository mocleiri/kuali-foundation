package org.kuali.common.devops.aws.sysadmin.model;

import java.util.List;

import org.kuali.common.util.Assert;
import org.kuali.common.util.channel.api.ChannelService;
import org.kuali.common.util.channel.model.ChannelContext;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

/**
 * 
 */
public final class InstallTomcatContext {

	private final ChannelService service;
	private final ChannelContext context;
	private final String packageName;
	private final List<String> javaOpts;
	private final String sharedDir;
	private final TomcatMajorVersion version;
	private final User user;
	private final Optional<String> javaHome;

	public static class Builder {

		// Required
		private final ChannelService service;
		private final ChannelContext context;

		// Optional
		private String packageName = Packages.TOMCAT7.getName();
		private final TomcatMajorVersion version = TomcatMajorVersion.SEVEN;
		private String sharedDir = "/usr/share";
		private User user = Users.TOMCAT.getUser();
		private Optional<String> javaHome = Optional.absent();
		private List<String> javaOpts = ImmutableList.of();

		public Builder(ChannelService service, ChannelContext context) {
			this.service = service;
			this.context = context;
		}

		public InstallTomcatContext build() {
			Assert.noBlanks(packageName, sharedDir);
			Assert.noNulls(javaOpts, service, context, version);
			return new InstallTomcatContext(this);
		}

	}

	private InstallTomcatContext(Builder builder) {
		this.service = builder.service;
		this.context = builder.context;
		this.packageName = builder.packageName;
		this.javaOpts = builder.javaOpts;
		this.sharedDir = builder.sharedDir;
		this.version = builder.version;
		this.user = builder.user;
		this.javaHome = builder.javaHome;
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

	public String getSharedDir() {
		return sharedDir;
	}

	public TomcatMajorVersion getVersion() {
		return version;
	}

	public User getUser() {
		return user;
	}

	public Optional<String> getJavaHome() {
		return javaHome;
	}

}
