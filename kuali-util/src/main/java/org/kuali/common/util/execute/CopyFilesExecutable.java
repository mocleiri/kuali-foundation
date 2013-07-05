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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.plexus.util.FileUtils;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class CopyFilesExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(CopyFilesExecutable.class);

	public static final List<String> DEFAULT_INCLUDES = Arrays.asList("**/*");
	public static final List<String> DEFAULT_EXCLUDES = Collections.emptyList();

	List<String> includes = DEFAULT_INCLUDES;
	List<String> excludes = DEFAULT_EXCLUDES;

	File srcDir;
	File dstDir;

	@Override
	public void execute() {

		// Make sure we are configured correctly
		Assert.notNull(srcDir, "srcDir is null");
		Assert.notNull(dstDir, "dstDir is null");
		Assert.isTrue(LocationUtils.exists(srcDir), "srcDir does not exist");

		try {
			String includesCSV = StringUtils.trimToNull(CollectionUtils.getCSV(CollectionUtils.toEmptyList(includes)));
			String excludesCSV = StringUtils.trimToNull(CollectionUtils.getCSV(CollectionUtils.toEmptyList(excludes)));
			logger.debug("src - [{}]", LocationUtils.getCanonicalPath(srcDir));
			logger.debug("dst - [{}]", LocationUtils.getCanonicalPath(dstDir));
			logger.debug("includes - [{}], excludes - [{}]", includesCSV, excludesCSV);
			FileUtils.forceMkdir(dstDir);
			FileUtils.copyDirectory(srcDir, dstDir, includesCSV, excludesCSV);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		}

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
}
