package org.kuali.common.util.builder;

import java.util.List;

import org.kuali.common.util.env.adapter.OptionalStringAdapter;
import org.kuali.common.util.env.annotation.Env;
import org.kuali.common.util.env.annotation.EnvAdapterClass;
import org.kuali.common.util.validate.NoBlanks;
import org.kuali.common.util.validate.NoNulls;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

@NoNulls
@NoBlanks
public final class Car {

	public List<String> getPassengers() {
		return passengers;
	}

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
	private final Optional<String> description;
	private final Optional<Engine> engine;
	private final List<String> passengers;

	private Car(Builder builder) {
		this.make = builder.make;
		this.description = builder.description;
		this.engine = builder.engine;
		this.passengers = builder.passengers;
	}

	@Env(prefix = "car")
	public static class Builder extends AbstractBuilder<Car> {

		private final String make; // Required

		@EnvAdapterClass(OptionalStringAdapter.class)
		private Optional<String> description = Optional.absent(); // Optional
		private Optional<Engine> engine = Optional.absent(); // Optional
		private List<String> passengers = ImmutableList.of();

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

		public Builder passengers(List<String> passengers) {
			this.passengers = passengers;
			return this;
		}

		@Override
		protected Car getInstance() {
			this.passengers = ImmutableList.copyOf(passengers);
			return new Car(this);
		}

	}
}
