package org.kuali.common.util.project.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.file.CanonicalFile;
import org.kuali.common.util.metainf.service.MetaInfUtils;
import org.springframework.util.ResourceUtils;

import com.google.common.base.Optional;

public final class ProjectResource {

	private final ProjectIdentifier project;
	private final Optional<String> prefix;
	private final String path;

	private ProjectResource(Builder builder) {
		this.project = builder.project;
		this.prefix = builder.prefix;
		this.path = builder.path;
	}

	public static ProjectResource create(ProjectIdentifier project, String path) {
		return builder(project, path).build();
	}

	public static ProjectResource create(String prefix, ProjectIdentifier project, String path) {
		return builder(project, path).prefix(prefix).build();
	}

	/**
	 * Automatically sets the prefix to {@code classpath:META-INF/}
	 */
	public static ProjectResource metainf(ProjectIdentifier project, String path) {
		return builder(project, path).classpathMetaInfPrefix().build();
	}

	public static Builder builder(ProjectIdentifier project, String path) {
		return new Builder(project, path);
	}

	public static class Builder {

		// Required
		private final ProjectIdentifier project;
		private final String path;

		// Optional
		private Optional<String> prefix = Optional.of(ResourceUtils.CLASSPATH_URL_PREFIX);

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
		 * {@code classpath:META-INF/}
		 */
		public Builder classpathMetaInfPrefix() {
			return prefix(ResourceUtils.CLASSPATH_URL_PREFIX + MetaInfUtils.METAINF_DIRECTORY_NAME + "/");
		}

		/**
		 * {@code /tmp/}
		 */
		public Builder directoryPrefix(File directory) {
			return prefix(new CanonicalFile(directory).getPath() + File.pathSeparator);
		}

		/**
		 * Typically {@code classpath:}
		 */
		public Builder prefix(String prefix) {
			this.prefix = Optional.of(prefix);
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
			checkNotNull(instance.prefix, "'prefix' cannot be null");
			if (instance.prefix.isPresent()) {
				checkArgument(!StringUtils.isBlank(instance.prefix.get()), "'prefix' cannot be blank");
			}
		}
	}

	public ProjectIdentifier getProject() {
		return project;
	}

	public Optional<String> getPrefix() {
		return prefix;
	}

	public String getPath() {
		return path;
	}

}
