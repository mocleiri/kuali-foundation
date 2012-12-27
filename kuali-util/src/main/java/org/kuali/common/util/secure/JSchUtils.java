/**
 * Copyright 2010-2012 The Kuali Foundation
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
package org.kuali.common.util.secure;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.LocationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 *
 */
public class JSchUtils {

	private static final Logger logger = LoggerFactory.getLogger(JSchUtils.class);

	public static final void validateCopyFileSource(File file) {
		Assert.notNull(file);
		String path = LocationUtils.getCanonicalPath(file);
		if (!file.exists()) {
			throw new IllegalArgumentException("File [" + path + "] does not exist.");
		}
		if (file.isDirectory()) {
			throw new IllegalArgumentException("File [" + path + "] is a directory.");
		}
		if (!file.canRead()) {
			throw new IllegalArgumentException("File [" + path + "] is not readable.");
		}
	}

	public static final void validateCopyFileDestination(RemoteFile file) {
		Assert.notNull(file);
		Assert.notNull(file.getAbsolutePath());
		String normalized = LocationUtils.getNormalizedAbsolutePath(file.getAbsolutePath());
		if (!StringUtils.equals(normalized, file.getAbsolutePath())) {
			logger.info("Normalized path [{}] -> [{}]", file.getAbsolutePath(), normalized);
			file.setAbsolutePath(normalized);
		}
		Assert.hasLength(file.getAbsolutePath());
		if (file.isDirectory()) {
			throw new IllegalArgumentException("File [" + file + "] is a directory.");
		}
	}

	public static final void validateCopyLocation(String location, RemoteFile destination) {
		if (LocationUtils.isExistingFile(location)) {
			File source = new File(location);
			validateCopyFileSource(source);
		}
		validateCopyFileDestination(destination);
	}

}
