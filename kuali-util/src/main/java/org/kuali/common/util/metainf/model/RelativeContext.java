package org.kuali.common.util.metainf.model;

import java.io.File;

import org.springframework.util.ResourceUtils;

public class RelativeContext {

	public RelativeContext() {
		this(null, DEFAULT_RELATIVE_URL_PREFIX);

	}

	public RelativeContext(File directory, String urlPrefix) {
		super();
		this.directory = directory;
		this.urlPrefix = urlPrefix;
	}

	public static final String DEFAULT_RELATIVE_URL_PREFIX = ResourceUtils.CLASSPATH_URL_PREFIX;

	File directory;
	String urlPrefix = DEFAULT_RELATIVE_URL_PREFIX;

	public File getDirectory() {
		return directory;
	}

	public void setDirectory(File directory) {
		this.directory = directory;
	}

	public String getUrlPrefix() {
		return urlPrefix;
	}

	public void setUrlPrefix(String urlPrefix) {
		this.urlPrefix = urlPrefix;
	}

}
