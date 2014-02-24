package org.kuali.common.devops.json.pojo;

import static com.google.common.base.Optional.absent;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

@IdiotProofImmutable
public final class Basket {

	private final String material;
	private final ImmutableList<Apple> apples;
	private final Optional<Apple> apple;

	private Basket(Builder builder) {
		this.material = builder.material;
		this.apples = ImmutableList.copyOf(builder.apples);
		this.apple = builder.apple;
	}

	@JsonCreator
	private static Basket newBasket(@JsonProperty("material") String material, @JsonProperty("apples") List<Apple> apples, @JsonProperty("apple") Optional<Apple> apple) {
		return builder(material).apples(apples).apple(apple).build();
	}

	public static Builder builder(String material) {
		return new Builder(material);
	}

	public static class Builder extends ValidatingBuilder<Basket> {

		private final String material;
		private List<Apple> apples = ImmutableList.of();
		private Optional<Apple> apple = absent();

		public Builder(String material) {
			this.material = material;
		}

		@Override
		public Basket build() {
			return validate(make());
		}

		@Override
		public Set<ConstraintViolation<Basket>> violations() {
			return violations(make());
		}

		private Basket make() {
			return new Basket(this);
		}

		public Builder apple(Optional<Apple> apple) {
			this.apple = apple;
			return this;
		}

		public Builder apple(Apple apple) {
			return apple(Optional.of(apple));
		}

		public Builder apples(List<Apple> apples) {
			this.apples = apples;
			return this;
		}

		public String getMaterial() {
			return material;
		}

		public List<Apple> getApples() {
			return apples;
		}

		public void setApples(List<Apple> apples) {
			this.apples = apples;
		}

		public Optional<Apple> getApple() {
			return apple;
		}

		public void setApple(Optional<Apple> apple) {
			this.apple = apple;
		}

	}

	public String getMaterial() {
		return material;
	}

	public ImmutableList<Apple> getApples() {
		return apples;
	}

	public Optional<Apple> getApple() {
		return apple;
	}

}
