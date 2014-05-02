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
package org.kuali.common.deploy.appserver;

import java.util.List;
import java.util.Properties;

import org.kuali.common.deploy.DeployUtils;
import org.kuali.common.deploy.Deployable;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.log.LoggerLevel;
import org.kuali.common.util.secure.channel.Result;
import org.kuali.common.util.secure.channel.SecureChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TomcatApplicationServer implements ApplicationServer {

	private static final Logger logger = LoggerFactory.getLogger(TomcatApplicationServer.class);

	SecureChannel channel;
	boolean validateShutdownExitValue = false;
	String username;
	String group;
	String shutdown;
	String startup;
	// Can be either files or dirs
	List<String> pathsToDelete;
	// Must be dirs
	List<String> dirsToCreate;
	// Can be either files or dirs
	List<String> pathsToChown;
	// Files that need to be transferred
	List<Deployable> deployables;
	// Properties used to filter deployables (if filter=true)
	Properties filterProperties;
	// If true, no files are transferred from local to remote
	boolean skipFiles;
	// The sign that tomcat has started up correctly is getting an HTTP 200 from an application url
	Executable httpWait;

	@Override
	public void stop() {
		long start = System.currentTimeMillis();
		logger.info("[tomcat:stopping]");
		Result result = DeployUtils.runscript(channel, username, shutdown, false);
		if (result.getExitValue() != 0) {
			DeployUtils.logResult(result, logger, LoggerLevel.WARN);
		}
		logger.info("[tomcat:stopped] - {}", FormatUtils.getTime(System.currentTimeMillis() - start));
	}

	@Override
	public void prepare() {
		long start = System.currentTimeMillis();
		logger.info("[tomcat:preparing]");
		// Remove old stuff (jdbc drivers, logs, applications, configuration files in /home/tomcat etc)
		DeployUtils.delete(channel, pathsToDelete);
		// Re-create directories that need to be there
		DeployUtils.mkdirs(channel, dirsToCreate);
		// Copy files to the remote server
		if (!skipFiles) {
			// Copy files from local to remote
			// TODO Get this hack out of here. The AppDynamics config sets a system property holding values for setenv.sh
			Properties properties = PropertyUtils.getGlobalProperties(filterProperties);
			DeployUtils.copyFiles(channel, deployables, properties);
		}
		// Make sure everything is owned by tomcat:tomcat
		DeployUtils.chown(channel, username, group, pathsToChown);
		logger.info("[tomcat:prepared] - {}", FormatUtils.getTime(System.currentTimeMillis() - start));
	}

	@Override
	public void start() {
		long start = System.currentTimeMillis();
		logger.info("[tomcat:start]");
		DeployUtils.runscript(channel, username, startup);
		httpWait.execute();
		logger.info("[tomcat:started] - {}", FormatUtils.getTime(System.currentTimeMillis() - start));
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

	public List<String> getPathsToDelete() {
		return pathsToDelete;
	}

	public void setPathsToDelete(List<String> pathsToDelete) {
		this.pathsToDelete = pathsToDelete;
	}

	public List<String> getDirsToCreate() {
		return dirsToCreate;
	}

	public void setDirsToCreate(List<String> dirsToCreate) {
		this.dirsToCreate = dirsToCreate;
	}

	public List<String> getPathsToChown() {
		return pathsToChown;
	}

	public void setPathsToChown(List<String> pathsToChown) {
		this.pathsToChown = pathsToChown;
	}

	public List<Deployable> getDeployables() {
		return deployables;
	}

	public void setDeployables(List<Deployable> deployables) {
		this.deployables = deployables;
	}

	public boolean isSkipFiles() {
		return skipFiles;
	}

	public void setSkipFiles(boolean skipFiles) {
		this.skipFiles = skipFiles;
	}

	public Properties getFilterProperties() {
		return filterProperties;
	}

	public void setFilterProperties(Properties filterProperties) {
		this.filterProperties = filterProperties;
	}

	public Executable getHttpWait() {
		return httpWait;
	}

	public void setHttpWait(Executable httpWait) {
		this.httpWait = httpWait;
	}

}
