package org.kuali.common.util.file.model;

import java.util.List;

public class RepoStats {

	public RepoStats(Repository repository, List<RepoFile> checksums, List<RepoFile> artifacts, int artifactsWithChecksumsCount) {
		this.repository = repository;
		this.checksums = checksums;
		this.artifacts = artifacts;
		this.artifactsWithChecksumsCount = artifactsWithChecksumsCount;
	}

	private final Repository repository;
	private final List<RepoFile> checksums;
	private final List<RepoFile> artifacts;
	private final int artifactsWithChecksumsCount;

	public Repository getRepository() {
		return repository;
	}

	public List<RepoFile> getChecksums() {
		return checksums;
	}

	public List<RepoFile> getArtifacts() {
		return artifacts;
	}

	public int getArtifactsWithChecksumsCount() {
		return artifactsWithChecksumsCount;
	}

}
