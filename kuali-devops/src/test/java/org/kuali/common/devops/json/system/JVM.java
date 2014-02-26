package org.kuali.common.devops.json.system;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutableWithBlanks;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@IdiotProofImmutableWithBlanks
@JsonDeserialize(builder = JVM.Builder.class)
@JsonPropertyOrder(alphabetic = true)
public final class JVM {

	private final OperatingSystem os;

	private JVM(Builder builder) {
		this.os = builder.os;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<JVM> {

		private OperatingSystem os;

		@Override
		public Set<ConstraintViolation<JVM>> violations() {
			return violations(new JVM(this));
		}

		@Override
		public JVM build() {
			return validate(new JVM(this));
		}

		public Builder withOs(OperatingSystem os) {
			this.os = os;
			return this;
		}

		public OperatingSystem getOs() {
			return os;
		}

		public void setOs(OperatingSystem os) {
			this.os = os;
		}

	}

	public OperatingSystem getOs() {
		return os;
	}

}
