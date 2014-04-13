package org.kuali.common.deploy.appserver.tomcat;

import org.kuali.common.util.Assert;

public final class TomcatContext {

	private final TomcatDirs dirs;

	public static class Builder {

		private final TomcatDirs dirs;

		public Builder(TomcatDirs dirs) {
			this.dirs = dirs;
		}

		public TomcatContext build() {
			Assert.noNulls(dirs);
			return new TomcatContext(this);
		}

	}

	private TomcatContext(Builder builder) {
		this.dirs = builder.dirs;
	}

	public TomcatDirs getDirs() {
		return dirs;
	}

}
