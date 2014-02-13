package org.kuali.common.devops.metadata.model;

import static org.kuali.common.util.validate.Validation.checkConstraints;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.base.Function;
import com.google.common.base.Optional;

@IdiotProofImmutable
public final class MetadataUrl<T> {

	private final String url;
	private final Optional<String> content;
	private final Function<String, T> converter;
	private final Optional<T> metadata;

	private MetadataUrl(Builder<T> builder) {
		this.url = builder.url;
		this.content = builder.content;
		this.converter = builder.converter;
		this.metadata = builder.metadata;
	}

	public static <T> Builder<T> builder() {
		return new Builder<T>();
	}

	public static class Builder<T> extends ValidatingBuilder<MetadataUrl<T>> {

		private String url;
		private Optional<String> content;
		private Function<String, T> converter;
		private Optional<T> metadata;

		public Builder<T> metadata(Optional<T> metadata) {
			this.metadata = metadata;
			return this;
		}

		public Builder<T> url(String url) {
			this.url = url;
			return this;
		}

		public Builder<T> content(Optional<String> content) {
			this.content = content;
			return this;
		}

		public Builder<T> converter(Function<String, T> converter) {
			this.converter = converter;
			return this;
		}

		@Override
		public MetadataUrl<T> build() {
			return checkConstraints(validator, new MetadataUrl<T>(this));
		}

	}

	public String getUrl() {
		return url;
	}

	public Optional<String> getContent() {
		return content;
	}

	public Function<String, T> getConverter() {
		return converter;
	}

	public Optional<T> getMetadata() {
		return metadata;
	}

}
