package org.kuali.common.util.metainf.model;

import java.io.File;

import org.kuali.common.util.Assert;
import org.springframework.util.ResourceUtils;

public class RelativeContext {

	public static final String DEFAULT_RELATIVE_URL_PREFIX = ResourceUtils.CLASSPATH_URL_PREFIX;

	private final File directory;
	private final String urlPrefix;

	public RelativeContext(File directory) {
		this(directory, DEFAULT_RELATIVE_URL_PREFIX);
	}

	public RelativeContext(File directory, String urlPrefix) {
		super();
		Assert.notNull(directory, "directory is null");
		Assert.notNull(urlPrefix, "urlPrefix is null");
		this.directory = directory;
		this.urlPrefix = urlPrefix;
	}

	public File getDirectory() {
		return directory;
	}

	public String getUrlPrefix() {
		return urlPrefix;
	}

}
