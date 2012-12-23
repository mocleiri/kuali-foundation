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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.UnixUtils;
import org.kuali.common.util.service.DefaultExecService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 *
 */
public class ExecScp extends DefaultExecService implements Scp {

	private static final Logger logger = LoggerFactory.getLogger(ExecScp.class);

	private static final String SCP = "scp";

	@Override
	public int copy(SecureContext context, File localFile, String remoteFile) {
		Assert.notNull(localFile);
		String localFilePath = LocationUtils.getCanonicalPath(localFile);
		if (!localFile.exists()) {
			throw new IllegalArgumentException(localFilePath + " does not exist");
		}
		String destination = UnixUtils.getLocation(context.getUsername(), context.getHostname(), remoteFile);
		return copy(context, localFilePath, destination);
	}

	@Override
	public int copy(SecureContext context, String remoteFile, File localFile) {
		Assert.notNull(localFile);
		try {
			logger.debug("Touching {}", localFile);
			FileUtils.touch(localFile);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		}
		String source = UnixUtils.getLocation(context.getUsername(), context.getHostname(), remoteFile);
		String destination = LocationUtils.getCanonicalPath(localFile);
		return copy(context, source, destination);
	}

	@Override
	public int copy(SecureContext context, String source, String destination) {
		List<String> args = getScpArgs(context, source, destination);
		return execute(SCP, args);
	}

	protected List<String> getScpArgs(SecureContext context, String source, String destination) {
		List<String> args = new ArrayList<String>();
		// Add any extra arguments they may have provided
		CollectionUtils.nullSafeAdd(args, context.getArgs());
		// Add arg for custom ssh_config file (if any)
		addConfigFile(context.getConfigFile(), SSHUtils.DEFAULT_CONFIG_FILE, args);
		// Add arg for custom private key (if any)
		addIdentityFile(context.getPrivateKey(), args);
		// Add arg for port if they are not using 22
		addPort(context, SSHUtils.DEFAULT_PORT, args);
		// Add arg for source file
		args.add(source);
		// Add arg for destination file
		args.add(destination);
		// Return the args list
		return args;
	}

	protected void addPort(SecureContext context, int defaultPort, List<String> args) {
		if (context.getPort() != defaultPort) {
			// Capital P because the scp protocol uses -p internally
			args.add("-P");
			args.add(Integer.toString(context.getPort()));
		}
	}

	@Override
	public int copy(File localFile, String user, String hostname, String remoteFile) {
		SecureContext context = new SecureContext(user, hostname);
		return copy(context, localFile, remoteFile);
	}

	@Override
	public int copy(String user, String hostname, String remoteFile, File localFile) {
		SecureContext context = new SecureContext(user, hostname);
		return copy(context, remoteFile, localFile);
	}

	@Override
	public int copy(String source, String destination) {
		SecureContext context = new SecureContext();
		return copy(context, source, destination);
	}

	@Override
	public int copy(File localFile, String destination) {
		SecureContext context = new SecureContext();
		return copy(context, localFile, destination);
	}

	@Override
	public int copy(String source, File localFile) {
		SecureContext context = new SecureContext();
		return copy(context, source, localFile);
	}

}
