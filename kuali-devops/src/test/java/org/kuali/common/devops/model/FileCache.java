package org.kuali.common.devops.model;

import static org.kuali.common.util.validate.Validation.checkValidation;

import java.io.File;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.base.Optional;

@IdiotProofImmutable
public final class FileCache {

	private final String url;
	private final Optional<String> content;
	private final File cache;

	private FileCache(Builder builder) {
		this.url = builder.url;
		this.content = builder.content;
		this.cache = builder.cache;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<FileCache> {

		private String url;
		private Optional<String> content;
		private File cache;

		public Builder url(String url) {
			this.url = url;
			return this;
		}

		public Builder content(Optional<String> content) {
			this.content = content;
			return this;
		}

		public Builder cache(File cache) {
			this.cache = cache;
			return this;
		}

		@Override
		public FileCache build() {
			return checkValidation(validator, new FileCache(this));
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public Optional<String> getContent() {
			return content;
		}

		public void setContent(Optional<String> content) {
			this.content = content;
		}

		public File getCache() {
			return cache;
		}

		public void setCache(File cache) {
			this.cache = cache;
		}

	}

	public String getUrl() {
		return url;
	}

	public Optional<String> getContent() {
		return content;
	}

	public File getCache() {
		return cache;
	}

}
