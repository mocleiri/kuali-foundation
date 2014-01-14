/**
 * Copyright 2010-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.common.util.scm;

import java.io.File;
import java.util.List;

public class ScmRequest {

	List<File> adds;
	List<File> deletes;
	List<File> commits;
	List<File> updates;
	String commitMessage;

	public ScmRequest() {
		this(null, null, null, null);
	}

	public ScmRequest(List<File> adds, List<File> deletes, List<File> commits, String commitMessage) {
		super();
		this.adds = adds;
		this.deletes = deletes;
		this.commits = commits;
		this.commitMessage = commitMessage;
	}

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

	public List<File> getUpdates() {
		return updates;
	}

	public void setUpdates(List<File> updates) {
		this.updates = updates;
	}

}
