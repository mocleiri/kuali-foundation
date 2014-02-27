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

	private final String lineSeparator;
	private final OperatingSystem os;

	private JVM(Builder builder) {
		this.os = builder.os;
		this.lineSeparator = builder.lineSeparator;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<JVM> {

		private OperatingSystem os;
		private String lineSeparator;

		@Override
		public Set<ConstraintViolation<JVM>> violations() {
			return violations(make());
		}

		@Override
		public JVM build() {
			return validate(make());
		}

		private JVM make() {
			return new JVM(this);
		}

		public Builder withLineSeparator(String lineSeparator) {
			this.lineSeparator = lineSeparator;
			return this;
		}

		public Builder withOperatingSystem(OperatingSystem os) {
			this.os = os;
			return this;
		}

	}

	public String getLineSeparator() {
		return lineSeparator;
	}

	public OperatingSystem getOs() {
		return os;
	}

}
