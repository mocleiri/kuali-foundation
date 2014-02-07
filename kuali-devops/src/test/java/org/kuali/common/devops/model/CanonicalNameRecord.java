package org.kuali.common.devops.model;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

@IdiotProofImmutable
public final class CanonicalNameRecord {

	private final String alias;
	private final String canonical;

	private CanonicalNameRecord(Builder builder) {
		this.alias = builder.alias;
		this.canonical = builder.canonical;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<CanonicalNameRecord> {

		private String alias;
		private String canonical;

		@Override
		public CanonicalNameRecord getInstance() {
			return new CanonicalNameRecord(this);
		}

		public Builder alias(String alias) {
			this.alias = alias;
			return this;
		}

		public Builder canonical(String canonical) {
			this.canonical = canonical;
			return this;
		}

		public String getAlias() {
			return alias;
		}

		public void setAlias(String alias) {
			this.alias = alias;
		}

		public String getCanonical() {
			return canonical;
		}

		public void setCanonical(String canonical) {
			this.canonical = canonical;
		}

	}

	public String getAlias() {
		return alias;
	}

	public String getCanonical() {
		return canonical;
	}

}
