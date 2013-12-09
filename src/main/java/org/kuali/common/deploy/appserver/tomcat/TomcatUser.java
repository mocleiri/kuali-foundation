package org.kuali.common.deploy.appserver.tomcat;

import org.kuali.common.util.Assert;

public final class TomcatUser {

	private final String home;
	private final String name;
	private final String group;
	private final String owner;

	public static class Builder {

		// Required
		private final String home;
		private final String name;

		// Optional
		private String group;
		private String owner;

		public Builder(String home, String name) {
			this.home = home;
			this.name = name;
			this.group = name;
			this.owner = name;
		}

		public Builder group(String group) {
			this.group = group;
			return this;
		}

		public Builder owner(String owner) {
			this.owner = owner;
			return this;
		}

		public TomcatUser build() {
			Assert.noBlanks(home, name, group, owner);
			return new TomcatUser(this);
		}

	}

	private TomcatUser(Builder builder) {
		this.home = builder.home;
		this.name = builder.name;
		this.group = builder.group;
		this.owner = builder.owner;
	}

	public String getHome() {
		return home;
	}

	public String getName() {
		return name;
	}

	public String getGroup() {
		return group;
	}

	public String getOwner() {
		return owner;
	}

}
