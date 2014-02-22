package org.kuali.common.devops.json;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.constraints.Min;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.collect.ImmutableList;

@IdiotProofImmutable
public final class Milk {

	@Min(0)
	private final double price;
	private final String type;
	private final ImmutableList<String> ingredients;

	private Milk(Builder builder) {
		this.price = builder.price;
		this.type = builder.type;
		this.ingredients = ImmutableList.copyOf(builder.ingredients);
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<Milk> {

		private double price = -1;
		private String type;
		private List<String> ingredients;

		public Builder ingredients(List<String> ingredients) {
			this.ingredients = ingredients;
			return this;
		}

		public Builder price(double price) {
			this.price = price;
			return this;
		}

		public Builder type(String type) {
			this.type = type;
			return this;
		}

		@Override
		public Set<ConstraintViolation<Milk>> violations() {
			return getViolations(make());
		}

		@Override
		public Milk build() {
			return validate(make());
		}

		private Milk make() {
			return new Milk(this);
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public List<String> getIngredients() {
			return ingredients;
		}

		public void setIngredients(List<String> ingredients) {
			this.ingredients = ingredients;
		}

	}

	public double getPrice() {
		return price;
	}

	public String getType() {
		return type;
	}

	public List<String> getIngredients() {
		return ingredients;
	}
}
