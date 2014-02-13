package org.kuali.common.devops.model;

import static org.kuali.common.util.validate.Validation.checkValidation;

import java.util.Properties;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.project.model.ImmutableProject;
import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.property.ImmutableProperties;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.base.Optional;

@IdiotProofImmutable
public final class Application {

	private final ImmutableProject project;
	private final ImmutableProperties manifest;
	private final ImmutableProperties configuration;
	private final Optional<Database> database;
	private final Optional<Scm> scm;

	private Application(Builder builder) {
		this.project = ImmutableProject.copyOf(builder.project);
		this.configuration = ImmutableProperties.copyOf(builder.configuration);
		this.manifest = ImmutableProperties.copyOf(builder.manifest);
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
		private Properties configuration;
		private Properties manifest;
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
			this.configuration = configuration;
			return this;
		}

		public Builder manifest(Properties manifest) {
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
		public Application build() {
			return checkValidation(new Application(this));
		}

		public Project getProject() {
			return project;
		}

		public void setProject(Project project) {
			this.project = project;
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

		public Properties getConfiguration() {
			return configuration;
		}

		public void setConfiguration(Properties configuration) {
			this.configuration = configuration;
		}

		public Properties getManifest() {
			return manifest;
		}

		public void setManifest(Properties manifest) {
			this.manifest = manifest;
		}
	}

	public Project getProject() {
		return project;
	}

	public Optional<Database> getDatabase() {
		return database;
	}

	public Optional<Scm> getScm() {
		return scm;
	}

	public ImmutableProperties getManifest() {
		return manifest;
	}

	public ImmutableProperties getConfiguration() {
		return configuration;
	}

}
