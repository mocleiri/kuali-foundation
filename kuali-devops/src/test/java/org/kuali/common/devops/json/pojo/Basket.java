package org.kuali.common.devops.json.pojo;

import static com.google.common.base.Optional.absent;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

@IdiotProofImmutable
@JsonDeserialize(builder = Basket.Builder.class)
public final class Basket {

	private final String material;
	private final ImmutableList<Apple> apples;
	private final Optional<Apple> apple;

	private Basket(Builder builder) {
		this.material = builder.material;
		this.apples = ImmutableList.copyOf(builder.apples);
		this.apple = builder.apple;
	}

	public static Builder builder(String material) {
		return new Builder(material);
	}

	public static class Builder extends ValidatingBuilder<Basket> {

		private final String material;
		private List<Apple> apples = ImmutableList.of();
		private Optional<Apple> apple = absent();

		@JsonCreator
		public Builder(@JsonProperty("material") String material) {
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

		public Builder withApple(Optional<Apple> apple) {
			this.apple = apple;
			return this;
		}

		public Builder withApples(List<Apple> apples) {
			this.apples = apples;
			return this;
		}

	}

	public String getMaterial() {
		return material;
	}

	public List<Apple> getApples() {
		return apples;
	}

	public Optional<Apple> getApple() {
		return apple;
	}

}
