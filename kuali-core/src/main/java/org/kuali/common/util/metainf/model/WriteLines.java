package org.kuali.common.util.metainf.model;

import java.util.List;

import org.kuali.common.util.Assert;
import org.kuali.common.util.ListUtils;

public class WriteLines {

	private final WriteRequest request;
	private final List<String> lines;

	public WriteLines(WriteRequest request, List<String> lines) {
		Assert.noNulls(request, lines);
		this.request = request;
		this.lines = ListUtils.newImmutableArrayList(lines);
	}

	public List<String> getLines() {
		return lines;
	}

	public WriteRequest getRequest() {
		return request;
	}

}
