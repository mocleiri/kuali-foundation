package org.kuali.common.util.builder;

import org.kuali.common.util.validate.NoNulls;
import org.kuali.common.util.validate.NotBlankIfPresent;

import com.google.common.base.Optional;

@NoNulls
public final class MyCar {

	public String getMake() {
		return make;
	}

	public Optional<String> getDescription() {
		return description;
	}

	private final String make;

	@NotBlankIfPresent(message = "Description cannot be blank if present")
	private final Optional<String> description;

	private MyCar(Builder builder) {
		this.make = builder.make;
		this.description = builder.description;
	}

	public static class Builder {

		private final String make; // Required
		private Optional<String> description = Optional.absent(); // Optional

		public Builder(String make) {
			this.make = make;
		}

		public Builder description(String description) {
			this.description = Optional.of(description);
			return this;
		}

		protected MyCar getInstance() {
			MyCar car = new MyCar(this);
			return car;
		}

		public MyCar build() {
			return getInstance();
		}

	}
}
