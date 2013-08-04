package org.kuali.common.util.metainf.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class WriteRequest {

	private final List<String> lines;
	private final File outputFile;
	private final String encoding;

	public WriteRequest(List<String> lines, File outputFile, String encoding) {
		super();
		this.lines = Collections.unmodifiableList(new ArrayList<String>(lines));
		this.outputFile = outputFile;
		this.encoding = encoding;
	}

	public List<String> getLines() {
		return lines;
	}

	public File getOutputFile() {
		return outputFile;
	}

	public String getEncoding() {
		return encoding;
	}

}
