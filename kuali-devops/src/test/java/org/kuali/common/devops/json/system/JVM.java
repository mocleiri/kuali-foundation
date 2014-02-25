package org.kuali.common.devops.json.system;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutableWithBlanks;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@IdiotProofImmutableWithBlanks
@JsonDeserialize(builder = JVM.Builder.class)
public final class JVM {

	private final OperatingSystem operatingSystem;

	private JVM(Builder builder) {
		this.operatingSystem = builder.operatingSystem;
	}

	public static Builder builder() {
		return new Builder();
	}

	@JsonPOJOBuilder(withPrefix = "")
	public static class Builder extends ValidatingBuilder<JVM> {

		private OperatingSystem operatingSystem;

		@Override
		public Set<ConstraintViolation<JVM>> violations() {
			return violations(new JVM(this));
		}

		@Override
		public JVM build() {
			return validate(new JVM(this));
		}

		public OperatingSystem getOperatingSystem() {
			return operatingSystem;
		}

		public void setOperatingSystem(OperatingSystem operatingSystem) {
			this.operatingSystem = operatingSystem;
		}

	}

	public OperatingSystem getOperatingSystem() {
		return operatingSystem;
	}

}
