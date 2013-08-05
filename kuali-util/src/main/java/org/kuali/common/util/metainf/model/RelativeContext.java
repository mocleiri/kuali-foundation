package org.kuali.common.util.metainf.model;

import java.io.File;

import org.kuali.common.util.Assert;
import org.kuali.common.util.file.CanonicalFile;
import org.springframework.util.ResourceUtils;

public class RelativeContext {

	public static final String DEFAULT_RELATIVE_URL_PREFIX = ResourceUtils.CLASSPATH_URL_PREFIX;

	private final File parent;
	private final File child;
	private final String urlPrefix;

	public RelativeContext(File parent) {
		this(parent, parent, DEFAULT_RELATIVE_URL_PREFIX);
	}

	public RelativeContext(File parent, File child) {
		this(parent, child, DEFAULT_RELATIVE_URL_PREFIX);
	}

	public RelativeContext(File parent, File child, String urlPrefix) {
		Assert.noNulls("parent, child, and urlPrefix are required", parent, child, urlPrefix);
		this.parent = new CanonicalFile(parent);
		this.child = new CanonicalFile(parent);
		this.urlPrefix = urlPrefix;
	}

	public String getUrlPrefix() {
		return urlPrefix;
	}

	public File getParent() {
		return parent;
	}

	public File getChild() {
		return child;
	}

}
