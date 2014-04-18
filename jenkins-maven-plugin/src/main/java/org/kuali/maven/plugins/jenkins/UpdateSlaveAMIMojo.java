/**
 * Copyright 2011-2014 The Kuali Foundation
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

import java.io.File;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.kuali.maven.common.ResourceUtils;
import org.springframework.util.PropertyPlaceholderHelper;

/**
 * Update the slave AMI being used by the Jenkins EC2 plugin
 * 
 * @goal updateslaveami
 */
public class UpdateSlaveAMIMojo extends AbstractMojo {
	ResourceUtils utils = new ResourceUtils();

	/**
	 * The Maven project object
	 * 
	 * @parameter expression="${project}"
	 * @readonly
	 */
	private MavenProject project;

	/**
	 * The location of the Jenkins config file
	 * 
	 * @parameter expression="${jenkins.configFile}" default-value="/var/lib/jenkins/config.xml"
	 * @required
	 */
	private File configFile;

	/**
	 * The AMI the Jenkins EC2 plugin should use when launching slaves
	 * 
	 * @parameter expression="${jenkins.newAmi}"
	 * @required
	 */
	private String newAmi;

	@Override
	public void execute() throws MojoExecutionException {
		// The only requirement here is that a .properties file containing the property "JENKINS_NEW_AMI" gets created
		// That properties file serves as input to another jenkins job that uses it to update the configuration of the
		// master CI server
		OutputStream out = null;
		try {
			PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper("${", "}", ":", false);
			Properties duplicate = new Properties();
			duplicate.putAll(project.getProperties());
			duplicate.putAll(System.getenv());
			duplicate.putAll(System.getProperties());
			String ami = duplicate.getProperty("jenkins.newAmi");
			if (StringUtils.isBlank(ami)) {
				throw new IllegalStateException("jenkins.newAmi was not set");
			}
			String resolvedAmi = helper.replacePlaceholders(ami, duplicate);
			Properties p = new Properties();
			p.setProperty("JENKINS_NEW_AMI", resolvedAmi);
			String filename = project.getBuild().getDirectory() + "/jenkins/ami.properties";
			getLog().info("Creating [" + filename + "]");
			out = FileUtils.openOutputStream(new File(filename));
			p.store(out, "Project Properties");
		} catch (Exception e) {
			throw new MojoExecutionException("Unexpected error", e);
		} finally {
			IOUtils.closeQuietly(out);
		}
	}

	public File getConfigFile() {
		return configFile;
	}

	public void setConfigFile(File configFile) {
		this.configFile = configFile;
	}

	public String getNewAmi() {
		return newAmi;
	}

	public void setNewAmi(String newAmi) {
		this.newAmi = newAmi;
	}

}