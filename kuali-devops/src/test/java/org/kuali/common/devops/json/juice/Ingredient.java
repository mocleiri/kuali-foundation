package org.kuali.common.devops.json.juice;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.kuali.common.util.build.ViolationsBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

@IdiotProofImmutable
public final class Ingredient {

	private final String name;
	private final String amount;

	private Ingredient(Builder builder) {
		this.name = builder.name;
		this.amount = builder.amount;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ViolationsBuilder<Ingredient> {

		private String name;
		private String amount;

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

		public Builder amount(String amount) {
			this.amount = amount;
			return this;
		}

	}

	public String getName() {
		return name;
	}

	public String getAmount() {
		return amount;
	}

}
