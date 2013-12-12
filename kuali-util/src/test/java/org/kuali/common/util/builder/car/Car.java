package org.kuali.common.util.builder.car;

import org.kuali.common.util.builder.AbstractBuilder;
import org.kuali.common.util.validate.NoBlanks;
import org.kuali.common.util.validate.NoNulls;

import com.google.common.base.Optional;

@NoNulls
@NoBlanks
public final class Car {

	public String getMake() {
		return make;
	}

	public Optional<String> getDescription() {
		return description;
	}

	private final String make;
	private final Optional<String> description;

	private Car(Builder builder) {
		this.make = builder.make;
		this.description = builder.description;
	}

	public static class Builder extends AbstractBuilder<Car> {

		private final String make; // Required
		private Optional<String> description = Optional.absent(); // Optional

		private Builder(String make) {
			this.make = make;
		}

		public Builder description(String description) {
			this.description = Optional.of(description);
			return this;
		}

		@Override
		protected Car getInstance() {
			return new Car(this);
		}

	}
}
