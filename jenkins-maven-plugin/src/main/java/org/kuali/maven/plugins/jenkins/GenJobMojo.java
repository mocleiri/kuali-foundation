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
package org.kuali.maven.plugins.jenkins;

import org.apache.maven.plugin.MojoExecutionException;

/**
 * Generate an XML config file for a Jenkins job to the local file system.
 * 
 * @goal genjob
 */
public class GenJobMojo extends AbstractGenerateMojo {

	/**
	 * The type of job to generate. Maven GAV info is combined with 'type' to derive the complete job name eg 'jenkins-maven-plugin-1.0-publish'
	 * 
	 * @parameter expression="${jenkins.type}" default-value="publish"
	 * @required
	 */
	private String type;

	@Override
	public void execute() throws MojoExecutionException {
		helper.generate(this, type);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
