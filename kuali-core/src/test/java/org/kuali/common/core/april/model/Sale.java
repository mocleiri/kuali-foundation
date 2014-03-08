package org.kuali.common.core.april.model;

import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@IdiotProofImmutable
@JsonDeserialize(builder = Sale.Builder.class)
public final class Sale {

	private final String line1;
	private final String line2;
	private final String line3;

	private Sale(Builder builder) {
		this.line1 = builder.line1;
		this.line2 = builder.line2;
		this.line3 = builder.line3;
	}

	public static final Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<Sale> {

		private String line1;
		private String line2;
		private String line3;

		public Builder withLine1(String line1) {
			this.line1 = line1;
			return this;
		}

		public Builder withLine2(String line2) {
			this.line2 = line2;
			return this;
		}

		public Builder withLine3(String line3) {
			this.line3 = line3;
			return this;
		}

		@Override
		public Sale build() {
			return validate(new Sale(this));
		}
	}

	public String getLine1() {
		return line1;
	}

	public String getLine2() {
		return line2;
	}

	public String getLine3() {
		return line3;
	}

}
