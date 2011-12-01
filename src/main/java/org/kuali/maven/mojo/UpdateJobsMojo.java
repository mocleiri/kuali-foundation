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

import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;
import org.kuali.maven.common.PropertiesUtils;
import org.kuali.maven.mojo.context.MojoContext;

/**
 * Connect to a Jenkins server and update one or more existing jobs configuration
 * 
 * @goal updatejobs
 * @requiresDependencyResolution test
 */
public class UpdateJobsMojo extends AbstractJobConfigMojo {

	/**
	 * The command issued to Jenkins CLI
	 * 
	 * @parameter expression="${jenkins.cmd}" default-value="update-job"
	 * @required
	 */
	private String cmd;

	/**
	 * Comma delimited list of types of jobs to update
	 * 
	 * @parameter expression="${jenkins.types}" default-value="publish,unit,license,release"
	 * @required
	 */
	private String types;

	@Override
	public void execute() throws MojoExecutionException {
		setStopOnError(false);
		String[] tokens = PropertiesUtils.splitAndTrim(types, ",");
		List<MojoContext> contexts = helper.pushJobsToJenkins(this, tokens);
		helper.handleResults(contexts);
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

}