package org.kuali.common.util.builder.car;

import org.springframework.core.env.Environment;

import com.google.common.base.Optional;

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

	public static class Builder {

		private final String make; // Required
		private Optional<String> description = Optional.absent(); // Optional

		// Spring's environment abstraction
		private final Optional<Environment> environment;

		public Builder(String make) {
			this(Optional.<Environment> absent(), make);
		}

		public Builder(Environment environment, String make) {
			this(Optional.of(environment), make);
		}

		private Builder(Optional<Environment> environment, String make) {
			if (environment.isPresent()) {
				Environment env = environment.get();
				this.make = env.getProperty("car.make", make);
			} else {
				this.make = make;
			}
			this.environment = environment;
		}

		public Builder description(String description) {
			this.description = Optional.of(description);
			return this;
		}

		private void override() {
			if (environment.isPresent()) {
				Environment env = environment.get();
				String description = env.getProperty("car.description");
				this.description = Optional.fromNullable(description);
			}
		}

		private void validate(Car instance) {
			if (instance.getMake() == null) {
				throw new IllegalArgumentException("make is null");
			}
			if (instance.getDescription() == null) {
				throw new IllegalArgumentException("description is null");
			}
		}

		public Car build() {
			override();
			Car instance = new Car(this);
			validate(instance);
			return instance;
		}
	}
}
