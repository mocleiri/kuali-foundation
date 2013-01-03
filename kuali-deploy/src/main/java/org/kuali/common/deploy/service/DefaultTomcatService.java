/**
 * Copyright 2004-2013 The Kuali Foundation
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
package org.kuali.common.deploy.service;

import org.kuali.common.util.UnixCmds;
import org.kuali.common.util.secure.Result;
import org.kuali.common.util.secure.SecureChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultTomcatService implements TomcatService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultTomcatService.class);

	UnixCmds cmds = new UnixCmds();
	boolean validateShutdownExitValue = false;
	boolean validateStartupExitValue = true;
	SecureChannel channel;
	String username;
	String shutdown;
	String startup;

	@Override
	public void shutdown() {
		executeCommand(cmds.su(username, shutdown), validateShutdownExitValue);
	}

	@Override
	public void startup() {
		executeCommand(cmds.su(username, startup), validateStartupExitValue);
	}

	protected void executeCommand(String command, boolean validateResult) {
		logger.info("[{}]", command);
		Result result = channel.executeCommand(command);
		ServiceUtils.logResult(result);
		if (validateResult) {
			ServiceUtils.validateResult(result);
		}
	}
}
