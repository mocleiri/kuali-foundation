package org.kuali.common.devops.json.breakfast;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.kuali.common.util.build.ViolationsBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

@IdiotProofImmutable
public final class FancyBowl {

	private final Milk milk;

	private FancyBowl(Builder builder) {
		this.milk = builder.milk;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ViolationsBuilder<FancyBowl> {

		private Milk milk;

		@Override
		public Set<ConstraintViolation<FancyBowl>> violations() {
			return violations(make());
		}

		@Override
		public FancyBowl build() {
			return validate(make());
		}

		private FancyBowl make() {
			return new FancyBowl(this);
		}

		public Builder milk(Milk milk) {
			this.milk = milk;
			return this;
		}

		public Milk getMilk() {
			return milk;
		}

		public void setMilk(Milk milk) {
			this.milk = milk;
		}

	}

	public Milk getMilk() {
		return milk;
	}

}
