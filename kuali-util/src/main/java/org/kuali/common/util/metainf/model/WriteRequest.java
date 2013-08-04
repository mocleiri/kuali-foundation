package org.kuali.common.util.metainf.model;

import java.io.File;

import org.kuali.common.util.Assert;

abstract class WriteRequest {

	private final File outputFile;
	private final String encoding;

	WriteRequest(File outputFile, String encoding) {
		super();
		Assert.notNull(outputFile, "outputFile is null");
		Assert.notNull(encoding, "encoding is null");
		this.outputFile = outputFile;
		this.encoding = encoding;
	}

	public File getOutputFile() {
		return outputFile;
	}

	public String getEncoding() {
		return encoding;
	}

}
