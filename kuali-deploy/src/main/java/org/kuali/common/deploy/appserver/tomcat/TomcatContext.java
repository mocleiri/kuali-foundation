package org.kuali.common.deploy.appserver.tomcat;

import org.kuali.common.util.Assert;

public final class TomcatContext {

	private final String basedir;

	public static class Builder {

		private final String basedir;

		public Builder(String basedir) {
			this.basedir = basedir;
		}

		public TomcatContext build() {
			Assert.noBlanks(basedir);
			return new TomcatContext(this);
		}

	}

	private TomcatContext(Builder builder) {
		this.basedir = builder.basedir;
	}

	public String getBasedir() {
		return basedir;
	}

}
