package org.kuali.common.devops.model;

import static com.google.common.base.Preconditions.checkNotNull;

import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.property.ImmutableProperties;

public final class Application {

	private final Project project;
	private final ImmutableProperties configuration;
	private final Database database;

	private Application(Builder builder) {
		this.project = builder.project;
		this.configuration = builder.configuration;
		this.database = builder.database;
	}

	public static class Builder {

		private Project project;
		private ImmutableProperties configuration;
		private Database database;

		public Builder project(Project project) {
			this.project = project;
			return this;
		}

		public Builder configuration(ImmutableProperties configuration) {
			this.configuration = configuration;
			return this;
		}

		public Builder database(Database database) {
			this.database = database;
			return this;
		}

		public Application build() {
			Application instance = new Application(this);
			validate(instance);
			return instance;
		}

		private static void validate(Application instance) {
			checkNotNull(instance.project, "'project' cannot be null");
			checkNotNull(instance.configuration, "'configuration' cannot be null");
			checkNotNull(instance.database, "'database' cannot be null");
		}

		public Project getProject() {
			return project;
		}

		public void setProject(Project project) {
			this.project = project;
		}

		public ImmutableProperties getConfiguration() {
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

	public ImmutableProperties getConfiguration() {
		return configuration;
	}

	public Database getDatabase() {
		return database;
	}
}
