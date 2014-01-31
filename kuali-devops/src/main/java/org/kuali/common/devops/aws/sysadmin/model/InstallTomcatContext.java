package org.kuali.common.devops.aws.sysadmin.model;

import org.kuali.common.devops.model.Heap;
import org.kuali.common.devops.model.Java;
import org.kuali.common.devops.model.Package;
import org.kuali.common.devops.model.Packages;
import org.kuali.common.devops.model.User;
import org.kuali.common.devops.model.Users;
import org.kuali.common.util.Assert;
import org.kuali.common.util.channel.api.ChannelService;
import org.kuali.common.util.channel.model.ChannelContext;

import com.google.common.base.Optional;

/**
 * 
 */
public final class InstallTomcatContext {

	private final ChannelService service;
	private final ChannelContext context;
	private final Package pkg;
	private final String sharedDir;
	private final TomcatMajorVersion version;
	private final User user;
	private final Optional<Java> java;
	private final Optional<Heap> heap;

	public static class Builder {

		// Required
		private final ChannelService service;
		private final ChannelContext context;

		// Optional
		private Package pkg = Packages.TOMCAT7.getPackage();
		private TomcatMajorVersion version = TomcatMajorVersion.SEVEN;
		private String sharedDir = "/usr/share";
		private User user = Users.TOMCAT.getUser();
		private Optional<Java> java = Optional.absent();
		private Optional<Heap> heap = Optional.absent();

		public Builder(ChannelService service, ChannelContext context) {
			this.service = service;
			this.context = context;
		}

		public InstallTomcatContext build() {
			Assert.noBlanks(sharedDir);
			// Assert.noNulls(pkg, javaOpts, service, context, version, javaHome);
			return new InstallTomcatContext(this);
		}

	}

	private InstallTomcatContext(Builder builder) {
		this.service = builder.service;
		this.context = builder.context;
		this.pkg = builder.pkg;
		this.java = builder.java;
		this.sharedDir = builder.sharedDir;
		this.version = builder.version;
		this.user = builder.user;
		this.heap = builder.heap;
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

	public Package getPkg() {
		return pkg;
	}

}
