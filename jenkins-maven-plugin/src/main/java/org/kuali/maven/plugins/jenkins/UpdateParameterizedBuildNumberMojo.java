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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

/**
 * Update the default value of the parameterized build number for the indicated jobs so it matches the build number of the currently executing job. Note that this updates the
 * config.xml on the local file system and will have no affect until Jenkins is restarted or configuration is reloaded from disk.
 * 
 * @goal updateparameterizedbuildnumber
 */
public class UpdateParameterizedBuildNumberMojo extends AbstractMojo {

	/**
	 * The name of the build parameter that holds the build number
	 * 
	 * @parameter expression="${jenkins.parameterName}" default-value="NUMBER"
	 * @required
	 */
	private String parameterName;

	/**
	 * The name of the file where jenkins stores job configuration info
	 * 
	 * @parameter expression="${jenkins.jobConfigFile}" default-value="config.xml"
	 * @required
	 */
	private String jobConfigFile;

	/**
	 * Comma separated list of job names
	 * 
	 * @parameter expression="${jenkins.jobs}"
	 * @required
	 */
	private String jobs;

	/**
	 * Directory where jobs live
	 * 
	 * @parameter expression="${jenkins.jobsDir}" default-value="/var/lib/jenkins/jobs"
	 * @required
	 */
	private File jobsDir;

	@Override
	public void execute() throws MojoExecutionException {
		try {
			String[] tokens = jobs.split(",");
			List<File> configFiles = getConfigFiles(jobsDir, tokens, jobConfigFile);
			getLog().info("Located " + configFiles.size() + " job config files");
			int buildNumber = getBuildNumber();
			updateContent(configFiles, buildNumber, parameterName);
		} catch (Exception e) {
			throw new MojoExecutionException("Unexpected error", e);
		}
	}

	protected int getBuildNumber() {
		String s = System.getenv("BUILD_NUMBER");
		if (StringUtils.isBlank(s)) {
			throw new IllegalArgumentException("env.BUILD_NUMBER cannot be blank");
		}
		return new Integer(s);
	}

	protected void updateContent(List<File> configFiles, int buildNumber, String parameterName) throws IOException {
		for (File configFile : configFiles) {
			String oldContent = FileUtils.readFileToString(configFile);
			String newContent = getReplacementContent(oldContent, buildNumber, parameterName);
			getLog().info("Updating " + configFile.getAbsolutePath());
			File bak = new File(configFile.getAbsolutePath() + ".bak");
			FileUtils.copyFile(configFile, bak);
			FileUtils.writeStringToFile(configFile, newContent);
		}
	}

	protected String getXmlFragment(String xml, String tag) {
		return StringUtils.substringBetween(xml, "<" + tag + ">", "</" + tag + ">");
	}

	protected String getReplacementContent(String s, int buildNumber, String parameterName) {
		String parameterDefinition = getXmlFragment(s, "hudson.model.StringParameterDefinition");
		String name = getXmlFragment(parameterDefinition, "name");
		if (!parameterName.equals(name)) {
			throw new IllegalStateException("Parameter name must equal " + parameterName + " but was '" + name + "' instead");
		}
		String defaultValue = getXmlFragment(parameterDefinition, "defaultValue");
		String oldToken = "<defaultValue>" + defaultValue + "</defaultValue>";
		String newToken = "<defaultValue>" + buildNumber + "</defaultValue>";
		int occurs = StringUtils.countMatches(s, oldToken);
		if (occurs != 1) {
			throw new IllegalArgumentException(oldToken + " must occur exactly once, but it actually occurs " + occurs);
		}
		return StringUtils.replace(s, oldToken, newToken);
	}

	protected List<File> getConfigFiles(File directory, String[] jobs, String configFile) {
		List<File> files = new ArrayList<File>();
		for (String job : jobs) {
			String filename = directory.getAbsolutePath() + File.separator + job + File.separator + configFile;
			File file = new File(filename);
			if (file.exists()) {
				files.add(file);
			} else {
				getLog().warn(filename + " does not exist");
			}
		}
		return files;
	}

	public String getJobConfigFile() {
		return jobConfigFile;
	}

	public void setJobConfigFile(String jobConfigFile) {
		this.jobConfigFile = jobConfigFile;
	}

	public String getJobs() {
		return jobs;
	}

	public void setJobs(String jobs) {
		this.jobs = jobs;
	}

	public File getJobsDir() {
		return jobsDir;
	}

	public void setJobsDir(File jobsDir) {
		this.jobsDir = jobsDir;
	}
}