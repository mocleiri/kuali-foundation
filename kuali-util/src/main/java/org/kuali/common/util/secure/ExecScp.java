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
import org.kuali.common.util.service.DefaultExecService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 *
 */
public class ExecScp extends DefaultExecService implements Scp {

	private static final Logger logger = LoggerFactory.getLogger(ExecScp.class);
	private static final ScpContext DEFAULT_SCP_CONTEXT = new SecureContext();

	private static final String SCP = "scp";

	@Override
	public int copy(ScpContext context, ScpFile source, ScpFile destination) {
		List<String> args = getScpArgs(context, source, destination);
		return execute(SCP, args);
	}

	@Override
	public int copy(ScpContext context, File source, ScpFile destination) {
		validateSource(source);
		return copy(context, new ScpFile(source), destination);
	}

	@Override
	public int copy(ScpContext context, ScpFile source, File destination) {
		validateDestination(destination);
		return copy(context, source, new ScpFile(destination));
	}

	@Override
	public int copy(ScpContext context, File source, File destination) {
		validateSource(source);
		validateDestination(destination);
		return copy(context, new ScpFile(source), new ScpFile(destination));
	}

	@Override
	public int copy(ScpFile source, ScpFile destination) {
		return copy(DEFAULT_SCP_CONTEXT, source, destination);
	}

	@Override
	public int copy(File source, ScpFile destination) {
		return copy(DEFAULT_SCP_CONTEXT, source, destination);
	}

	@Override
	public int copy(File source, File destination) {
		return copy(DEFAULT_SCP_CONTEXT, source, destination);
	}

	@Override
	public int copy(ScpFile source, File destination) {
		return copy(DEFAULT_SCP_CONTEXT, source, destination);
	}

	/**
	 * Make sure <code>file</code> exists and is readable.
	 */
	protected void validateSource(File file) {
		Assert.notNull(file);
		String path = LocationUtils.getCanonicalPath(file);
		if (!file.exists()) {
			throw new IllegalArgumentException("[" + path + "] does not exist");
		} else if (file.isDirectory()) {
			throw new IllegalArgumentException("Directory copying is not supported [" + path + "]");
		} else if (!file.canRead()) {
			throw new IllegalArgumentException("Cannot read from [" + path + "]");
		}
	}

	/**
	 * Prove we can actually create <code>file</code> using Java. This validates (among other things) that Java has sufficient permissions
	 * for creating <code>file</code>. The <code>scp</code> process being exec'd by Java usually has a tight coupling to the Java process
	 * itself as it relates to manipulating the local file system. If Java succeeds, <code>scp</code> is likely to succeed. If Java fails,
	 * <code>scp</code> is likely to fail. Aside from permissions issues, this also makes sure <code>file</code> is not an existing
	 * directory. <code>scp</code> silently overwrites existing files by default. Thus the <code>touch</code> utility should be a reasonably
	 * accurate predictor for the success or failure of <code>scp</code> due to issues with the local file system.
	 */
	protected void validateDestination(File file) {
		Assert.notNull(file);
		String path = LocationUtils.getCanonicalPath(file);
		if (file.isDirectory()) {
			throw new IllegalArgumentException("Destination file is an existing directory [" + path + "]");
		}
		logger.debug("Touching [{}]", path);
		LocationUtils.touch(file);
	}

	protected List<String> getScpArgs(ScpContext context, ScpFile source, ScpFile destination) {
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
		args.add(toString(source));
		// Add arg for destination file
		args.add(toString(destination));
		// Return the args list
		return args;
	}

	protected String toString(ScpFile file) {
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

}
