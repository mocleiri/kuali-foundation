package org.kuali.common.util.vm;

import org.kuali.common.util.bind.api.Bind;
import org.kuali.common.util.build.AwesomeBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

@IdiotProofImmutable
public final class Java {

	private final RuntimeEnvironment runtimeEnvironment;
	private final VirtualMachine virtualMachine;

	private Java(Builder builder) {
		this.runtimeEnvironment = builder.runtimeEnvironment;
		this.virtualMachine = builder.virtualMachine;
	}

	@Bind
	public static class Builder extends AwesomeBuilder<Java> {

		@Bind
		private RuntimeEnvironment runtimeEnvironment;

		@Bind
		private VirtualMachine virtualMachine;

		public Builder runtimeEnvironment(RuntimeEnvironment runtimeEnvironment) {
			this.runtimeEnvironment = runtimeEnvironment;
			return this;
		}

		public Builder virtualMachine(VirtualMachine virtualMachine) {
			this.virtualMachine = virtualMachine;
			return this;
		}

		@Override
		public Java getInstance() {
			return new Java(this);
		}

		public RuntimeEnvironment getRuntimeEnvironment() {
			return runtimeEnvironment;
		}

		public void setRuntimeEnvironment(RuntimeEnvironment runtimeEnvironment) {
			this.runtimeEnvironment = runtimeEnvironment;
		}

		public VirtualMachine getVirtualMachine() {
			return virtualMachine;
		}

		public void setVirtualMachine(VirtualMachine virtualMachine) {
			this.virtualMachine = virtualMachine;
		}

	}

	public RuntimeEnvironment getRuntimeEnvironment() {
		return runtimeEnvironment;
	}

	public VirtualMachine getVirtualMachine() {
		return virtualMachine;
	}

}
