package org.kuali.common.util.spring.binder;

import javax.validation.constraints.Min;

import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.builder.AbstractBuilder;
import org.kuali.common.util.validate.MatchDeclaringClassFields;
import org.kuali.common.util.validate.NoBlanks;
import org.kuali.common.util.validate.NoNulls;
import org.kuali.common.util.validate.ValidPort;

@NoNulls
@NoBlanks
public final class Car {

	// Very first car was built in 1886
	@Min(1886)
	private final int year;
	private final String make;
	private final String model;

	// No negative prices
	@Min(0)
	private final double price;

	// No negative sizes
	@Min(0)
	private final long internalHardDriveSizeInBytes;

	// No negative times
	@ValidPort
	private final Integer zeroToSixtyTimeInMillis;

	private Car(Builder builder) {
		this.year = builder.year;
		this.make = builder.model;
		this.model = builder.model;
		this.price = builder.price;
		this.internalHardDriveSizeInBytes = builder.internalHardDriveSizeInBytes;
		this.zeroToSixtyTimeInMillis = builder.zeroToSixtyTimeInMillis;
	}

	public static Builder builder() {
		return new Builder();
	}

	@MatchDeclaringClassFields
	public static class Builder extends AbstractBuilder<Car> {

		private int year = 1967;
		private String make = "Chevrolet";
		private String model = "Camaro SS";

		private double price = 30900;

		@BytesFormat
		private long internalHardDriveSizeInBytes = 0;

		@TimeFormat
		@ValidPort
		private Integer zeroToSixtyTimeInMillis = FormatUtils.getMillisAsInt("7.9s");

		@Override
		public Car getInstance() {
			return new Car(this);
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

		public Builder withInternalHardDriveSizeInBytes(long internalHardDriveSizeInBytes) {
			this.internalHardDriveSizeInBytes = internalHardDriveSizeInBytes;
			return this;
		}

		public Builder withZeroToSixtyTimeInMillis(int zeroToSixtyTimeInMillis) {
			this.zeroToSixtyTimeInMillis = zeroToSixtyTimeInMillis;
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

		public long getInternalHardDriveSizeInBytes() {
			return internalHardDriveSizeInBytes;
		}

		public void setInternalHardDriveSizeInBytes(long internalHardDriveSizeInBytes) {
			this.internalHardDriveSizeInBytes = internalHardDriveSizeInBytes;
		}

		public int getZeroToSixtyTimeInMillis() {
			return zeroToSixtyTimeInMillis;
		}

		public void setZeroToSixtyTimeInMillis(int zeroToSixtyTimeInMillis) {
			this.zeroToSixtyTimeInMillis = zeroToSixtyTimeInMillis;
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

	public long getInternalHardDriveSizeInBytes() {
		return internalHardDriveSizeInBytes;
	}

	public long getZeroToSixtyTimeInMillis() {
		return zeroToSixtyTimeInMillis;
	}

}
