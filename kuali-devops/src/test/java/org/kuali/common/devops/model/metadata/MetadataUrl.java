package org.kuali.common.devops.model.metadata;

import com.google.common.base.Function;
import com.google.common.base.Optional;

public class MetadataUrl<T> {

	String url;
	Optional<String> content;
	Function<String, T> contentConverter;
	T instance;

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

	public Function<String, T> getContentConverter() {
		return contentConverter;
	}

	public void setContentConverter(Function<String, T> contentConverter) {
		this.contentConverter = contentConverter;
	}

	public T getInstance() {
		return instance;
	}

	public void setInstance(T instance) {
		this.instance = instance;
	}

}
