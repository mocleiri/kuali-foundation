package org.kuali.common.util.file.model;

import java.util.List;

public class DuplicateArtifact {

	public DuplicateArtifact(String path, List<Repository> repositories) {
		this.path = path;
		this.repositories = repositories;
	}

	private final String path;
	private final List<Repository> repositories;

	public String getPath() {
		return path;
	}

	public List<Repository> getRepositories() {
		return repositories;
	}

}
