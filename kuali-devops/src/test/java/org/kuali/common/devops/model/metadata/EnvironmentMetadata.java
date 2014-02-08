package org.kuali.common.devops.model.metadata;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.base.Optional;

@IdiotProofImmutable
public final class EnvironmentMetadata {

	private final String fqdn;
	private final Optional<String> tomcatVersion;
	private final Optional<String> tomcatHeap;
	private final Optional<String> systemPropertiesJsp;
	private final Optional<String> applicationManifest;
	private final Optional<String> projectProperties;
	private final Optional<String> projectConfiguration;

	private EnvironmentMetadata(Builder builder) {
		this.fqdn = builder.fqdn;
		this.tomcatVersion = builder.tomcatVersion;
		this.tomcatHeap = builder.tomcatHeap;
		this.systemPropertiesJsp = builder.systemPropertiesJsp;
		this.applicationManifest = builder.applicationManifest;
		this.projectProperties = builder.projectProperties;
		this.projectConfiguration = builder.projectConfiguration;
	}

	public static class Builder extends ValidatingBuilder<EnvironmentMetadata> {

		private String fqdn;
		private Optional<String> tomcatVersion;
		private Optional<String> tomcatHeap;
		private Optional<String> systemPropertiesJsp;
		private Optional<String> applicationManifest;
		private Optional<String> projectProperties;
		private Optional<String> projectConfiguration;

		public Builder fqdn(String fqdn) {
			this.fqdn = fqdn;
			return this;
		}

		public Builder tomcatVersion(Optional<String> tomcatVersion) {
			this.tomcatVersion = tomcatVersion;
			return this;
		}

		public Builder tomcatHeap(Optional<String> tomcatHeap) {
			this.tomcatHeap = tomcatHeap;
			return this;
		}

		public Builder systemPropertiesJsp(Optional<String> systemPropertiesJsp) {
			this.systemPropertiesJsp = systemPropertiesJsp;
			return this;
		}

		public Builder applicationManifest(Optional<String> applicationManifest) {
			this.applicationManifest = applicationManifest;
			return this;
		}

		public Builder projectProperties(Optional<String> projectProperties) {
			this.projectProperties = projectProperties;
			return this;
		}

		public Builder projectConfiguration(Optional<String> projectConfiguration) {
			this.projectConfiguration = projectConfiguration;
			return this;
		}

		@Override
		public EnvironmentMetadata getInstance() {
			return new EnvironmentMetadata(this);
		}

		public String getFqdn() {
			return fqdn;
		}

		public void setFqdn(String fqdn) {
			this.fqdn = fqdn;
		}

		public Optional<String> getTomcatVersion() {
			return tomcatVersion;
		}

		public void setTomcatVersion(Optional<String> tomcatVersion) {
			this.tomcatVersion = tomcatVersion;
		}

		public Optional<String> getTomcatHeap() {
			return tomcatHeap;
		}

		public void setTomcatHeap(Optional<String> tomcatHeap) {
			this.tomcatHeap = tomcatHeap;
		}

		public Optional<String> getSystemPropertiesJsp() {
			return systemPropertiesJsp;
		}

		public void setSystemPropertiesJsp(Optional<String> systemPropertiesJsp) {
			this.systemPropertiesJsp = systemPropertiesJsp;
		}

		public Optional<String> getApplicationManifest() {
			return applicationManifest;
		}

		public void setApplicationManifest(Optional<String> applicationManifest) {
			this.applicationManifest = applicationManifest;
		}

		public Optional<String> getProjectProperties() {
			return projectProperties;
		}

		public void setProjectProperties(Optional<String> projectProperties) {
			this.projectProperties = projectProperties;
		}

		public Optional<String> getProjectConfiguration() {
			return projectConfiguration;
		}

		public void setProjectConfiguration(Optional<String> projectConfiguration) {
			this.projectConfiguration = projectConfiguration;
		}

	}

	public String getFqdn() {
		return fqdn;
	}

	public Optional<String> getTomcatVersion() {
		return tomcatVersion;
	}

	public Optional<String> getTomcatHeap() {
		return tomcatHeap;
	}

	public Optional<String> getSystemPropertiesJsp() {
		return systemPropertiesJsp;
	}

	public Optional<String> getApplicationManifest() {
		return applicationManifest;
	}

	public Optional<String> getProjectProperties() {
		return projectProperties;
	}

	public Optional<String> getProjectConfiguration() {
		return projectConfiguration;
	}

}
