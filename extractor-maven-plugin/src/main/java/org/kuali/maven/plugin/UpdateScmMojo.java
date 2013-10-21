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
package org.kuali.maven.plugin;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
import org.kuali.maven.common.Extractor;

/**
 * Update the SCM section of a Maven pom so the URL's match the correct SCM URL. The correct SCM URL to use must be provided to the plugin as a property.
 * 
 * @goal updatescm
 */
public class UpdateScmMojo extends AbstractMojo {

	/**
	 * The Maven project this plugin runs in.
	 * 
	 * @parameter expression="${project}"
	 * @required
	 * @readonly
	 */
	private MavenProject project;

	/**
	 * The property containing the correct SCM URL. This can be a project property, system property, or environment variable.
	 * 
	 * @parameter expression="${extractor.scmUrlProperty}" default-value="scm.url"
	 * @required
	 */
	private String scmUrlProperty;

	/**
	 * The location of the Maven pom to update.
	 * 
	 * @parameter expression="${extractor.pom}" default-value="${project.basedir}/pom.xml"
	 * @required
	 */
	private File pom;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		Extractor extractor = new Extractor();
		String pomUrl = extractor.getScmUrl(project.getScm());
		String actualUrl = extractor.getActualUrl(project, scmUrlProperty);
		if (pomUrl.equals(actualUrl)) {
			getLog().info("pom url matches actual url.  Nothing to do!");
			return;
		}
		try {
			getLog().info("Updating " + pom);
			String content = FileUtils.readFileToString(pom);
			String newContent = getNewContent(project, content, pomUrl, actualUrl);
			FileUtils.writeStringToFile(pom, newContent);
		} catch (IOException e) {
			throw new MojoExecutionException("Unexpected IO exception", e);
		}
	}

	protected String getNewContent(MavenProject project, String content, String pomUrl, String newUrl) {
		String property = "project.scm.url";
		Properties properties = project.getProperties();
		String url = properties.getProperty(property);
		if (!StringUtils.isBlank(url)) {
			String open = "<" + property + ">";
			String close = "</" + property + ">";
			String oldScm = open + StringUtils.substringBetween(content, open, close) + close;
			int pos = oldScm.indexOf(pomUrl);
			if (pos == -1) {
				throw new IllegalStateException("Existing SCM information doesn't contain " + pomUrl);
			}
			String newScm = oldScm.replace(pomUrl, newUrl);
			return content.replace(oldScm, newScm);
		} else {
			content = handleConnection(content, "connection", pomUrl, newUrl);
			content = handleConnection(content, "developerConnection", pomUrl, newUrl);
			content = handleConnection(content, "url", pomUrl, newUrl);
			return content;
		}
	}

	protected String handleConnection(String content, String tag, String pomUrl, String newUrl) {
		String scm = StringUtils.substringBetween(content, "<scm>", "</scm>");
		getLog().debug("scm=" + scm);
		String open = "<" + tag + ">";
		String close = "</" + tag + ">";
		String oldScm = open + StringUtils.substringBetween(scm, open, close) + close;
		getLog().debug("oldScm=" + oldScm);
		getLog().debug("pomUrl=        " + pomUrl);
		int pos = oldScm.indexOf(pomUrl);
		if (pos == -1) {
			throw new IllegalStateException("Existing SCM information doesn't contain " + pomUrl);
		}
		String newScm = oldScm.replace(pomUrl, newUrl);
		getLog().debug("Old=[" + oldScm + "]");
		getLog().info("Update - " + newScm);
		return content.replace(oldScm, newScm);
	}

	public MavenProject getProject() {
		return project;
	}

	public String getScmUrlProperty() {
		return scmUrlProperty;
	}

	public void setScmUrlProperty(String svnUrlProperty) {
		this.scmUrlProperty = svnUrlProperty;
	}
}
