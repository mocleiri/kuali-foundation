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
	}

	private final String name;
	private List<RepoFile> files;

	public List<RepoFile> getFiles() {
		return files;
	}

	public void setFiles(List<RepoFile> files) {
		this.files = files;
	}

	public String getName() {
		return name;
	}

}
