package org.kuali.common.util.file.model;

import java.util.List;

import org.kuali.common.util.Assert;

import com.google.common.collect.ImmutableList;

public class Repository {

	public Repository(String name, List<RepoFile> files) {
		Assert.noBlanks(name);
		Assert.noNulls(files);
		this.name = name;
		this.files = ImmutableList.copyOf(files);
		this.size = getTotalSize(files);
	}

	private long getTotalSize(List<RepoFile> files) {
		long size = 0;
		for (RepoFile file : files) {
			size += file.getSize();
		}
		return size;
	}

	private final String name;
	private List<RepoFile> files;
	private long size;

	public List<RepoFile> getFiles() {
		return files;
	}

	public void setFiles(List<RepoFile> files) {
		this.files = files;
	}

	public String getName() {
		return name;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

}
