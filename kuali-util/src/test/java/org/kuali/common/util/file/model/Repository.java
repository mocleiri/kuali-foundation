package org.kuali.common.util.file.model;

import java.util.List;

public class Repository {

	public Repository(String name, List<RepoFile> files) {
		this.name = name;
		this.files = files;
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
