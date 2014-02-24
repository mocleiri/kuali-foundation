package org.kuali.common.devops.json.pojo;

import static com.google.common.base.Optional.absent;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;

@IdiotProofImmutable
public final class Apple {

	private final String color;
	private final Optional<Double> weight;

	private Apple(Builder builder) {
		this.color = builder.color;
		this.weight = builder.weight;
	}

	public static Apple createApple(String color) {
		return createApple(color, Optional.<Double> absent());
	}

	public static Apple createApple(String color, double weight) {
		return createApple(color, Optional.of(weight));
	}

	@JsonCreator
	public static Apple createApple(@JsonProperty("color") String color, @JsonProperty("weight") Optional<Double> weight) {
		return builder(color).weight(weight).build();
	}

	public static Builder builder(String color) {
		return new Builder(color);
	}

	public static class Builder extends ValidatingBuilder<Apple> {

		private final String color;
		private Optional<Double> weight = absent();

		public Builder(String color) {
			this.color = color;
		}

		@Override
		public Apple build() {
			return validate(make());
		}

		@Override
		public Set<ConstraintViolation<Apple>> violations() {
			return violations(make());
		}

		private Apple make() {
			return new Apple(this);
		}

		public Builder weight(Optional<Double> weight) {
			this.weight = weight;
			return this;
		}

		public Builder weight(double weight) {
			return weight(Optional.of(weight));
		}

		public String getColor() {
			return color;
		}

		public Optional<Double> getWeight() {
			return weight;
		}

		public void setWeight(Optional<Double> weight) {
			this.weight = weight;
		}

	}

	public String getColor() {
		return color;
	}

	public Optional<Double> getWeight() {
		return weight;
	}

}
