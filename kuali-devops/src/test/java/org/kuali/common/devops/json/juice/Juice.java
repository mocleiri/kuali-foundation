package org.kuali.common.devops.json.juice;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.constraints.Min;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.collect.ImmutableList;

@IdiotProofImmutable
public final class Juice {

	@Min(0)
	private final double price;
	private final ImmutableList<Ingredient> ingredients;

	private Juice(Builder builder) {
		this.price = builder.price;
		this.ingredients = ImmutableList.copyOf(builder.ingredients);
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<Juice> {

		private double price;
		private List<Ingredient> ingredients = ImmutableList.of();

		@Override
		public Set<ConstraintViolation<Juice>> violations() {
			return violations(make());
		}

		@Override
		public Juice build() {
			return validate(make());
		}

		public Builder price(double price) {
			this.price = price;
			return this;
		}

		public Builder ingredients(List<Ingredient> ingredients) {
			this.ingredients = ingredients;
			return this;
		}

		private Juice make() {
			return new Juice(this);
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public List<Ingredient> getIngredients() {
			return ingredients;
		}

		public void setIngredients(List<Ingredient> ingredients) {
			this.ingredients = ingredients;
		}
	}

	public double getPrice() {
		return price;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}
}
