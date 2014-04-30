package org.kuali.common.devops.metadata.model;

import static com.google.common.base.Optional.absent;

import java.util.Properties;

import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;
import org.kuali.common.util.project.model.Project;

import com.google.common.base.Optional;

@IdiotProofImmutable
public final class EnvironmentMetadata {

	private final MetadataUrl<Optional<String>> tomcatVersion;
	private final MetadataUrl<Optional<Long>> tomcatStartupTime;
	private final MetadataUrl<RemoteEnvironment> remoteEnvironment;
	private final MetadataUrl<Properties> manifest;
	// These 2 are optional as they are derived from information contained in the manifest
	// If the manifest is not present, we have no way to figure out what the url's should be
	private final Optional<MetadataUrl<Project>> project;
	private final Optional<MetadataUrl<Properties>> config;

	private EnvironmentMetadata(Builder builder) {
		this.tomcatVersion = builder.tomcatVersion;
		this.tomcatStartupTime = builder.tomcatStartupTime;
		this.remoteEnvironment = builder.remoteEnvironment;
		this.manifest = builder.manifest;
		this.project = builder.project;
		this.config = builder.config;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<EnvironmentMetadata> {

		private MetadataUrl<Optional<String>> tomcatVersion;
		private MetadataUrl<Optional<Long>> tomcatStartupTime;
		private MetadataUrl<RemoteEnvironment> remoteEnvironment;
		private MetadataUrl<Properties> manifest;
		private Optional<MetadataUrl<Project>> project = absent();
		private Optional<MetadataUrl<Properties>> config = absent();

		public Builder configIsAbsent() {
			return config(Optional.<MetadataUrl<Properties>> absent());
		}

		public Builder config(Optional<MetadataUrl<Properties>> config) {
			this.config = config;
			return this;
		}

		public Builder config(MetadataUrl<Properties> config) {
			return config(Optional.of(config));
		}

		public Builder projectIsAbsent() {
			return project(Optional.<MetadataUrl<Project>> absent());
		}

		public Builder project(Optional<MetadataUrl<Project>> project) {
			this.project = project;
			return this;
		}

		public Builder project(MetadataUrl<Project> project) {
			return project(Optional.of(project));
		}

		public Builder manifest(MetadataUrl<Properties> manifest) {
			this.manifest = manifest;
			return this;
		}

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
		public EnvironmentMetadata build() {
			return validate(new EnvironmentMetadata(this));
		}

		public MetadataUrl<Optional<String>> getTomcatVersion() {
			return tomcatVersion;
		}

		public void setTomcatVersion(MetadataUrl<Optional<String>> tomcatVersion) {
			this.tomcatVersion = tomcatVersion;
		}

		public MetadataUrl<Optional<Long>> getTomcatStartupTime() {
			return tomcatStartupTime;
		}

		public void setTomcatStartupTime(MetadataUrl<Optional<Long>> tomcatStartupTime) {
			this.tomcatStartupTime = tomcatStartupTime;
		}

		public MetadataUrl<RemoteEnvironment> getRemoteEnvironment() {
			return remoteEnvironment;
		}

		public void setRemoteEnvironment(MetadataUrl<RemoteEnvironment> remoteEnvironment) {
			this.remoteEnvironment = remoteEnvironment;
		}

		public MetadataUrl<Properties> getManifest() {
			return manifest;
		}

		public void setManifest(MetadataUrl<Properties> manifest) {
			this.manifest = manifest;
		}

		public Optional<MetadataUrl<Project>> getProject() {
			return project;
		}

		public void setProject(Optional<MetadataUrl<Project>> project) {
			this.project = project;
		}

		public Optional<MetadataUrl<Properties>> getConfig() {
			return config;
		}

		public void setConfig(Optional<MetadataUrl<Properties>> config) {
			this.config = config;
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

	public MetadataUrl<Properties> getManifest() {
		return manifest;
	}

	public Optional<MetadataUrl<Project>> getProject() {
		return project;
	}

	public Optional<MetadataUrl<Properties>> getConfig() {
		return config;
	}
}
