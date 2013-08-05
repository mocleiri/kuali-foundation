package org.kuali.common.util.metainf.model;

import java.io.File;

import org.kuali.common.util.Assert;
import org.kuali.common.util.file.CanonicalFile;
import org.springframework.util.ResourceUtils;

public class RelativeContext {

	public static final String DEFAULT_RELATIVE_URL_PREFIX = ResourceUtils.CLASSPATH_URL_PREFIX;

	private final CanonicalFile parent;
	private final CanonicalFile outputFile;
	private final String urlPrefix;

	public RelativeContext(File outputFile) {
		this(outputFile, outputFile, DEFAULT_RELATIVE_URL_PREFIX);
	}

	public RelativeContext(File parent, File outputFile) {
		this(parent, outputFile, DEFAULT_RELATIVE_URL_PREFIX);
	}

	public RelativeContext(File parent, File outputFile, String urlPrefix) {
		Assert.noNulls("parent, outputFile, and urlPrefix are required", parent, outputFile, urlPrefix);
		this.parent = new CanonicalFile(parent);
		this.outputFile = new CanonicalFile(parent);
		this.urlPrefix = urlPrefix;
	}

	public String getUrlPrefix() {
		return urlPrefix;
	}

	public CanonicalFile getParent() {
		return parent;
	}

	public CanonicalFile getOutputFile() {
		return outputFile;
	}

}
