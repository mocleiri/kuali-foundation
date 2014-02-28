package org.kuali.common.util.bind.breakfast.immutable;

import org.kuali.common.util.bind.api.Bind;
import org.kuali.common.util.build.SimpleValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

@IdiotProofImmutable
public final class Bowl {

	@Bind
	private final Milk milk;

	private Bowl(Builder builder) {
		this.milk = builder.milk;
	}

	public static class Builder extends SimpleValidatingBuilder<Bowl> {

		private Milk milk;

		public Builder withMilk(Milk milk) {
			this.milk = milk;
			return this;
		}

		@Override
		public Bowl build() {
			return validate(new Bowl(this));
		}
	}

	public Milk getMilk() {
		return milk;
	}

}
