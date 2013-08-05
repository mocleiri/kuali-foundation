package org.kuali.common.util.metainf.model;

import java.io.File;

import org.kuali.common.util.Assert;
import org.kuali.common.util.file.CanonicalFile;
import org.springframework.util.ResourceUtils;

public class RelativeContext {

	public static final String DEFAULT_RELATIVE_URL_PREFIX = ResourceUtils.CLASSPATH_URL_PREFIX;

	private final CanonicalFile parent;
	private final String urlPrefix;

	public RelativeContext(File parent) {
		this(parent, DEFAULT_RELATIVE_URL_PREFIX);
	}

	public RelativeContext(File parent, String urlPrefix) {
		Assert.noNulls("parent and urlPrefix are required", parent, urlPrefix);
		this.parent = new CanonicalFile(parent);
		this.urlPrefix = urlPrefix;
	}

	public String getUrlPrefix() {
		return urlPrefix;
	}

	public CanonicalFile getParent() {
		return parent;
	}

}
