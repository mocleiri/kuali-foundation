package org.kuali.common.devops.model.metadata;

import com.google.common.base.Function;
import com.google.common.base.Optional;

public class MetadataUrl<T> {

	String url;
	Optional<String> content;
	Function<String, T> converter;
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

	public Function<String, T> getConverter() {
		return converter;
	}

	public void setConverter(Function<String, T> converter) {
		this.converter = converter;
	}

	public T getInstance() {
		return instance;
	}

	public void setInstance(T instance) {
		this.instance = instance;
	}

}
