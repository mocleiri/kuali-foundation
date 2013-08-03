package org.kuali.common.util.metainf.model;

import java.io.File;

import org.springframework.util.ResourceUtils;

public final class RelativeContext {

	public RelativeContext(File directory) {
		this(directory, DEFAULT_RELATIVE_URL_PREFIX);

	}

	public RelativeContext(File directory, String urlPrefix) {
		super();
		this.directory = directory;
		this.urlPrefix = urlPrefix;
	}

	public static final String DEFAULT_RELATIVE_URL_PREFIX = ResourceUtils.CLASSPATH_URL_PREFIX;

	private final File directory;
	private final String urlPrefix;

	public File getDirectory() {
		return directory;
	}

	public String getUrlPrefix() {
		return urlPrefix;
	}

}
