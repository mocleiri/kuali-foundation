package org.kuali.common.util.project.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.file.CanonicalFile;
import org.kuali.common.util.metainf.service.MetaInfUtils;
import org.springframework.util.ResourceUtils;

public final class ProjectResource {

	private final String prefix;
	private final ProjectIdentifier project;
	private final String path;

	private ProjectResource(Builder builder) {
		this.project = builder.project;
		this.prefix = builder.prefix;
		this.path = builder.path;
	}

	/**
	 * Create a {@code ProjectResource} with the prefix set to {@code classpath:}
	 */
	public static ProjectResource create(ProjectIdentifier project, String path) {
		return builder(project, path).build();
	}

	/**
	 * Create a {@code ProjectResource} with the corresponding prefix
	 */
	public static ProjectResource create(String prefix, ProjectIdentifier project, String path) {
		return builder(project, path).prefix(prefix).build();
	}

	/**
	 * Create a {@code ProjectResource} with the prefix set to {@code classpath:}
	 */
	public static ProjectResource classpath(ProjectIdentifier project, String path) {
		return classpath(project, path, false);
	}

	/**
	 * Create a {@code ProjectResource} with the prefix set to {@code classpath:} or {@code classpath:META-INF/}
	 */
	public static ProjectResource classpath(ProjectIdentifier project, String path, boolean metainf) {
		return builder(project, path).classpathPrefix(metainf).build();
	}

	/**
	 * Create a {@code ProjectResource} with the prefix set to {@code directory} and optionally further prefixed with {@code META-INF}
	 */
	public static ProjectResource directory(File directory, ProjectIdentifier project, String path, boolean metainf) {
		return builder(project, path).directoryPrefix(directory, metainf).build();
	}

	/**
	 * Create a {@code ProjectResource} with the prefix set to {@code directory}
	 */
	public static ProjectResource directory(File directory, ProjectIdentifier project, String path) {
		return directory(directory, project, path, false);
	}

	public static Builder builder(ProjectIdentifier project, String path) {
		return new Builder(project, path);
	}

	public static class Builder {

		// Required
		private final ProjectIdentifier project;
		private final String path;

		// Optional
		private String prefix = ResourceUtils.CLASSPATH_URL_PREFIX;

		public Builder(ProjectIdentifier project, String path) {
			this.project = project;
			this.path = path;
		}

		/**
		 * {@code classpath:}
		 */
		public Builder classpathPrefix() {
			return prefix(ResourceUtils.CLASSPATH_URL_PREFIX);
		}

		/**
		 * {@code classpath:} or {@code classpath:META-INF/}
		 */
		public Builder classpathPrefix(boolean metainf) {
			if (metainf) {
				return prefix(ResourceUtils.CLASSPATH_URL_PREFIX + MetaInfUtils.METAINF_DIRECTORY_NAME + "/");
			} else {
				return classpathPrefix();
			}
		}

		/**
		 * {@code /tmp/}
		 */
		public Builder directoryPrefix(File directory) {
			return directoryPrefix(directory, false);
		}

		/**
		 * {@code /tmp/} or {@code /tmp/META-INF/}
		 */
		public Builder directoryPrefix(File directory, boolean metainf) {
			String path = new CanonicalFile(directory).getPath() + File.pathSeparator;
			if (metainf) {
				return prefix(path + MetaInfUtils.METAINF_DIRECTORY_NAME + File.pathSeparator);
			} else {
				return prefix(path);
			}
		}

		/**
		 * Typically {@code classpath:}
		 */
		public Builder prefix(String prefix) {
			this.prefix = prefix;
			return this;
		}

		public ProjectResource build() {
			ProjectResource instance = new ProjectResource(this);
			validate(instance);
			return instance;
		}

		private static void validate(ProjectResource instance) {
			checkNotNull(instance.project, "'project' cannot be null");
			checkArgument(!StringUtils.isBlank(instance.path), "'path' cannot be blank");
			checkArgument(!StringUtils.isBlank(instance.prefix), "'prefix' cannot be blank");
		}
	}

	public ProjectIdentifier getProject() {
		return project;
	}

	public String getPrefix() {
		return prefix;
	}

	public String getPath() {
		return path;
	}

}
