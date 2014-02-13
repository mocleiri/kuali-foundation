package org.kuali.common.util.bind.breakfast.immutable;

import static org.kuali.common.util.validate.Validation.checkConstraints;

import org.kuali.common.util.bind.api.Bind;
import org.kuali.common.util.validate.IdiotProofImmutable;

@IdiotProofImmutable
public final class Bowl {

	@Bind
	private final Milk milk;

	private Bowl(Builder builder) {
		this.milk = builder.milk;
	}

	public static class Builder implements org.apache.commons.lang3.builder.Builder<Bowl> {

		private Milk milk;

		public Builder milk(Milk milk) {
			this.milk = milk;
			return this;
		}

		@Override
		public Bowl build() {
			return checkConstraints(new Bowl(this));
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
