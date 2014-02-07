package org.kuali.common.devops.model;

import static org.kuali.common.devops.logic.Examiner.ENV_JSP_FRAGMENT;
import static org.kuali.common.devops.logic.Manifests.MANIFEST_URL_FRAGMENT;
import static org.kuali.common.devops.logic.Tomcats.HEAP_URL_FRAGMENT;
import static org.kuali.common.devops.logic.Tomcats.VERSION_URL_FRAGMENT;

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
			tomcatVersion(DEFAULT_PREFIX + fqdn + VERSION_URL_FRAGMENT);
			tomcatHeap(DEFAULT_PREFIX + fqdn + HEAP_URL_FRAGMENT);
			envJsp(DEFAULT_PREFIX + fqdn + ENV_JSP_FRAGMENT);
			applicationManifest(DEFAULT_PREFIX + fqdn + MANIFEST_URL_FRAGMENT);
		}

		@Override
		public DeployEnvironmentUrls getInstance() {
			return new DeployEnvironmentUrls(this);
		}

		public Builder fqdn(String fqdn) {
			this.fqdn = fqdn;
			return this;
		}

		public Builder tomcatVersion(String tomcatVersion) {
			this.tomcatVersion = tomcatVersion;
			return this;
		}

		public Builder tomcatHeap(String tomcatHeap) {
			this.tomcatHeap = tomcatHeap;
			return this;
		}

		public Builder envJsp(String envJsp) {
			this.envJsp = envJsp;
			return this;
		}

		public Builder applicationManifest(String applicationManifest) {
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
