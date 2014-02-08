package org.kuali.common.devops.metadata.model;

import java.util.Properties;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.base.Optional;

@IdiotProofImmutable
public final class EnvironmentMetadata {

	private final MetadataUrl<Optional<String>> tomcatVersion;
	private final MetadataUrl<Optional<Long>> tomcatStartupTime;
	private final MetadataUrl<RemoteEnvironment> remoteEnvironment;
	private final MetadataUrl<Properties> manifest;
	private final MetadataUrl<Properties> project;

	private EnvironmentMetadata(Builder builder) {
		this.tomcatVersion = builder.tomcatVersion;
		this.tomcatStartupTime = builder.tomcatStartupTime;
		this.remoteEnvironment = builder.remoteEnvironment;
		this.manifest = builder.manifest;
		this.project = builder.project;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<EnvironmentMetadata> {

		private MetadataUrl<Optional<String>> tomcatVersion;
		private MetadataUrl<Optional<Long>> tomcatStartupTime;
		private MetadataUrl<RemoteEnvironment> remoteEnvironment;
		private MetadataUrl<Properties> manifest;
		private MetadataUrl<Properties> project;

		public Builder project(MetadataUrl<Properties> project) {
			this.project = project;
			return this;
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
		public EnvironmentMetadata getInstance() {
			return new EnvironmentMetadata(this);
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

		public MetadataUrl<Properties> getProject() {
			return project;
		}

		public void setProject(MetadataUrl<Properties> project) {
			this.project = project;
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

	public MetadataUrl<Properties> getProject() {
		return project;
	}
}
