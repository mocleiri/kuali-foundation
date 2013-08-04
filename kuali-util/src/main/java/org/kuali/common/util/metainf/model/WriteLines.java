package org.kuali.common.util.metainf.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class WriteLines extends WriteRequest {

	private final List<String> lines;

	public WriteLines(List<String> lines, File outputFile, String encoding) {
		super(outputFile, encoding);
		this.lines = Collections.unmodifiableList(new ArrayList<String>(lines));
	}

	public List<String> getLines() {
		return lines;
	}

}
