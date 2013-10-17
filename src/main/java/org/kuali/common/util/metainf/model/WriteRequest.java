package org.kuali.common.util.metainf.model;

import java.io.File;

import org.kuali.common.util.Assert;

public class WriteRequest {

	private static final File DEFAULT_RELATIVE_DIR = null;

	private final File outputFile;
	private final String encoding;
	private final File relativeDir;

	public WriteRequest(File outputFile, String encoding) {
		this(outputFile, encoding, DEFAULT_RELATIVE_DIR);
	}

	public WriteRequest(File outputFile, String encoding, File relativeDir) {
		Assert.noNulls(outputFile, encoding);
		this.outputFile = outputFile;
		this.encoding = encoding;
		this.relativeDir = relativeDir;
	}

	public File getOutputFile() {
		return outputFile;
	}

	public String getEncoding() {
		return encoding;
	}

	public File getRelativeDir() {
		return relativeDir;
	}

}
