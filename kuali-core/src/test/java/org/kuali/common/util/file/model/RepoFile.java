package org.kuali.common.util.file.model;

import org.kuali.common.util.Assert;

public final class RepoFile {

	public RepoFile(String path, long size) {
		Assert.noBlanks(path);
		Assert.isTrue(size >= 0, "size is negative");
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
