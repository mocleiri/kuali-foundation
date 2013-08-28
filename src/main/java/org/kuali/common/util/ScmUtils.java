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
package org.kuali.common.util;

import java.io.File;
import java.util.ArrayList;

import org.codehaus.plexus.util.StringUtils;
import org.kuali.common.util.scm.ScmRequest;
import org.springframework.util.Assert;

/**
 * @deprecated
 */
@Deprecated
public class ScmUtils {

	/**
	 * Use <code>ScmConfig</code> instead
	 */
	@Deprecated
	public static org.kuali.common.util.service.ScmService getScmService(String url) {
		Assert.hasText(url, "URL has no text");
		// scm:svn:https://svn.kuali.org/repos/student/trunk
		String[] tokens = StringUtils.split(url, ":");
		String scmType = tokens[1].toUpperCase();
		org.kuali.common.util.service.ScmType type = org.kuali.common.util.service.ScmType.valueOf(scmType);
		switch (type) {
		case SVN:
			return new org.kuali.common.util.service.SvnService();
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
