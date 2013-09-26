package org.kuali.common.util.file.model;

public class Artifact {

	public Artifact(Repository repository, RepoFile file, RepoFile checksum) {
		this.repository = repository;
		this.file = file;
		this.checksum = checksum;
	}

	private final Repository repository;
	private final RepoFile file;
	private final RepoFile checksum;

	public Repository getRepository() {
		return repository;
	}

	public RepoFile getFile() {
		return file;
	}

	public RepoFile getChecksum() {
		return checksum;
	}

}
