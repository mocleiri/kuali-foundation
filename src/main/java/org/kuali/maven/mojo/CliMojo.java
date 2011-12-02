/**
 * Copyright 2004-2011 The Kuali Foundation
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
package org.kuali.maven.mojo;

import org.apache.maven.plugin.MojoExecutionException;

/**
 * Connect to a Jenkins server and execute a Jenkins CLI command
 * 
 * @goal cli
 * @requiresDependencyResolution test
 */
public class CliMojo extends AbstractCliMojo {

	/**
	 * The command issued to Jenkins CLI eg "help", "version", "who-ami-i" etc
	 * 
	 * @parameter expression="${jenkins.cmd}" default-value="help"
	 * @required
	 */
	private String cmd;

	@Override
	public void execute() throws MojoExecutionException {
		helper.executeCliCommand(this);
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

}