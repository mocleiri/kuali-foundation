package org.kuali.common.util.file.model;

import java.util.List;

import org.kuali.common.util.Assert;

public class DuplicateArtifact implements Comparable<DuplicateArtifact> {

	public DuplicateArtifact(String path, List<Artifact> artifacts) {
		Assert.noBlanks(path);
		Assert.noNulls(artifacts);
		this.path = path;
		this.artifacts = artifacts;
	}

	private final String path;
	private final List<Artifact> artifacts;

	@Override
	public int compareTo(DuplicateArtifact other) {
		int sizeComparison = Double.compare(artifacts.size(), other.getArtifacts().size());
		if (sizeComparison == 0) {
			return path.compareTo(other.getPath());
		} else {
			return sizeComparison;
		}
	}

	public String getPath() {
		return path;
	}

	public List<Artifact> getArtifacts() {
		return artifacts;
	}

}
