package org.kuali.common.devops.json.breakfast;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.kuali.common.util.build.ViolationsBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

@IdiotProofImmutable
public final class SuperFancyBowl {

	private final FancyMilk milk;

	private SuperFancyBowl(Builder builder) {
		this.milk = builder.milk;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ViolationsBuilder<SuperFancyBowl> {

		private FancyMilk milk;

		@Override
		public Set<ConstraintViolation<SuperFancyBowl>> violations() {
			return violations(make());
		}

		@Override
		public SuperFancyBowl build() {
			return validate(make());
		}

		private SuperFancyBowl make() {
			return new SuperFancyBowl(this);
		}

		public Builder milk(FancyMilk milk) {
			this.milk = milk;
			return this;
		}

		public FancyMilk getMilk() {
			return milk;
		}

		public void setMilk(FancyMilk milk) {
			this.milk = milk;
		}

	}

	public FancyMilk getMilk() {
		return milk;
	}

}
