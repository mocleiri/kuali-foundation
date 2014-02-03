package org.kuali.common.devops.model;

import java.util.Properties;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.project.model.ImmutableProject;
import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.property.ImmutableProperties;
import org.kuali.common.util.validate.IdiotProofImmutable;

@IdiotProofImmutable
public final class Application {

	private final ImmutableProject project;
	private final ImmutableProperties configuration;
	private final Database database;

	private Application(Builder builder) {
		this.project = ImmutableProject.copyOf(builder.project);
		this.configuration = ImmutableProperties.copyOf(builder.configuration);
		this.database = builder.database;
	}

	public static Application create(Project project, Properties config, Database database) {
		return builder().project(project).configuration(config).database(database).build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<Application> {

		private Project project;
		private Properties configuration;
		private Database database;

		public Builder project(Project project) {
			this.project = project;
			return this;
		}

		public Builder configuration(Properties configuration) {
			this.configuration = configuration;
			return this;
		}

		public Builder database(Database database) {
			this.database = database;
			return this;
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

		public Properties getConfiguration() {
			return configuration;
		}

		public void setConfiguration(ImmutableProperties configuration) {
			this.configuration = configuration;
		}

		public Database getDatabase() {
			return database;
		}

		public void setDatabase(Database database) {
			this.database = database;
		}
	}

	public Project getProject() {
		return project;
	}

	public Properties getConfiguration() {
		return configuration;
	}

	public Database getDatabase() {
		return database;
	}
}
