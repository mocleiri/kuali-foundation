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
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class ScpUtils {
	private static final Logger logger = LoggerFactory.getLogger(ScpUtils.class);

	public static final List<String> getScpExecArgs(ScpContext context, List<ScpFile> sources, ScpFile destination) {
		List<String> args = new ArrayList<String>();
		// Add explicitly provided args (if any)
		CollectionUtils.nullSafeAdd(args, context.getArgs());
		// Add "-o" args (if any)
		SSHUtils.addOptions(args, context.getOptions());
		// Add arg for custom ssh_config file (if provided and different from the default)
		SSHUtils.addConfigFile(args, context.getConfigFile(), SSHUtils.DEFAULT_CONFIG_FILE);
		// Add arg for custom private key (if any)
		SSHUtils.addIdentityFile(args, context.getPrivateKey());
		// Add arg for port (if provided and different from the default)
		// Capital P because the scp protocol uses -p internally
		SSHUtils.addPort(args, "-P", context.getPort(), SSHUtils.DEFAULT_PORT);
		// Add arg for source file
		args.add(toString(sources));
		// Add arg for destination file
		args.add(toString(destination));
		// Return the args list
		return args;
	}

	public static final List<String> getStrings(List<ScpFile> files) {
		List<String> strings = new ArrayList<String>();
		for (ScpFile file : files) {
			strings.add(toString(file));
		}
		return strings;
	}

	public static final String toString(List<ScpFile> files) {
		List<String> strings = getStrings(files);
		return CollectionUtils.getSpaceSeparatedString(strings);
	}

	public static final String toString(ScpFile file) {
		StringBuilder sb = new StringBuilder();
		if (!StringUtils.isBlank(file.getUsername())) {
			sb.append(file.getUsername() + "@");
		}
		if (!StringUtils.isBlank(file.getHostname())) {
			sb.append(file.getHostname() + ":");
		}
		sb.append(file.getFilename());
		return sb.toString();
	}

	public static final ScpFile getScpFile(File file) {
		ScpFile scpFile = new ScpFile();
		scpFile.setFilename(LocationUtils.getCanonicalPath(file));
		return scpFile;
	}

	public static final ScpFile getScpFile(String filename) {
		return getScpFile(new File(filename));
	}

	public static final boolean isLocalFile(ScpFile file) {
		return file.getHostname() == null;
	}

	/**
	 * If <code>destination</code> is local, complete some file system checks.
	 */
	public static final void validateDestination(ScpFile destination, List<ScpFile> sources) {
		validate(destination);
		if (!isLocalFile(destination)) {
			return;
		}
		File localFile = new File(destination.getFilename());
		if (sources.size() > 1) {
			// If we have more than one source, the local file system destination must be a directory
			LocationUtils.forceMkdir(localFile);
		} else {
			// Create any necessary but non-existent parent directories
			LocationUtils.forceMkdir(localFile.getParentFile());
		}
	}

	/**
	 * Make sure sources is non-null and contains at least one entry and make sure any local files exist and are readable.
	 */
	public static final void validateSources(List<ScpFile> sources) {
		Assert.notNull(sources);
		Assert.isTrue(sources.size() > 0);
		for (ScpFile source : sources) {
			validateSource(source);
		}
	}

	/**
	 * Make sure <code>source</code> exists and is readable if it is a local file.
	 */
	public static final void validateSource(ScpFile source) {
		validate(source);
		if (!isLocalFile(source)) {
			return;
		}
		File file = new File(source.getFilename());
		String path = LocationUtils.getCanonicalPath(file);
		if (!file.exists()) {
			throw new IllegalArgumentException("[" + path + "] does not exist");
		} else if (!file.canRead()) {
			throw new IllegalArgumentException("Cannot read from [" + path + "]");
		}
	}

	public static final void validate(List<ScpFile> files) {
		logger.debug("Validating {} scp files", files.size());
		for (ScpFile file : files) {
			validate(file);
		}
	}

	public static final boolean isValid(ScpFile file) {

		boolean notNull = (file != null);
		boolean hasFilename = !StringUtils.isBlank(file.getFilename());
		boolean hasHostname = !StringUtils.isBlank(file.getHostname());
		boolean hasUsername = !StringUtils.isBlank(file.getUsername());

		// Can't be null
		// Must always have a filename
		// If there is a username, there must also be a hostname
		return notNull && hasFilename && (hasUsername) ? hasHostname : true;
	}

	public static final void validate(ScpFile file) {
		Assert.isTrue(isValid(file));
	}
}
