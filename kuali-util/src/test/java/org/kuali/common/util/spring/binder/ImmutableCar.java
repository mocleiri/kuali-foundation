package org.kuali.common.util.spring.binder;

import javax.validation.constraints.Min;

import org.kuali.common.util.FormatUtils;

public class ImmutableCar {

	@Min(1886)
	private final int year; // Very first car was built in 1886
	private final String make;
	private final String model;

	@Min(0)
	private final double price; // No negative prices!

	@Min(0)
	private final long internalHardDriveSize; // Negative hard drive size makes no sense

	@Min(0)
	private final int zeroToSixtyTime; // Negative zero to sixty time makes no sense

	private ImmutableCar(Builder builder) {
		this.year = builder.year;
		this.make = builder.make;
		this.model = builder.model;
		this.price = builder.price;
		this.internalHardDriveSize = builder.internalHardDriveSize;
		this.zeroToSixtyTime = builder.zeroToSixtyTime;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private int year = 1967;
		private String make = "Chevrolet";
		private String model = "Camaro SS";
		private double price = 30900;
		@BytesFormat
		private long internalHardDriveSize = 0;
		@TimeFormat
		private int zeroToSixtyTime = FormatUtils.getMillisAsInt("7.9s");

		public ImmutableCar build() {
			return new ImmutableCar(this);
		}

		public Builder withYear(int year) {
			this.year = year;
			return this;
		}

		public Builder withMake(String make) {
			this.make = make;
			return this;
		}

		public Builder withModel(String model) {
			this.model = model;
			return this;
		}

		public Builder withPrice(double price) {
			this.price = price;
			return this;
		}

		public Builder withInternalHardDriveSize(long internalHardDriveSize) {
			this.internalHardDriveSize = internalHardDriveSize;
			return this;
		}

		public Builder withZeroToSixtyTime(int zeroToSixtyTime) {
			this.zeroToSixtyTime = zeroToSixtyTime;
			return this;
		}

		public int getYear() {
			return year;
		}

		public void setYear(int year) {
			this.year = year;
		}

		public String getMake() {
			return make;
		}

		public void setMake(String make) {
			this.make = make;
		}

		public String getModel() {
			return model;
		}

		public void setModel(String model) {
			this.model = model;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double stickerPrice) {
			this.price = stickerPrice;
		}

		public long getInternalHardDriveSize() {
			return internalHardDriveSize;
		}

		public void setInternalHardDriveSize(long internalHardDriveSize) {
			this.internalHardDriveSize = internalHardDriveSize;
		}

		public int getZeroToSixtyTime() {
			return zeroToSixtyTime;
		}

		public void setZeroToSixtyTime(int zeroToSixtyTime) {
			this.zeroToSixtyTime = zeroToSixtyTime;
		}
	}

	public int getYear() {
		return year;
	}

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public double getPrice() {
		return price;
	}

	public long getInternalHardDriveSize() {
		return internalHardDriveSize;
	}

	public int getZeroToSixtyTime() {
		return zeroToSixtyTime;
	}

}
