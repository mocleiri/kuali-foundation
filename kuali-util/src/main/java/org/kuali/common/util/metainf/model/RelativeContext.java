package org.kuali.common.util.metainf.model;

import java.io.File;

import org.kuali.common.util.Assert;
import org.kuali.common.util.file.CanonicalFile;
import org.springframework.util.ResourceUtils;

public class RelativeContext {

	public static final String DEFAULT_RELATIVE_URL_PREFIX = ResourceUtils.CLASSPATH_URL_PREFIX;
	public static final boolean DEFAULT_GENERATE_RELATIVE_PATHS = true;

	private final boolean generateRelativePaths;
	private final CanonicalFile parent;
	private final String urlPrefix;

	public RelativeContext(File parent) {
		this(parent, DEFAULT_RELATIVE_URL_PREFIX);
	}

	public RelativeContext(File parent, boolean generateRelativePaths) {
		this(parent, DEFAULT_RELATIVE_URL_PREFIX, generateRelativePaths);
	}

	public RelativeContext(File parent, String urlPrefix) {
		this(parent, urlPrefix, DEFAULT_GENERATE_RELATIVE_PATHS);
	}

	public RelativeContext(File parent, String urlPrefix, boolean generateRelativePaths) {
		Assert.noNulls(parent, urlPrefix);
		this.parent = new CanonicalFile(parent);
		this.urlPrefix = urlPrefix;
		this.generateRelativePaths = generateRelativePaths;
	}

	public String getUrlPrefix() {
		return urlPrefix;
	}

	public CanonicalFile getParent() {
		return parent;
	}

	public boolean isGenerateRelativePaths() {
		return generateRelativePaths;
	}

}
