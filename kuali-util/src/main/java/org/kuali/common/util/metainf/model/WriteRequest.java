package org.kuali.common.util.metainf.model;

import java.io.File;

abstract class WriteRequest {

	WriteRequest(File outputFile, String encoding) {
		super();
		this.outputFile = outputFile;
		this.encoding = encoding;
	}

	private final File outputFile;
	private final String encoding;

	public File getOutputFile() {
		return outputFile;
	}

	public String getEncoding() {
		return encoding;
	}

}
