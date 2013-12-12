package org.kuali.common.util.builder;

import org.kuali.common.util.env.adapter.OptionalStringAdapter;
import org.kuali.common.util.env.annotation.Env;
import org.kuali.common.util.env.annotation.EnvAdapterClass;
import org.kuali.common.util.validate.NoBlanks;
import org.kuali.common.util.validate.NoNulls;
import org.kuali.common.util.validate.NotBlankIfPresent;

import com.google.common.base.Optional;

@NoNulls
@NoBlanks
@Env(prefix = "car")
public final class Car {

	public String getMake() {
		return make;
	}

	public Optional<String> getDescription() {
		return description;
	}

	private final String make;

	@NotBlankIfPresent(message = "Car description cannot be blank if present")
	@EnvAdapterClass(OptionalStringAdapter.class)
	private final Optional<String> description;

	private Car(Builder builder) {
		this.make = builder.make;
		this.description = builder.description;
	}

	public static class Builder extends AbstractBuilder<Car> {

		private final String make; // Required
		private Optional<String> description = Optional.absent(); // Optional

		public Builder(String make) {
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
