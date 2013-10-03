package org.kuali.common.util.file.model;

import org.kuali.common.util.Assert;

import com.google.common.base.Optional;

public class Artifact {

	public Artifact(Repository repository, RepoFile file, Optional<RepoFile> checksum) {
		Assert.noNulls(repository, file, checksum);
		this.repository = repository;
		this.file = file;
		this.checksum = checksum;
	}

	private final Repository repository;
	private final RepoFile file;
	private final Optional<RepoFile> checksum;

	public Repository getRepository() {
		return repository;
	}

	public RepoFile getFile() {
		return file;
	}

	public Optional<RepoFile> getChecksum() {
		return checksum;
	}

}
