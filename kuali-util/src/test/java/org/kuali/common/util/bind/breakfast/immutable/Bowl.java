package org.kuali.common.util.bind.breakfast.immutable;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.kuali.common.util.bind.api.Bind;
import org.kuali.common.util.build.ValidatingBuilder2;
import org.kuali.common.util.validate.IdiotProofImmutable;

@IdiotProofImmutable
public final class Bowl {

	@Bind
	private final Milk milk;

	private Bowl(Builder builder) {
		this.milk = builder.milk;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder2<Bowl> {

		private Milk milk;

		public Builder milk(Milk milk) {
			this.milk = milk;
			return this;
		}

		private Bowl newBowl() {
			return new Bowl(this);
		}

		@Override
		public boolean isValid() {
			return isValid(newBowl());
		}

		@Override
		public Set<ConstraintViolation<Bowl>> getViolations() {
			return getViolations(newBowl());
		}

		@Override
		public Bowl build() {
			return validate(newBowl());
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
