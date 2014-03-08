package org.kuali.common.core.april.model;

import java.util.List;

import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@IdiotProofImmutable
@JsonDeserialize(builder = SaleLines.Builder.class)
public final class SaleLines {

	private final String line1;
	private final String line2;
	private final String line3;

	private SaleLines(Builder builder) {
		this.line1 = builder.line1;
		this.line2 = builder.line2;
		this.line3 = builder.line3;
	}

	public static final SaleLines create(List<String> lines, int index) {
		Builder builder = builder();
		builder.withLine1(lines.get(index));
		builder.withLine2(lines.get(index + 1));
		builder.withLine3(lines.get(index + 2));
		return builder.build();
	}

	public static final Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<SaleLines> {

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
		public SaleLines build() {
			return validate(new SaleLines(this));
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
