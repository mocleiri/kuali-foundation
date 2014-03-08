package org.kuali.common.core.april.model;

import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;
import org.kuali.common.util.ObjectUtils;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

@IdiotProofImmutable
@JsonDeserialize(builder = Sale.Builder.class)
@JsonPropertyOrder(value = { "level", "area", "section", "row", "price", "quantity", "date" }, alphabetic = true)
public final class Sale implements Comparable<Sale> {

	private final String level;
	private final String area;
	// Usually a number, but the on field seats are a letter (H/S/R) etc
	private final String section;
	private final String row;
	private final String price;
	private final String quantity;
	private final String date;

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
		private String row;
		private String price;
		private String quantity;
		private String date;

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

		public Builder withRow(String row) {
			this.row = row;
			return this;
		}

		public Builder withPrice(String price) {
			this.price = price;
			return this;
		}

		public Builder withQuantity(String quantity) {
			this.quantity = quantity;
			return this;
		}

		public Builder withDate(String date) {
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

	public String getRow() {
		return row;
	}

	public String getPrice() {
		return price;
	}

	public String getQuantity() {
		return quantity;
	}

	public String getDate() {
		return date;
	}

	@Override
	public int compareTo(Sale b) {
		Sale a = this;
		return ComparisonChain.start().compare(a.level, b.level).compare(a.area, b.area).compare(a.section, b.section).compare(a.row, b.row).compare(a.price, b.price).result();
		// return ComparisonChain.start().compare(a.price, b.price).result();
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
			return a.level.equals(b.level) && a.area.equals(b.area) && a.section.equals(b.section) && a.row.equals(b.row) && a.price.equals(b.price)
					&& a.quantity.equals(b.quantity) && a.date.equals(b.date);
		}
	}

}
