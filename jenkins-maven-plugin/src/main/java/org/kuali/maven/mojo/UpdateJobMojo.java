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
 * Connect to a Jenkins server and update the configuration for an existing job
 * 
 * @goal updatejob
 * @requiresDependencyResolution test
 */
public class UpdateJobMojo extends AbstractJobConfigMojo {

	/**
	 * The command issued to Jenkins CLI
	 * 
	 * @parameter expression="${jenkins.cmd}" default-value="update-job"
	 * @required
	 */
	private String cmd;

	/**
	 * The type of job to update
	 * 
	 * @parameter expression="${jenkins.type}" default-value="publish"
	 * @required
	 */
	private String type;

	@Override
	public void execute() throws MojoExecutionException {
		helper.pushJobToJenkins(this, type);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

}