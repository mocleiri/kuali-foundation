package org.kuali.common.util.vm;

import org.kuali.common.util.build.AwesomeBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

@IdiotProofImmutable
public final class VirtualMachine {

	private final User user;

	private VirtualMachine(Builder builder) {
		this.user = builder.user;
	}

	public static VirtualMachine build() {
		return builder().build();
	}

	private static Builder builder() {
		return new Builder();
	}

	private static class Builder extends AwesomeBuilder<VirtualMachine> {

		private User user = User.create();

		@Override
		public VirtualMachine getInstance() {
			return new VirtualMachine(this);
		}

	}

	public User getUser() {
		return user;
	}

}
