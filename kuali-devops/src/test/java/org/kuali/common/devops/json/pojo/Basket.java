package org.kuali.common.devops.json.pojo;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;

@IdiotProofImmutable
public final class Basket {

	private final String material;
	private final ImmutableList<Apple> apples;

	private Basket(Builder builder) {
		this.material = builder.material;
		this.apples = ImmutableList.copyOf(builder.apples);
	}

	@JsonCreator
	public static Basket create(@JsonProperty("material") String material, @JsonProperty("apples") List<Apple> apples) {
		return builder().material(material).apples(apples).build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<Basket> {

		private String material;
		private List<Apple> apples;

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

		public Builder material(String material) {
			this.material = material;
			return this;
		}

		public Builder apples(List<Apple> apples) {
			this.apples = apples;
			return this;
		}

		public String getMaterial() {
			return material;
		}

		public void setMaterial(String material) {
			this.material = material;
		}

		public List<Apple> getApples() {
			return apples;
		}

		public void setApples(List<Apple> apples) {
			this.apples = apples;
		}

	}

	public String getMaterial() {
		return material;
	}

	public ImmutableList<Apple> getApples() {
		return apples;
	}

}
