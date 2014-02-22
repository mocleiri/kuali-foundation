package org.kuali.common.devops.json;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.constraints.Min;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

@IdiotProofImmutable
public final class FancyMilk {

	@Min(0)
	private final double price;
	private final String type;
	private final ImmutableList<String> ingredients;
	private final ImmutableMap<String, String> nutritionFacts;

	private FancyMilk(Builder builder) {
		this.price = builder.price;
		this.type = builder.type;
		this.ingredients = ImmutableList.copyOf(builder.ingredients);
		this.nutritionFacts = ImmutableMap.copyOf(builder.nutritionFacts);
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<FancyMilk> {

		private double price = -1;
		private String type;
		private List<String> ingredients;
		private Map<String, String> nutritionFacts;

		public Builder nutritionFacts(Map<String, String> nutritionFacts) {
			this.nutritionFacts = nutritionFacts;
			return this;
		}

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
		public Set<ConstraintViolation<FancyMilk>> violations() {
			return violations(make());
		}

		@Override
		public FancyMilk build() {
			return validate(make());
		}

		private FancyMilk make() {
			return new FancyMilk(this);
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

		public Map<String, String> getNutritionFacts() {
			return nutritionFacts;
		}

		public void setNutritionFacts(Map<String, String> nutritionFacts) {
			this.nutritionFacts = nutritionFacts;
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

	public ImmutableMap<String, String> getNutritionFacts() {
		return nutritionFacts;
	}
}
