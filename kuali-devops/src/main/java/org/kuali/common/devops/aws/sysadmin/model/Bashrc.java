package org.kuali.common.devops.aws.sysadmin.model;

import org.kuali.common.util.Assert;

public final class Bashrc {

	private final User user;
	private final String content;
	private final String location;

	public static class Builder {

		// Required
		private final User user;
		private final String content;

		// Required but filled in automatically
		private final String location;

		public Builder(User user, String content) {
			this.user = user;
			this.content = content;
			this.location = user.getHome() + "/.bashrc";
		}

		public Bashrc build() {
			Assert.noBlanks(content, location);
			Assert.noNulls(user);
			return new Bashrc(this);
		}
	}

	private Bashrc(Builder builder) {
		this.user = builder.user;
		this.content = builder.content;
		this.location = builder.location;
	}

	public User getUser() {
		return user;
	}

	public String getContent() {
		return content;
	}

	public String getLocation() {
		return location;
	}

}
