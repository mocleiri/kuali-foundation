package org.kuali.common.core.april.model;

import static com.google.common.base.Objects.equal;

import javax.validation.constraints.Min;

import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;
import org.kuali.common.util.ObjectUtils;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

@IdiotProofImmutable
@JsonDeserialize(builder = Sale.Builder.class)
@JsonPropertyOrder(value = { "level", "area", "section", "price" }, alphabetic = true)
public final class Sale implements Comparable<Sale> {

	private final String level;
	private final String area;
	// Usually a number, but the on field seats are a letter (H/S/R) etc
	private final String section;

	@Min(0)
	private final int row;

	@Min(0)
	private final double price;

	@Min(0)
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

	@Override
	public int compareTo(Sale b) {
		Sale a = this;
		return ComparisonChain.start().compare(a.level, b.level).compare(a.area, b.area).compare(a.section, b.section).compare(a.price, b.price).result();
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(level, area, section, row, price, quantity, date);
	}

	@Override
	public boolean equals(Object object) {
		if (ObjectUtils.notEqual(this, object)) {
			return false;
		} else {
			Sale a = this;
			Sale b = (Sale) object;
			return equal(a.level, b.level) && equal(a.area, b.area) && equal(a.section, b.section) && equal(a.row, b.row) && equal(a.price, b.price)
					&& equal(a.quantity, b.quantity) && equal(a.date, b.date);
		}
	}

}
