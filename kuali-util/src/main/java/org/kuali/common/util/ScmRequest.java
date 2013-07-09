package org.kuali.common.util;

import java.io.File;
import java.util.List;

public class ScmRequest {

	List<File> adds;
	List<File> deletes;
	List<File> commits;
	String commitMessage;

	public List<File> getAdds() {
		return adds;
	}

	public void setAdds(List<File> adds) {
		this.adds = adds;
	}

	public List<File> getDeletes() {
		return deletes;
	}

	public void setDeletes(List<File> deletes) {
		this.deletes = deletes;
	}

	public List<File> getCommits() {
		return commits;
	}

	public void setCommits(List<File> commits) {
		this.commits = commits;
	}

	public String getCommitMessage() {
		return commitMessage;
	}

	public void setCommitMessage(String commitMessage) {
		this.commitMessage = commitMessage;
	}

}
