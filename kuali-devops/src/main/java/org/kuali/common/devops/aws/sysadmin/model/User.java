package org.kuali.common.devops.aws.sysadmin.model;

import org.kuali.common.util.Assert;

public final class User {

	private final String login;
	private final String home;
	private final String group;
	private final String authorizedKeys;

	public static class Builder {

		// Required
		private final String login;

		// Optional
		private String home;
		private String group;
		private String authorizedKeys;

		public Builder(String login) {
			this.login = login;
			this.group = login;
			home("/home/" + login);
		}

		/**
		 * Set the users home directory. This also sets authorized keys to <code>[home]/.ssh/authorized_keys</code>
		 */
		public Builder home(String home) {
			this.home = home;
			this.authorizedKeys = home + "/.ssh/authorized_keys";
			return this;
		}

		public Builder authorizedKeys(String authorizedKeys) {
			this.authorizedKeys = authorizedKeys;
			return this;
		}

		public Builder group(String group) {
			this.group = group;
			return this;
		}

		public User build() {
			Assert.noBlanks(login, group, home, authorizedKeys);
			return new User(this);
		}
	}

	private User(Builder builder) {
		this.login = builder.login;
		this.group = builder.group;
		this.authorizedKeys = builder.authorizedKeys;
		this.home = builder.home;
	}

	public String getLogin() {
		return login;
	}

	public String getHome() {
		return home;
	}

	public String getAuthorizedKeys() {
		return authorizedKeys;
	}

	public String getGroup() {
		return group;
	}

}
