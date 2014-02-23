package org.kuali.common.devops.json.juice;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

@IdiotProofImmutable
public final class Ingredient {

	private final String name;
	private final String description;

	private Ingredient(Builder builder) {
		this.name = builder.name;
		this.description = builder.description;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<Ingredient> {

		private String name;
		private String description;

		@Override
		public Ingredient build() {
			return validate(make());
		}

		@Override
		public Set<ConstraintViolation<Ingredient>> violations() {
			return violations(make());
		}

		private Ingredient make() {
			return new Ingredient(this);
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder description(String description) {
			this.description = description;
			return this;
		}

	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

}
