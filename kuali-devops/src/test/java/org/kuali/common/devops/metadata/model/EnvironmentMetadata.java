package org.kuali.common.devops.metadata.model;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.base.Optional;

@IdiotProofImmutable
public final class EnvironmentMetadata {

	private final MetadataUrl<Optional<String>> tomcatVersion;
	private final MetadataUrl<Optional<Long>> tomcatStartupTime;
	private final MetadataUrl<RemoteEnvironment> remoteEnvironment;

	private EnvironmentMetadata(Builder builder) {
		this.tomcatVersion = builder.tomcatVersion;
		this.tomcatStartupTime = builder.tomcatStartupTime;
		this.remoteEnvironment = builder.remoteEnvironment;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<EnvironmentMetadata> {

		private MetadataUrl<Optional<String>> tomcatVersion;
		private MetadataUrl<Optional<Long>> tomcatStartupTime;
		private MetadataUrl<RemoteEnvironment> remoteEnvironment;

		public Builder remoteEnvironment(MetadataUrl<RemoteEnvironment> remoteEnvironment) {
			this.remoteEnvironment = remoteEnvironment;
			return this;
		}

		public Builder tomcatVersion(MetadataUrl<Optional<String>> tomcatVersion) {
			this.tomcatVersion = tomcatVersion;
			return this;
		}

		public Builder tomcatStartupTime(MetadataUrl<Optional<Long>> tomcatStartupTime) {
			this.tomcatStartupTime = tomcatStartupTime;
			return this;
		}

		@Override
		public EnvironmentMetadata getInstance() {
			return new EnvironmentMetadata(this);
		}
	}

	public MetadataUrl<Optional<String>> getTomcatVersion() {
		return tomcatVersion;
	}

	public MetadataUrl<Optional<Long>> getTomcatStartupTime() {
		return tomcatStartupTime;
	}

	public MetadataUrl<RemoteEnvironment> getRemoteEnvironment() {
		return remoteEnvironment;
	}
}
