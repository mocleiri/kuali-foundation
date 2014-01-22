package org.kuali.common.util.vm;

import org.kuali.common.util.bind.api.Bind;
import org.kuali.common.util.build.AwesomeBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

@IdiotProofImmutable
public class RuntimeEnvironment {

	private final Specification specification;

	private RuntimeEnvironment(Builder builder) {
		this.specification = builder.specification;
	}

	@Bind
	public static class Builder extends AwesomeBuilder<RuntimeEnvironment> {

		private Specification specification;

		public Builder specification(Specification specification) {
			this.specification = specification;
			return this;
		}

		@Override
		public RuntimeEnvironment getInstance() {
			return new RuntimeEnvironment(this);
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
