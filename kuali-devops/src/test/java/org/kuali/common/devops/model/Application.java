package org.kuali.common.devops.model;

import java.util.Properties;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.property.ImmutableProperties;
import org.kuali.common.util.validate.IdiotProofImmutable;

@IdiotProofImmutable
public final class Application {

	private final Project project;
	private final ImmutableProperties configuration;
	private final Database database;

	private Application(Builder builder) {
		this.project = builder.project;
		this.configuration = builder.configuration;
		this.database = builder.database;
	}

	public static class Builder extends ValidatingBuilder<Application> {

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

	public Properties getConfiguration() {
		return configuration;
	}

	public Database getDatabase() {
		return database;
	}
}
