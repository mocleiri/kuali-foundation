package org.kuali.common.devops.model;

import java.util.Map;
import java.util.Properties;

import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.project.model.ImmutableProject;
import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;

@IdiotProofImmutable
public final class Application {

	private final ImmutableProject project;
	private final ImmutableMap<String, String> manifest;
	private final ImmutableMap<String, String> configuration;
	private final Optional<Database> database;
	private final Optional<Scm> scm;

	private Application(Builder builder) {
		this.project = ImmutableProject.copyOf(builder.project);
		this.configuration = ImmutableMap.copyOf(builder.configuration);
		this.manifest = ImmutableMap.copyOf(builder.manifest);
		this.database = builder.database;
		this.scm = builder.scm;
	}

	public static Application create(Project project, Properties manifest, Properties config, Optional<Database> database, Optional<Scm> scm) {
		return builder().project(project).manifest(manifest).configuration(config).database(database).scm(scm).build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<Application> {

		private Project project;
		private Map<String, String> configuration;
		private Map<String, String> manifest;
		private Optional<Database> database;
		private Optional<Scm> scm;

		public Builder scm(Optional<Scm> scm) {
			this.scm = scm;
			return this;
		}

		public Builder scm(Scm scm) {
			return scm(Optional.of(scm));
		}

		public Builder project(Project project) {
			this.project = project;
			return this;
		}

		public Builder configuration(Properties configuration) {
			return configuration(PropertyUtils.convert(configuration));
		}

		public Builder configuration(Map<String, String> configuration) {
			this.configuration = configuration;
			return this;
		}

		public Builder manifest(Properties manifest) {
			return manifest(PropertyUtils.convert(manifest));
		}

		public Builder manifest(Map<String, String> manifest) {
			this.manifest = manifest;
			return this;
		}

		public Builder database(Optional<Database> database) {
			this.database = database;
			return this;
		}

		public Builder database(Database database) {
			return database(Optional.of(database));
		}

		@Override
		public Application getInstance() {
			return new Application(this);
		}

		public Project getProject() {
			return project;
		}

		public void setProject(Project project) {
			this.project = project;
		}

		public Map<String, String> getConfiguration() {
			return configuration;
		}

		public void setConfiguration(Map<String, String> configuration) {
			this.configuration = configuration;
		}

		public Optional<Database> getDatabase() {
			return database;
		}

		public void setDatabase(Optional<Database> database) {
			this.database = database;
		}

		public Optional<Scm> getScm() {
			return scm;
		}

		public void setScm(Optional<Scm> scm) {
			this.scm = scm;
		}
	}

	public Project getProject() {
		return project;
	}

	public Map<String, String> getConfiguration() {
		return configuration;
	}

	public Optional<Database> getDatabase() {
		return database;
	}

	public Optional<Scm> getScm() {
		return scm;
	}

	public ImmutableMap<String, String> getManifest() {
		return manifest;
	}
}
