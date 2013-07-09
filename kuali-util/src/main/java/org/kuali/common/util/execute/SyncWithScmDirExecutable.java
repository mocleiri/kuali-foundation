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
package org.kuali.common.util.execute;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.kuali.common.util.Assert;
import org.kuali.common.util.service.ScmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SyncWithScmDirExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(SyncWithScmDirExecutable.class);

	public static final List<String> DEFAULT_INCLUDES = Arrays.asList("**/**");
	public static final List<String> DEFAULT_EXCLUDES = Arrays.asList("**/.svn/**", "**/.git/**");
	public static final boolean DEFAULT_COMMIT = false;
	public static final boolean DEFAULT_SKIP = false;

	List<String> includes = DEFAULT_INCLUDES;
	List<String> excludes = DEFAULT_EXCLUDES;
	boolean skip = DEFAULT_SKIP;
	boolean commit = DEFAULT_COMMIT;
	String commitMessage;
	ScmService service;
	File srcDir;
	File scmDir;

	@Override
	public void execute() {

		if (skip) {
			logger.info("Skipping file sync");
			return;
		}

		// Make sure we are configured correctly
		Assert.notNull(service, "service is null");
		Assert.notNull(srcDir, "srcDir is null");
		Assert.notNull(scmDir, "scmDir is null");
		Assert.isExistingDir(srcDir, "srcDir is not an existing directory");
		Assert.isExistingDir(scmDir, "scmDir is not an existing directory");
	}
}
