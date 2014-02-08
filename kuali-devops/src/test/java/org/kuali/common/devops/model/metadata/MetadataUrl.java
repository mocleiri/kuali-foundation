package org.kuali.common.devops.model.metadata;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.base.Function;
import com.google.common.base.Optional;

@IdiotProofImmutable
public final class MetadataUrl<T> {

	private final String url;
	private final Optional<String> content;
	private final Function<String, Optional<T>> converter;
	private final Optional<T> metadata;

	private MetadataUrl(Builder<T> builder) {
		this.url = builder.url;
		this.content = builder.content;
		this.converter = builder.converter;
		this.metadata = builder.metadata;
	}

	public static <T> MetadataUrl<T> create(String url, Optional<String> content, Function<String, Optional<T>> converter) {
		Builder<T> builder = builder();
		Optional<T> metadata = content.isPresent() ? converter.apply(content.get()) : Optional.<T> absent();
		return builder.url(url).content(content).converter(converter).metadata(metadata).build();
	}

	public static <T> Builder<T> builder() {
		return new Builder<T>();
	}

	public static class Builder<T> extends ValidatingBuilder<MetadataUrl<T>> {

		private String url;
		private Optional<String> content;
		private Function<String, Optional<T>> converter;
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

		public Builder<T> converter(Function<String, Optional<T>> converter) {
			this.converter = converter;
			return this;
		}

		@Override
		public MetadataUrl<T> getInstance() {
			return new MetadataUrl<T>(this);
		}

	}

	public String getUrl() {
		return url;
	}

	public Optional<String> getContent() {
		return content;
	}

	public Function<String, Optional<T>> getConverter() {
		return converter;
	}

	public Optional<T> getMetadata() {
		return metadata;
	}

}
