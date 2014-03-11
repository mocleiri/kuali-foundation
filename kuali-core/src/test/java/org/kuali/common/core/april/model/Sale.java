package org.kuali.common.core.april.model;

import static com.google.common.base.Objects.equal;

import javax.validation.constraints.Min;

import org.kuali.common.core.april.json.DayDeserializer;
import org.kuali.common.core.april.json.DaySerializer;
import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;
import org.kuali.common.util.ObjectUtils;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.Objects;

@IdiotProofImmutable
@JsonDeserialize(builder = Sale.Builder.class)
@JsonPropertyOrder(value = { "date", "level", "area", "price", "section", "row", "quantity" }, alphabetic = true)
public final class Sale {

	private final Level level;
	private final Area area;
	// Usually a number, but the on field seats are a letter (H/S/R) etc
	private final String section;

	private final String row;

	@Min(0)
	private final double price;

	@Min(0)
	private final int quantity;

	@Min(0)
	@JsonSerialize(using = DaySerializer.class)
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

		private Level level;
		private Area area;
		private String section;
		private String row;
		private double price;
		private int quantity;
		@JsonDeserialize(using = DayDeserializer.class)
		private long date;

		public Builder withLevel(Level level) {
			this.level = level;
			return this;
		}

		public Builder withArea(Area area) {
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

	public Level getLevel() {
		return level;
	}

	public Area getArea() {
		return area;
	}

	public String getSection() {
		return section;
	}

	public String getRow() {
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
