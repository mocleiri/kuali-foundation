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
	private final OperatingSystem operatingSystem;

	private JVM(Builder builder) {
		this.operatingSystem = builder.operatingSystem;
		this.lineSeparator = builder.lineSeparator;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<JVM> {

		private OperatingSystem operatingSystem;
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

		public Builder withOperatingSystem(OperatingSystem operatingSystem) {
			this.operatingSystem = operatingSystem;
			return this;
		}

		public OperatingSystem getOperatingSystem() {
			return operatingSystem;
		}

		public void setOperatingSystem(OperatingSystem operatingSystem) {
			this.operatingSystem = operatingSystem;
		}

		public String getLineSeparator() {
			return lineSeparator;
		}

		public void setLineSeparator(String lineSeparator) {
			this.lineSeparator = lineSeparator;
		}

	}

	public String getLineSeparator() {
		return lineSeparator;
	}

	public OperatingSystem getOperatingSystem() {
		return operatingSystem;
	}

}
