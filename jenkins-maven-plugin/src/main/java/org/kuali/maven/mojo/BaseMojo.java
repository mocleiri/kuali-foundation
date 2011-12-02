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

import java.io.File;
import java.util.List;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.project.MavenProject;

/**
 * 
 */
public abstract class BaseMojo extends AbstractMojo {
	public static final String FS = System.getProperty("file.separator");
	JenkinsHelper helper = new JenkinsHelper();

	/**
	 * The Maven project object
	 * 
	 * @parameter expression="${project}"
	 * @readonly
	 */
	private MavenProject project;

	/**
	 * The plugin dependencies.
	 * 
	 * @parameter expression="${plugin.artifacts}"
	 * @required
	 * @readonly
	 */
	private List<Artifact> pluginArtifacts;

	/**
	 * The Jenkins instance to connect to.
	 * 
	 * @parameter expression="${jenkins.url}" default-value="${project.ciManagement.url}"
	 * @required
	 */
	private String url;

	/**
	 * The format for timestamp displays
	 * 
	 * @parameter expression="${jenkins.timestampFormat}" default-value="yyyy-MM-dd HH:mm:ss z"
	 * @required
	 */
	private String timestampFormat;

	/**
	 * The working directory for the plugin
	 * 
	 * @parameter expression="${jenkins.workingDir}" default-value="${project.build.directory}/jenkins"
	 * @required
	 */
	private File workingDir;

	/**
	 * If set to true, the build will fail the first time it encounters an issue. When false, mojo's that issue multiple requests, will proceed through
	 * their list of requests and then fail at the end if an issue was encountered along the way.
	 * 
	 * @parameter expression="${jenkins.stopOnError}" default-value="false"
	 * @required
	 */
	private boolean stopOnError;

	public MavenProject getProject() {
		return project;
	}

	public List<Artifact> getPluginArtifacts() {
		return pluginArtifacts;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String server) {
		this.url = server;
	}

	public String getTimestampFormat() {
		return timestampFormat;
	}

	public void setTimestampFormat(String timestampFormat) {
		this.timestampFormat = timestampFormat;
	}

	public File getWorkingDir() {
		return workingDir;
	}

	public void setWorkingDir(File workingDir) {
		this.workingDir = workingDir;
	}

	public boolean isStopOnError() {
		return stopOnError;
	}

	public void setStopOnError(boolean stopOnError) {
		this.stopOnError = stopOnError;
	}

}