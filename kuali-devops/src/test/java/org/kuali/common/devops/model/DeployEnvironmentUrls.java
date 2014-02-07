package org.kuali.common.devops.model;

import static org.kuali.common.devops.logic.Examiner.ENV_JSP_FRAGMENT;
import static org.kuali.common.devops.logic.Manifests.MANIFEST_LOCATION;
import static org.kuali.common.devops.logic.Tomcats.HEAP_FRAGMENT;
import static org.kuali.common.devops.logic.Tomcats.RELEASE_NOTES_FRAGMENT;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.base.Optional;

@IdiotProofImmutable
public final class DeployEnvironmentUrls {

	private final String fqdn;
	private final String tomcatVersion;
	private final String tomcatHeap;
	private final String envJsp;
	private final String applicationManifest;
	private final Optional<String> projectProperties;
	private final Optional<String> projectConfiguration;

	private DeployEnvironmentUrls(Builder builder) {
		this.fqdn = builder.fqdn;
		this.tomcatVersion = builder.tomcatVersion;
		this.tomcatHeap = builder.tomcatHeap;
		this.envJsp = builder.envJsp;
		this.applicationManifest = builder.applicationManifest;
		this.projectProperties = builder.projectProperties;
		this.projectConfiguration = builder.projectConfiguration;
	}

	public static Builder builder(String fqdn) {
		return new Builder(fqdn);
	}

	public static class Builder extends ValidatingBuilder<DeployEnvironmentUrls> {

		public static final String DEFAULT_PREFIX = "http://";

		private String fqdn;
		private String tomcatVersion;
		private String tomcatHeap;
		private String envJsp;
		private String applicationManifest;
		private Optional<String> projectProperties = Optional.absent();
		private Optional<String> projectConfiguration = Optional.absent();

		public Builder() {
		}

		public Builder(String fqdn) {
			this.fqdn = fqdn;
			releaseNotes(DEFAULT_PREFIX + fqdn + RELEASE_NOTES_FRAGMENT);
			heap(DEFAULT_PREFIX + fqdn + HEAP_FRAGMENT);
			envJsp(DEFAULT_PREFIX + fqdn + ENV_JSP_FRAGMENT);
			manifest(DEFAULT_PREFIX + fqdn + MANIFEST_LOCATION);
		}

		@Override
		public DeployEnvironmentUrls getInstance() {
			return new DeployEnvironmentUrls(this);
		}

		public Builder fqdn(String fqdn) {
			this.fqdn = fqdn;
			return this;
		}

		public Builder releaseNotes(String releaseNotes) {
			this.tomcatVersion = releaseNotes;
			return this;
		}

		public Builder heap(String heap) {
			this.tomcatHeap = heap;
			return this;
		}

		public Builder envJsp(String envJsp) {
			this.envJsp = envJsp;
			return this;
		}

		public Builder manifest(String manifest) {
			this.applicationManifest = manifest;
			return this;
		}

		public Builder projectProperties(Optional<String> projectProperties) {
			this.projectProperties = projectProperties;
			return this;
		}

		public Builder configuration(Optional<String> configuration) {
			this.projectConfiguration = configuration;
			return this;
		}

		public String getTomcatVersion() {
			return tomcatVersion;
		}

		public void setTomcatVersion(String releaseNotes) {
			this.tomcatVersion = releaseNotes;
		}

		public String getTomcatHeap() {
			return tomcatHeap;
		}

		public void setTomcatHeap(String heap) {
			this.tomcatHeap = heap;
		}

		public String getEnvJsp() {
			return envJsp;
		}

		public void setEnvJsp(String envJsp) {
			this.envJsp = envJsp;
		}

		public String getApplicationManifest() {
			return applicationManifest;
		}

		public void setApplicationManifest(String manifest) {
			this.applicationManifest = manifest;
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

		public void setProjectConfiguration(Optional<String> configuration) {
			this.projectConfiguration = configuration;
		}

		public String getFqdn() {
			return fqdn;
		}

		public void setFqdn(String fqdn) {
			this.fqdn = fqdn;
		}

	}

	public String getTomcatVersion() {
		return tomcatVersion;
	}

	public String getTomcatHeap() {
		return tomcatHeap;
	}

	public String getEnvJsp() {
		return envJsp;
	}

	public String getApplicationManifest() {
		return applicationManifest;
	}

	public Optional<String> getProjectProperties() {
		return projectProperties;
	}

	public Optional<String> getProjectConfiguration() {
		return projectConfiguration;
	}

	public String getFqdn() {
		return fqdn;
	}

}
