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
public final class Car {

	public Optional<Engine> getEngine() {
		return engine;
	}

	public String getMake() {
		return make;
	}

	public Optional<String> getDescription() {
		return description;
	}

	private final String make;

	@NotBlankIfPresent(message = "Car description cannot be blank")
	private final Optional<String> description;

	private final Optional<Engine> engine;

	private Car(Builder builder) {
		this.make = builder.make;
		this.description = builder.description;
		this.engine = builder.engine;
	}

	@Env(prefix = "car")
	public static class Builder extends AbstractBuilder<Car> {

		private final String make; // Required

		@EnvAdapterClass(OptionalStringAdapter.class)
		private Optional<String> description = Optional.absent(); // Optional
		private Optional<Engine> engine = Optional.absent(); // Optional

		public Builder(String make) {
			this.make = make;
		}

		public Builder(BuilderContext context, String make) {
			super(context);
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
