package org.kuali.common.devops.json.fruit;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

@IdiotProofImmutable
public final class Apple {

	private final String color;

	private Apple(Builder builder) {
		this.color = builder.color;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<Apple> {

		private String color;

		public Builder color(String color) {
			this.color = color;
			return this;
		}

		@Override
		public Set<ConstraintViolation<Apple>> violations() {
			return violations(make());
		}

		@Override
		public Apple build() {
			return validate(make());
		}

		private Apple make() {
			return new Apple(this);
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
