package org.kuali.common.util;

import java.io.File;
import java.util.ArrayList;

public class ScmUtils {

	public static ScmRequest cloneOrNew(ScmRequest request) {
		if (request == null) {
			return new ScmRequest();
		} else {
			return clone(request);
		}
	}

	public static ScmRequest clone(ScmRequest request) {
		ScmRequest clone = new ScmRequest();
		clone.setCommitMessage(request.getCommitMessage());

		if (!CollectionUtils.isEmpty(request.getAdds())) {
			clone.setAdds(new ArrayList<File>(request.getAdds()));
		}

		if (!CollectionUtils.isEmpty(request.getDeletes())) {
			clone.setDeletes(new ArrayList<File>(request.getDeletes()));
		}

		if (!CollectionUtils.isEmpty(request.getCommits())) {
			clone.setCommits(new ArrayList<File>(request.getCommits()));
		}
		return clone;
	}

}
