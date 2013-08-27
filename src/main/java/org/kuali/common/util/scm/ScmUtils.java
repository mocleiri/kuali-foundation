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

import org.codehaus.plexus.util.StringUtils;
import org.springframework.util.Assert;

public class ScmUtils {

	/**
	 * Use <code>ScmConfig</code> instead
	 */
	public static ScmService getScmService(String vendor) {
		Assert.hasText(vendor, "vendor is blank");
		ScmType type = ScmType.valueOf(StringUtils.upperCase(vendor));
		switch (type) {
		case SVN:
			return new SvnService();
		case GIT:
			throw new IllegalArgumentException("GIT support is coming soon!");
		default:
			throw new IllegalArgumentException("SCM type [" + vendor + "] is unknown");
		}
	}

}
