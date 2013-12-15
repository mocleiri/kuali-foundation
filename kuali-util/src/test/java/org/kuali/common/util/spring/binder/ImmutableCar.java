package org.kuali.common.util.spring.binder;

import javax.validation.constraints.Min;

public class ImmutableCar {

	public String getManufacturer() {
		return manufacturer;
	}

	public String getColor() {
		return color;
	}

	public double getStickerPrice() {
		return stickerPrice;
	}

	public int getYear() {
		return year;
	}

	private final String manufacturer;
	private final String color;

	// No negative sticker prices!
	@Min(0)
	private final double stickerPrice;

	// First year a car was made was 1886
	@Min(1886)
	private final int year;

	private ImmutableCar(Builder builder) {
		this.manufacturer = builder.manufacturer;
		this.color = builder.color;
		this.stickerPrice = builder.stickerPrice;
		this.year = builder.year;
	}

	public static class Builder {

		private String manufacturer;
		private String color;
		private double stickerPrice;
		private int year;

		public Builder withManufacturer(String manufacturer) {
			this.manufacturer = manufacturer;
			return this;
		}

		public Builder withColor(String color) {
			this.color = color;
			return this;
		}

		public Builder withStickerPrice(double stickerPrice) {
			this.stickerPrice = stickerPrice;
			return this;
		}

		public Builder withYear(int year) {
			this.year = year;
			return this;
		}

		public ImmutableCar build() {
			return new ImmutableCar(this);
		}
	}

}
