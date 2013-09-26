package org.kuali.common.util.file.model;

import java.util.List;

import com.google.common.collect.ImmutableList;

public final class RepoArtifacts {

	public RepoArtifacts(Repository repository, List<Artifact> artifacts) {
		this.repository = repository;
		this.artifacts = ImmutableList.copyOf(artifacts);
		this.size = getSize(artifacts);
	}

	private static long getSize(List<Artifact> artifacts) {
		long size = 0;
		for (Artifact artifact : artifacts) {
			size += artifact.getFile().getSize();
		}
		return size;
	}

	private final Repository repository;
	private final List<Artifact> artifacts;
	private final long size;

	public Repository getRepository() {
		return repository;
	}

	public List<Artifact> getArtifacts() {
		return artifacts;
	}

	public long getSize() {
		return size;
	}
}