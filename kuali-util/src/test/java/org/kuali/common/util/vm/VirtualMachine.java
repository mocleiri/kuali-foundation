package org.kuali.common.util.vm;

import org.kuali.common.util.bind.api.Bound;
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

	public static Builder builder() {
		return new Builder();
	}

	@Bound(prefix = false)
	public static class Builder extends AwesomeBuilder<VirtualMachine> {

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
