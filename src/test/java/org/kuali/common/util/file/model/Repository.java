package org.kuali.common.util.file.model;

import java.util.List;

import org.kuali.common.util.Assert;

import com.google.common.collect.ImmutableList;

public final class Repository implements Comparable<Repository> {

	public Repository(String name, List<RepoFile> files) {
		Assert.noBlanks(name);
		Assert.noNulls(files);
		this.name = name;
		this.files = ImmutableList.copyOf(files);
		this.size = getTotalSize(files);
	}

	@Override
	public int compareTo(Repository other) {
		return Double.compare(size, other.getSize());
	}

	private long getTotalSize(List<RepoFile> files) {
		long size = 0;
		for (RepoFile file : files) {
			size += file.getSize();
		}
		return size;
	}

	private final String name;
	private final List<RepoFile> files;
	private final long size;

	public String getName() {
		return name;
	}

	public List<RepoFile> getFiles() {
		return files;
	}

	public long getSize() {
		return size;
	}

}
