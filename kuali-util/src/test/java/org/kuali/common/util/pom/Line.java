package org.kuali.common.util.pom;

public class Line {

	int index;
	String content;

	public Line() {
		this(0, null);
	}

	public Line(int index, String content) {
		super();
		this.index = index;
		this.content = content;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
