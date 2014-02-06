package org.kuali.common.util.file.model;

import java.util.List;

import com.google.common.collect.ImmutableList;

public final class RepoArtifacts {

	public RepoArtifacts(Repository repository, List<ArtifactForTesting> artifacts) {
		this.repository = repository;
		this.artifacts = ImmutableList.copyOf(artifacts);
		this.size = getSize(artifacts);
	}

	private static long getSize(List<ArtifactForTesting> artifacts) {
		long size = 0;
		for (ArtifactForTesting artifact : artifacts) {
			size += artifact.getFile().getSize();
		}
		return size;
	}

	private final Repository repository;
	private final List<ArtifactForTesting> artifacts;
	private final long size;

	public Repository getRepository() {
		return repository;
	}

	public List<ArtifactForTesting> getArtifacts() {
		return artifacts;
	}

	public long getSize() {
		return size;
	}
}