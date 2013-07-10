package org.kuali.common.util;

import java.io.File;
import java.util.ArrayList;

import org.codehaus.plexus.util.StringUtils;
import org.kuali.common.util.service.ScmService;
import org.kuali.common.util.service.ScmType;
import org.kuali.common.util.service.SvnService;
import org.springframework.util.Assert;

public class ScmUtils {

	/**
	 * Use <code>ScmConfig</code> instead
	 */
	@Deprecated
	public static ScmService getScmService(String url) {
		Assert.hasText(url, "URL has no text");
		// scm:svn:https://svn.kuali.org/repos/student/trunk
		String[] tokens = StringUtils.split(url, ":");
		String scmType = tokens[1].toUpperCase();
		ScmType type = ScmType.valueOf(scmType);
		switch (type) {
		case SVN:
			return new SvnService();
		case GIT:
			throw new IllegalArgumentException("GIT support is coming soon!");
		default:
			throw new IllegalArgumentException("SCM type [" + scmType + "] is unknown");
		}
	}

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
