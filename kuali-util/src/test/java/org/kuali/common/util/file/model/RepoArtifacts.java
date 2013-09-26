package org.kuali.common.util.file.model;

import java.util.List;

import com.google.common.collect.ImmutableList;

public class RepoArtifacts {

	public RepoArtifacts(Repository repository, List<Artifact> artifacts) {
		this.repository = repository;
		this.artifacts = ImmutableList.copyOf(artifacts);
	}

	private final Repository repository;
	private final List<Artifact> artifacts;

	public Repository getRepository() {
		return repository;
	}

	public List<Artifact> getArtifacts() {
		return artifacts;
	}
}