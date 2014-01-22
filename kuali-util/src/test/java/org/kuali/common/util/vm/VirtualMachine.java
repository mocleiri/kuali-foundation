package org.kuali.common.util.vm;

import org.kuali.common.util.build.AwesomeBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

@IdiotProofImmutable
public final class VirtualMachine {

	private final User user;
	private final OperatingSystem operatingSystem;

	private VirtualMachine(Builder builder) {
		this.user = builder.user;
		this.operatingSystem = builder.operatingSystem;
	}

	public static VirtualMachine build() {
		return builder().build();
	}

	private static Builder builder() {
		return new Builder();
	}

	private static class Builder extends AwesomeBuilder<VirtualMachine> {

		private User user = User.create();
		private OperatingSystem operatingSystem = OperatingSystem.create();

		@Override
		public VirtualMachine getInstance() {
			return new VirtualMachine(this);
		}

	}

	public OperatingSystem getOperatingSystem() {
		return operatingSystem;
	}

	public User getUser() {
		return user;
	}

}
