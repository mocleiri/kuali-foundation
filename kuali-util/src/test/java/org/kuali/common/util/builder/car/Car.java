package org.kuali.common.util.builder.car;

import org.kuali.common.util.Assert;

public final class Car {

	public String getColor() {
		return color;
	}

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	private final String color;
	private final String make;
	private final String model;

	private Car(Builder builder) {
		this.color = builder.color;
		this.make = builder.make;
		this.model = builder.model;
	}

	public static class Builder {

		private String color;
		private String make;
		private String model;

		public Builder color(String color) {
			this.color = color;
			return this;
		}

		public Builder make(String make) {
			this.make = make;
			return this;
		}

		public Builder model(String model) {
			this.model = model;
			return this;
		}

		private void validate(Car instance) {
			Assert.noBlanks(instance.getColor(), instance.getMake(), instance.getModel());
		}

		public Car build() {
			Car instance = new Car(this);
			validate(instance);
			return instance;
		}
	}
}
