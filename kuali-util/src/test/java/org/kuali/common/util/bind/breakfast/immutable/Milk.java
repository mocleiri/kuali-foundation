package org.kuali.common.util.bind.breakfast.immutable;

import static org.kuali.common.util.validate.Validation.checkConstraints;

import javax.validation.constraints.Min;

import org.kuali.common.util.validate.IdiotProofImmutable;

@IdiotProofImmutable
public final class Milk {

	@Min(0)
	private final double price;
	private final String type;

	private Milk(Builder builder) {
		this.price = builder.price;
		this.type = builder.type;
	}

	public static class Builder implements org.apache.commons.lang3.builder.Builder<Milk> {

		private double price = -1;
		private String type;

		public Builder price(double price) {
			this.price = price;
			return this;
		}

		public Builder type(String type) {
			this.type = type;
			return this;
		}

		@Override
		public Milk build() {
			return checkConstraints(new Milk(this));
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

	}

	public double getPrice() {
		return price;
	}

	public String getType() {
		return type;
	}
}
