package org.kuali.common.devops.aws.sysadmin.model;

import org.kuali.common.util.Assert;

public final class JDKContext {

	private final JDKLevel level;
	private final String version;

	public static class Builder {

		// Required
		private final JDKLevel level;
		private final String version;

		public Builder(JDKLevel level, String version) {
			this.level = level;
			this.version = version;
		}

		public JDKContext build() {
			Assert.noNulls(level);
			Assert.noBlanks(version);
			return new JDKContext(this);
		}
	}

	private JDKContext(Builder builder) {
		this.level = builder.level;
		this.version = builder.version;
	}

	public JDKLevel getLevel() {
		return level;
	}

	public String getVersion() {
		return version;
	}

}
