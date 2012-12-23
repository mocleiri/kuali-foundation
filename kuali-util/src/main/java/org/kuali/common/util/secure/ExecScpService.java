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
import org.codehaus.plexus.util.cli.Commandline;
import org.kuali.common.util.LocationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class ExecScpService implements ScpService {

	private static final Logger logger = LoggerFactory.getLogger(ExecScpService.class);

	private static final String SCP = "scp";

	@Override
	public int copy(SecureContext context, File source, String destination) {
		return 0;
	}

	@Override
	public int copy(SecureContext context, String source, File destination) {
		return 0;
	}

	protected Commandline getCommandLine(SecureContext context) {
		Commandline cl = new Commandline();
		cl.setExecutable(SCP);
		return cl;
	}

	protected List<String> getArgs(SecureContext context) {
		List<String> args = new ArrayList<String>();
		addConfigFile(context, SSHUtils.DEFAULT_CONFIG_FILE, args);
		addIdentityFile(context, args);
		addPort(context, SSHUtils.DEFAULT_PORT, args);
		return args;
	}

	protected void addPort(SecureContext context, int defaultPort, List<String> args) {
		if (context.getPort() != defaultPort) {
			args.add("-P");
			args.add(Integer.toString(context.getPort()));
		}
	}

	protected void addIdentityFile(SecureContext context, List<String> args) {
		if (context.getPrivateKey() != null) {
			String path = LocationUtils.getCanonicalPath(context.getPrivateKey());
			args.add("-i");
			args.add(path);
		}
	}

	protected void addConfigFile(SecureContext context, File defaultConfigFile, List<String> args) {
		File configFile = context.getConfigFile();
		if (configFile == null) {
			return;
		}
		String defaultPath = LocationUtils.getCanonicalPath(defaultConfigFile);
		String configFilePath = LocationUtils.getCanonicalPath(configFile);
		if (!StringUtils.equals(defaultPath, configFilePath)) {
			args.add("-F");
			args.add(configFilePath);
		}
	}

}
