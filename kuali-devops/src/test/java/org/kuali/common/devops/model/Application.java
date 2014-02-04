package org.kuali.common.devops.model;

import java.util.Map;
import java.util.Properties;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.project.model.ImmutableProject;
import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

@IdiotProofImmutable
public final class Application {

	private final ImmutableProject project;
	private final ImmutableMap<String, String> configuration;
	private final Database database;
	private final Optional<Scm> scm;

	private Application(Builder builder) {
		this.project = ImmutableProject.copyOf(builder.project);
		this.configuration = ImmutableMap.copyOf(builder.configuration);
		this.database = builder.database;
		this.scm = builder.scm;
	}

	public static Application create(Project project, Properties config, Database database) {
		return builder().project(project).configuration(config).database(database).build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<Application> {

		private Project project;
		private Map<String, String> configuration;
		private Database database;
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
			Map<String, String> map = Maps.newHashMap();
			for (String key : configuration.stringPropertyNames()) {
				map.put(key, configuration.getProperty(key));
			}
			return configuration(map);
		}

		public Builder configuration(Map<String, String> configuration) {
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

		public Map<String, String> getConfiguration() {
			return configuration;
		}

		public void setConfiguration(Map<String, String> configuration) {
			this.configuration = configuration;
		}

		public Database getDatabase() {
			return database;
		}

		public void setDatabase(Database database) {
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

	public Database getDatabase() {
		return database;
	}

	public Optional<Scm> getScm() {
		return scm;
	}
}
