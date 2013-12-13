package org.kuali.common.util.builder;

import javax.validation.constraints.Min;

import org.kuali.common.util.env.annotation.Env;
import org.kuali.common.util.validate.NoBlanks;
import org.kuali.common.util.validate.NoNulls;

@NoNulls
@NoBlanks
public final class Engine {

	public String getType() {
		return type;
	}

	public double getWeight() {
		return weight;
	}

	private final String type;

	@Min(1)
	private final double weight;

	private Engine(Builder builder) {
		this.type = builder.type;
		this.weight = builder.weight;
	}

	@Env(prefix = "engine")
	public static class Builder extends AbstractBuilder<Engine> {

		// Required
		private final double weight;

		// Optional
		private String type = "two-stroke";

		public Builder(double weight) {
			this.weight = weight;
		}

		public Builder withType(String type) {
			this.type = type;
			return this;
		}

		@Override
		public Engine getInstance() {
			return new Engine(this);
		}

	}

}
