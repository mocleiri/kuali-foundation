package org.kuali.common.util.file.model;

public final class RepoFile {

	public RepoFile(String path, long size) {
		this.path = path;
		this.size = size;
	}

	private final String path;
	private final long size;

	public String getPath() {
		return path;
	}

	public long getSize() {
		return size;
	}

}
