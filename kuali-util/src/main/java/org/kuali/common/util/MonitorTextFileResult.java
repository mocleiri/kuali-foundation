package org.kuali.common.util;

import java.io.File;

public class MonitorTextFileResult {

	boolean exists;
	boolean contains;
	boolean timeoutExceeded;
	long elapsed;
	File file;
	String content;

	public MonitorTextFileResult() {
		this(false, false, false, -1);
	}

	public MonitorTextFileResult(boolean exists, boolean contains, boolean timeoutExceeded, long elapsed) {
		super();
		this.exists = exists;
		this.contains = contains;
		this.timeoutExceeded = timeoutExceeded;
		this.elapsed = elapsed;
	}

	public boolean isExists() {
		return exists;
	}

	public void setExists(boolean exists) {
		this.exists = exists;
	}

	public boolean isContains() {
		return contains;
	}

	public void setContains(boolean contains) {
		this.contains = contains;
	}

	public long getElapsed() {
		return elapsed;
	}

	public void setElapsed(long elapsed) {
		this.elapsed = elapsed;
	}

	public boolean isTimeoutExceeded() {
		return timeoutExceeded;
	}

	public void setTimeoutExceeded(boolean timeoutExceeded) {
		this.timeoutExceeded = timeoutExceeded;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
