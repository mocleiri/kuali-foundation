package org.kuali.common.devops.metadata.model;

import org.kuali.common.devops.model.GlobalProperties;
import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

@IdiotProofImmutable
public final class EnvironmentMetadata {

	private final MetadataUrl<String> tomcatVersion;
	private final MetadataUrl<Long> tomcatStartupTime;
	private final MetadataUrl<GlobalProperties> environmentJsp;

	private EnvironmentMetadata(Builder builder) {
		this.tomcatVersion = builder.tomcatVersion;
		this.tomcatStartupTime = builder.tomcatStartupTime;
		this.environmentJsp = builder.environmentJsp;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<EnvironmentMetadata> {

		private MetadataUrl<String> tomcatVersion;
		private MetadataUrl<Long> tomcatStartupTime;
		private MetadataUrl<GlobalProperties> environmentJsp;

		public Builder environmentJsp(MetadataUrl<GlobalProperties> environmentJsp) {
			this.environmentJsp = environmentJsp;
			return this;
		}

		public Builder tomcatVersion(MetadataUrl<String> tomcatVersion) {
			this.tomcatVersion = tomcatVersion;
			return this;
		}

		public Builder tomcatStartupTime(MetadataUrl<Long> tomcatStartupTime) {
			this.tomcatStartupTime = tomcatStartupTime;
			return this;
		}

		@Override
		public EnvironmentMetadata getInstance() {
			return new EnvironmentMetadata(this);
		}
	}

	public MetadataUrl<String> getTomcatVersion() {
		return tomcatVersion;
	}

	public MetadataUrl<Long> getTomcatStartupTime() {
		return tomcatStartupTime;
	}

	public MetadataUrl<GlobalProperties> getEnvironmentJsp() {
		return environmentJsp;
	}
}
