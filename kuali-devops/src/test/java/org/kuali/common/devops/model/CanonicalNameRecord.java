package org.kuali.common.devops.model;

import static org.kuali.common.util.validate.Validation.checkConstraints;

import org.kuali.common.util.ObjectUtils;
import org.kuali.common.util.build.LegacyValidatingBuilder;
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

	public static class Builder extends LegacyValidatingBuilder<CanonicalNameRecord> {

		private String alias;
		private String canonical;

		@Override
		public CanonicalNameRecord build() {
			return checkConstraints(new CanonicalNameRecord(this));
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + alias.hashCode();
		result = prime * result + canonical.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object other) {
		if (ObjectUtils.notEqual(this, other)) {
			return false;
		} else {
			CanonicalNameRecord cname = (CanonicalNameRecord) other;
			return alias.equals(cname.getAlias()) && canonical.equals(cname.getCanonical());
		}
	}

}
