package org.kuali.common.devops.table;

import com.google.common.base.Optional;

public class TableContext {

	private int indent;
	private boolean headers;
	private Optional<Integer> border = Optional.of(1);

	public int getIndent() {
		return indent;
	}

	public void setIndent(int indent) {
		this.indent = indent;
	}

	public boolean isHeaders() {
		return headers;
	}

	public void setHeaders(boolean headers) {
		this.headers = headers;
	}

	public Optional<Integer> getBorder() {
		return border;
	}

	public void setBorder(Optional<Integer> border) {
		this.border = border;
	}

}
