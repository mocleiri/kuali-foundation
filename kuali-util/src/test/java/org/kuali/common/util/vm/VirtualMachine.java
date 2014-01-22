package org.kuali.common.util.vm;

import org.kuali.common.util.build.AwesomeBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

@IdiotProofImmutable
public final class VirtualMachine {

	private final Specification specification;

	private VirtualMachine(Builder builder) {
		this.specification = builder.specification;
	}

	public static class Builder extends AwesomeBuilder<VirtualMachine> {

		private Specification specification;

		public Builder specification(Specification specification) {
			this.specification = specification;
			return this;
		}

		@Override
		public VirtualMachine getInstance() {
			return new VirtualMachine(this);
		}

		public Specification getSpecification() {
			return specification;
		}

		public void setSpecification(Specification specification) {
			this.specification = specification;
		}

	}

	public Specification getSpecification() {
		return specification;
	}

}
