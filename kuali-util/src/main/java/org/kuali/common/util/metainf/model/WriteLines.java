package org.kuali.common.util.metainf.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WriteLines {

	private final List<String> lines;
	private final WriteRequest request;

	public WriteLines(List<String> lines, File outputFile, String encoding, File relativeDir) {
		this.request = new WriteRequest(outputFile, encoding, relativeDir);
		this.lines = Collections.unmodifiableList(new ArrayList<String>(lines));
	}

	public List<String> getLines() {
		return lines;
	}

	public WriteRequest getRequest() {
		return request;
	}

}
