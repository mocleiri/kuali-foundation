package org.kuali.common.devops.model.metadata;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.base.Optional;

@IdiotProofImmutable
public final class EnvironmentMetadata {

	private final Optional<String> tomcatVersion;

	private EnvironmentMetadata(Builder builder) {
		this.tomcatVersion = builder.tomcatVersion;
	}

	public static class Builder extends ValidatingBuilder<EnvironmentMetadata> {

		private Optional<String> tomcatVersion;

		public Builder tomcatVersion(Optional<String> tomcatVersion) {
			this.tomcatVersion = tomcatVersion;
			return this;
		}

		@Override
		public EnvironmentMetadata getInstance() {
			return new EnvironmentMetadata(this);
		}

		public Optional<String> getTomcatVersion() {
			return tomcatVersion;
		}

		public void setTomcatVersion(Optional<String> tomcatVersion) {
			this.tomcatVersion = tomcatVersion;
		}

	}

	public Optional<String> getTomcatVersion() {
		return tomcatVersion;
	}

}
