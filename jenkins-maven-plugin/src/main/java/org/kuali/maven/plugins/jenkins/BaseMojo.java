/**
 * Copyright 2011-2012 The Kuali Foundation
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
import java.util.List;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.project.MavenProject;
import org.kuali.maven.plugins.jenkins.helper.JenkinsHelper;

/**
 *
 */
public abstract class BaseMojo extends AbstractMojo {
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
	 * If set to true, the build will fail the first time Jenkins CLI encounters an issue. When false, mojo's that issue multiple CLI
	 * requests, will proceed through their list of requests and then fail at the end if an issue was encountered along the way.
	 *
	 * @parameter expression="${jenkins.stopOnError}" default-value="false"
	 * @required
	 */
	private boolean stopOnError;

	/**
	 * If true the version of the Maven project is dropped from the job name
	 *
	 * @parameter expression="${jenkins.excludeVersion}" default-value="false"
	 * @required
	 */
	private boolean excludeVersion;

	/**
	 * If set to true, the Maven build will fail if Jenkins CLI returns a non-zero exit value, otherwise the Maven build will continue
	 *
	 * @parameter expression="${jenkins.failOnError}" default-value="true"
	 * @required
	 */
	private boolean failOnError;

	/**
	 * Optional <code>List</code> of custom <code>Style</code> xml templates. This can be supplied to the pom configuration as
	 *
	 * <pre>
	 * &lt;<styles>&gt;
	 *   &lt;<style>&gt;
	 *     &lt;<name>&gt;my-custom-style&lt;</name>&gt;
	 *     &lt;<template>&gt;classpath:org/foo/bar/my-custom-style.xml&lt;</template>&gt;
	 *   &lt;</style>&gt;
	 * &lt;</styles>&gt;
	 * </pre>
	 *
	 * @parameter expression="${jenkins.styles}"
	 */
	private List<Style> styles;

	/**
	 * The style of Jenkins job. Default styles are <code>maven</code> and <code>freestyle</code>. This parameter must match one of the
	 * default styles or one of the optional custom styles supplied to the plugin configuration.
	 *
	 * @parameter expression="${jenkins.style}" default-value="freestyle"
	 * @required
	 */
	private String style;

	/**
	 * Comma separated list of integers that represent a Jenkins CLI command completing successfully
	 *
	 * @parameter expression="${jenkins.successCodes}" default-value="0"
	 * @required
	 */
	private String successCodes;

	/**
	 * Comma separated list of known job types. When specifying a job name these can be used as shorthand for the fully qualified job name.
	 * eg "publish" gets expanded to "jenkins-maven-plugin-1.1-publish". To turn off expansion set 'jobTypes' to the empty string, null, or
	 * 'NONE'
	 *
	 * @parameter expression="${jenkins.jobTypes}" default-value="publish,unit,license,release"
	 * @required
	 */
	private String jobTypes;

	/**
	 * This is the type of SCM for the project eg "svn" or "git". If not provided, this value is filled in automatically based on
	 * information in the POM.
	 *
	 * @parameter expression="${jenkins.scmType}"
	 */
	private String scmType;

	/**
	 * This is the SCM url for the project eg "http://svn.kuali.org/repos/foundation/trunk/jenkins-maven-plugin". If not provided, this
	 * value is filled in automatically based on information in the POM.
	 *
	 * @parameter expression="${jenkins.scmUrl}"
	 */
	private String scmUrl;

	/**
	 * This is the "major version" for the project. For a project with the version "1.1.0" the major version is "1.1". If not provided, this
	 * value is filled in automatically based on information from the POM.
	 *
	 * @parameter expression="${jenkins.majorVersion}"
	 */
	private String majorVersion;

	protected abstract void executeMojo();

	@Override
	public void execute() {
		helper.updateMojo(this);
		executeMojo();
	}

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

	public boolean isFailOnError() {
		return failOnError;
	}

	public void setFailOnError(boolean failOnError) {
		this.failOnError = failOnError;
	}

	public String getSuccessCodes() {
		return successCodes;
	}

	public void setSuccessCodes(String successCodes) {
		this.successCodes = successCodes;
	}

	public String getJobTypes() {
		return jobTypes;
	}

	public void setJobTypes(String jobTypes) {
		this.jobTypes = jobTypes;
	}

	public String getScmType() {
		return scmType;
	}

	public void setScmType(String scmType) {
		this.scmType = scmType;
	}

	public String getScmUrl() {
		return scmUrl;
	}

	public void setScmUrl(String scmUrl) {
		this.scmUrl = scmUrl;
	}

	public String getMajorVersion() {
		return majorVersion;
	}

	public void setMajorVersion(String majorVersion) {
		this.majorVersion = majorVersion;
	}

	public List<Style> getStyles() {
		return styles;
	}

	public void setStyles(List<Style> styles) {
		this.styles = styles;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public boolean isExcludeVersion() {
		return excludeVersion;
	}

	public void setExcludeVersion(boolean includeVersion) {
		this.excludeVersion = includeVersion;
	}

}