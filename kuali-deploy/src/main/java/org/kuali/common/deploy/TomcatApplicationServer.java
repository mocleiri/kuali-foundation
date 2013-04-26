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
package org.kuali.common.deploy;

import java.util.Date;

import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.UnixCmds;
import org.kuali.common.util.secure.Result;
import org.kuali.common.util.secure.SecureChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TomcatApplicationServer implements ApplicationServer {

	private static final Logger logger = LoggerFactory.getLogger(TomcatApplicationServer.class);

	UnixCmds cmds = new UnixCmds();
	boolean validateShutdownExitValue = false;
	SecureChannel channel;
	String username;
	String shutdown;
	String startup;

	@Override
	public void stop() {
		logger.info("Shutting down Tomcat - {}", FormatUtils.getDate(new Date()));
		executeCommand(cmds.su(username, shutdown), validateShutdownExitValue);
	}

	@Override
	public void start() {
		executeCommand(cmds.su(username, startup), true);
	}

	protected void executeCommand(String command, boolean validateResult) {
		Result result = channel.executeCommand(command);
		ServiceUtils.logResult(result, logger);
		if (validateResult) {
			ServiceUtils.validateResult(result);
		}
	}

	public UnixCmds getCmds() {
		return cmds;
	}

	public void setCmds(UnixCmds cmds) {
		this.cmds = cmds;
	}

	public boolean isValidateShutdownExitValue() {
		return validateShutdownExitValue;
	}

	public void setValidateShutdownExitValue(boolean validateShutdownExitValue) {
		this.validateShutdownExitValue = validateShutdownExitValue;
	}

	public SecureChannel getChannel() {
		return channel;
	}

	public void setChannel(SecureChannel channel) {
		this.channel = channel;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getShutdown() {
		return shutdown;
	}

	public void setShutdown(String shutdown) {
		this.shutdown = shutdown;
	}

	public String getStartup() {
		return startup;
	}

	public void setStartup(String startup) {
		this.startup = startup;
	}

}
