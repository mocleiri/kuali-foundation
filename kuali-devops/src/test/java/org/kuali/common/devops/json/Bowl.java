package org.kuali.common.devops.json;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.kuali.common.util.bind.api.Bind;
import org.kuali.common.util.build.ValidatingBuilder;
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

	public static class Builder extends ValidatingBuilder<Bowl> {

		private Milk milk;

		public Builder milk(Milk milk) {
			this.milk = milk;
			return this;
		}

		@Override
		public Set<ConstraintViolation<Bowl>> violations() {
			return getViolations(new Bowl(this));
		}

		@Override
		public Bowl build() {
			return validate(new Bowl(this));
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
