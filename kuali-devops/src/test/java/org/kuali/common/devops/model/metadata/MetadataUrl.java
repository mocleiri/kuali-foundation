package org.kuali.common.devops.model.metadata;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.base.Function;

@IdiotProofImmutable
public final class MetadataUrl<T> {

	private final String url;
	private final Function<String, T> contentConverter;

	private MetadataUrl(Builder<T> builder) {
		this.url = builder.url;
		this.contentConverter = builder.contentConverter;
	}

	public static <T> MetadataUrl<T> create(String url, Function<String, T> contentConverter) {
		Builder<T> builder = builder();
		return builder.url(url).contentConverter(contentConverter).build();
	}

	public static <T> Builder<T> builder() {
		return new Builder<T>();
	}

	public static class Builder<T> extends ValidatingBuilder<MetadataUrl<T>> {

		private String url;
		private Function<String, T> contentConverter;

		public Builder<T> url(String url) {
			this.url = url;
			return this;
		}

		public Builder<T> contentConverter(Function<String, T> contentConverter) {
			this.contentConverter = contentConverter;
			return this;
		}

		@Override
		public MetadataUrl<T> getInstance() {
			return new MetadataUrl<T>(this);
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public Function<String, T> getContentConverter() {
			return contentConverter;
		}

		public void setContentConverter(Function<String, T> converter) {
			this.contentConverter = converter;
		}

	}

	public String getUrl() {
		return url;
	}

	public Function<String, T> getContentConverter() {
		return contentConverter;
	}

}
