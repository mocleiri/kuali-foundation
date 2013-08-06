package org.kuali.common.util.metainf.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.kuali.common.util.Assert;

public class WriteLines {

	private final WriteRequest request;
	private final List<String> lines;

	public WriteLines(WriteRequest request, List<String> lines) {
		Assert.noNulls(request, lines);
		this.request = request;
		this.lines = Collections.unmodifiableList(new ArrayList<String>(lines));
	}

	public List<String> getLines() {
		return lines;
	}

	public WriteRequest getRequest() {
		return request;
	}

}
