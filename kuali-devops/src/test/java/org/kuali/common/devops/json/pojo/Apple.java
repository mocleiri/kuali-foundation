package org.kuali.common.devops.json.pojo;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@IdiotProofImmutable
public final class Apple {

	private final String color;

	private Apple(Builder builder) {
		this.color = builder.color;
	}

	@JsonCreator
	public static Apple create(@JsonProperty("color") String color) {
		return builder().color(color).build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<Apple> {

		private String color;

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

		public Builder color(String color) {
			this.color = color;
			return this;
		}

		public String getColor() {
			return color;
		}

		public void setColor(String color) {
			this.color = color;
		}

	}

	public String getColor() {
		return color;
	}

}
