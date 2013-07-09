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

import org.kuali.common.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SyncDirsExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(SyncDirsExecutable.class);

	File srcDir;
	File dstDir;
	boolean skip;

	@Override
	public void execute() {
		if (skip) {
			logger.info("Skipping file sync");
			return;
		}

		// Make sure we are configured correctly
		Assert.notNull(srcDir, "srcDir is null");
		Assert.notNull(dstDir, "dstDir is null");
		Assert.isExistingDir(srcDir, "srcDir is not an existing directory");
	}
}
