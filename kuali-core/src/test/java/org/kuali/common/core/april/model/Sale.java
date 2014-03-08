package org.kuali.common.core.april.model;

import javax.validation.constraints.Min;

import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@IdiotProofImmutable
@JsonDeserialize(builder = Sale.Builder.class)
public final class Sale {

	private final String level;
	private final String area;
	// Usually a number, but the on field seats are a letter (H/S/R) etc
	private final String section;

	@Min(1)
	private final int row;

	@Min(0)
	private final double price;

	@Min(1)
	private final int quantity;

	@Min(0)
	private final long date;

	private Sale(Builder builder) {
		this.level = builder.level;
		this.area = builder.area;
		this.section = builder.section;
		this.row = builder.row;
		this.price = builder.price;
		this.quantity = builder.quantity;
		this.date = builder.date;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<Sale> {

		private String level;
		private String area;
		private String section;
		private int row;
		private double price;
		private int quantity;
		private long date;

		public Builder withLevel(String level) {
			this.level = level;
			return this;
		}

		public Builder withArea(String area) {
			this.area = area;
			return this;
		}

		public Builder withSection(String section) {
			this.section = section;
			return this;
		}

		public Builder withRow(int row) {
			this.row = row;
			return this;
		}

		public Builder withPrice(double price) {
			this.price = price;
			return this;
		}

		public Builder withQuantity(int quantity) {
			this.quantity = quantity;
			return this;
		}

		public Builder withDate(long date) {
			this.date = date;
			return this;
		}

		@Override
		public Sale build() {
			return validate(new Sale(this));
		}
	}

	public String getLevel() {
		return level;
	}

	public String getArea() {
		return area;
	}

	public String getSection() {
		return section;
	}

	public int getRow() {
		return row;
	}

	public double getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public long getDate() {
		return date;
	}

}
