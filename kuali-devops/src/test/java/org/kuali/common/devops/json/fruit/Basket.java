package org.kuali.common.devops.json.fruit;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.collect.ImmutableList;

@IdiotProofImmutable
public final class Basket {

	private final String material;
	private final ImmutableList<Apple> apples;

	private Basket(Builder builder) {
		this.apples = ImmutableList.copyOf(builder.apples);
		this.material = builder.material;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<Basket> {

		private String material;
		private List<Apple> apples;

		public Builder material(String material) {
			this.material = material;
			return this;
		}

		public Builder apples(List<Apple> apples) {
			this.apples = apples;
			return this;
		}

		@Override
		public Set<ConstraintViolation<Basket>> violations() {
			return violations(make());
		}

		@Override
		public Basket build() {
			return validate(make());
		}

		private Basket make() {
			return new Basket(this);
		}

		public List<Apple> getApples() {
			return apples;
		}

		public void setApples(List<Apple> apples) {
			this.apples = apples;
		}

		public String getMaterial() {
			return material;
		}

		public void setMaterial(String material) {
			this.material = material;
		}

	}

	public ImmutableList<Apple> getApples() {
		return apples;
	}

	public String getMaterial() {
		return material;
	}

}
