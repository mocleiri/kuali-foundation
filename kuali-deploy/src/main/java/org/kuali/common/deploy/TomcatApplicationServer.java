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
import java.util.List;

import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.UnixCmds;
import org.kuali.common.util.secure.Result;
import org.kuali.common.util.secure.SecureChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TomcatApplicationServer implements ApplicationServer {

	private static final Logger logger = LoggerFactory.getLogger(TomcatApplicationServer.class);

	SecureChannel channel;
	UnixCmds cmds = new UnixCmds();
	boolean validateShutdownExitValue = false;
	String username;
	String group;
	String shutdown;
	String startup;
	List<String> deleteDirs;
	List<String> createDirs;
	List<String> chownDirs;
	// Tomcat automatically invokes - /usr/local/tomcat/bin/setenv.sh at startup
	Deployable setenv;
	List<Deployable> jsps;

	@Override
	public void stop() {
		logger.info("Shutting down Tomcat - {}", FormatUtils.getDate(new Date()));
		executeCommand(cmds.su(username, shutdown), validateShutdownExitValue);
	}

	@Override
	public void prepare() {
		//
	}

	@Override
	public void start() {
		executeCommand(cmds.su(username, startup), true);
	}

	protected void executeCommand(String command, boolean validateResult) {
		Result result = channel.executeCommand(command);
		DeployUtils.logResult(result, logger);
		if (validateResult) {
			DeployUtils.validateResult(result);
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

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public List<String> getDeleteDirs() {
		return deleteDirs;
	}

	public void setDeleteDirs(List<String> deleteDirs) {
		this.deleteDirs = deleteDirs;
	}

	public List<String> getCreateDirs() {
		return createDirs;
	}

	public void setCreateDirs(List<String> createDirs) {
		this.createDirs = createDirs;
	}

	public List<String> getChownDirs() {
		return chownDirs;
	}

	public void setChownDirs(List<String> chownDirs) {
		this.chownDirs = chownDirs;
	}

	public Deployable getSetenv() {
		return setenv;
	}

	public void setSetenv(Deployable setenv) {
		this.setenv = setenv;
	}

	public List<Deployable> getJsps() {
		return jsps;
	}

	public void setJsps(List<Deployable> jsps) {
		this.jsps = jsps;
	}

}
