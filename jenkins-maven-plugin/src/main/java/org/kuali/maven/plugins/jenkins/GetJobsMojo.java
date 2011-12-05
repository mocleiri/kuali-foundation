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
package org.kuali.maven.mojo.jenkins;

import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;
import org.kuali.maven.common.PropertiesUtils;
import org.kuali.maven.mojo.jenkins.context.MojoContext;

/**
 * Connect to a Jenkins server and retrieve XML documents describing one or more job configurations
 * 
 * @goal getjobs
 * @requiresDependencyResolution test
 */
public class GetJobsMojo extends AbstractCliMojo {

	/**
	 * The command issued to Jenkins CLI
	 * 
	 * @parameter expression="${jenkins.cmd}" default-value="get-job"
	 * @required
	 */
	private String cmd;

	/**
	 * The types of jobs to retrieve. Maven GAV info is combined with 'type' to derive the complete job name eg 'jenkins-maven-plugin-1.0-publish'
	 * 
	 * @parameter expression="${jenkins.types}" default-value="publish,unit,license,release"
	 * @required
	 */
	private String types;

	/**
	 * The explicit list of jobs to retrieve. If names are provided, 'types' is ignored.
	 * 
	 * @parameter
	 */
	private List<String> names;

	@Override
	public void execute() throws MojoExecutionException {
		String[] tokens = PropertiesUtils.splitAndTrim(types, ",");
		getLog().info("");
		getLog().info("Working Dir - " + getWorkingDir().getAbsolutePath());
		getLog().info("");
		List<MojoContext> contexts = helper.getJobs(this, names, tokens);
		helper.handleResults(contexts);
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

}