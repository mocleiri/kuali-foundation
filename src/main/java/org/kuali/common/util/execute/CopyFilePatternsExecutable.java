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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.plexus.util.FileUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.FileSystemUtils;
import org.kuali.common.util.LocationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @deprecated
 */
@Deprecated
public class CopyFilePatternsExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(CopyFilePatternsExecutable.class);

	public static final List<String> DEFAULT_INCLUDES = FileSystemUtils.DEFAULT_RECURSIVE_INCLUDES;
	public static final List<String> DEFAULT_EXCLUDES = new ArrayList<String>();

	List<String> includes = DEFAULT_INCLUDES;
	List<String> excludes = DEFAULT_EXCLUDES;

	File srcDir;
	File dstDir;
	File relativeDir;
	boolean skip;

	@Override
	public void execute() {

		if (skip) {
			return;
		}

		// Make sure we are configured correctly
		Assert.notNull(srcDir, "srcDir is null");
		Assert.notNull(dstDir, "dstDir is null");

		// Source directory has to exist already. We'll create destination directory if necessary
		Assert.isExistingDir(srcDir, "srcDir is not an existing directory");

		try {
			// Null safe conversion of the lists to CSV
			String includesCSV = StringUtils.trimToNull(CollectionUtils.getCSV(includes));
			String excludesCSV = StringUtils.trimToNull(CollectionUtils.getCSV(excludes));

			// Make sure we can create the destination directory
			FileUtils.forceMkdir(dstDir);

			// Show what we are up to
			logCopy();

			// Copy files from src to dst
			FileUtils.copyDirectory(srcDir, dstDir, includesCSV, excludesCSV);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		}

	}

	protected void logCopy() {
		String path = FileSystemUtils.getRelativePathQuietly(relativeDir, dstDir);
		Object[] args = { path, org.kuali.common.util.LoggerUtils.getLogMsg(includes, excludes) };
		logger.debug("srcDir - [{}]", LocationUtils.getCanonicalPath(srcDir));
		logger.info("Copying to - [{}] - {}", args);
	}

	public List<String> getIncludes() {
		return includes;
	}

	public void setIncludes(List<String> includes) {
		this.includes = includes;
	}

	public List<String> getExcludes() {
		return excludes;
	}

	public void setExcludes(List<String> excludes) {
		this.excludes = excludes;
	}

	public File getSrcDir() {
		return srcDir;
	}

	public void setSrcDir(File srcDir) {
		this.srcDir = srcDir;
	}

	public File getDstDir() {
		return dstDir;
	}

	public void setDstDir(File dstDir) {
		this.dstDir = dstDir;
	}

	public File getRelativeDir() {
		return relativeDir;
	}

	public void setRelativeDir(File relativeDir) {
		this.relativeDir = relativeDir;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}
}
