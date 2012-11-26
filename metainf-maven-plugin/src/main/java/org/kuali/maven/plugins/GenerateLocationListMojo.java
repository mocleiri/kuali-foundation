/**
 * Copyright 2004-2012 The Kuali Foundation
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
package org.kuali.maven.plugins;

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

/**
 * @author Jeff Caddel
 * @goal generatelocationlist
 */
public class GenerateLocationListMojo extends AbstractMojo {

	/**
	 * @parameter expression="${metainf.include}"
	 */
	private String include;

	/**
	 * @parameter expression="${metainf.exclude}" default-value="&#42;&#42;/META-INF/&#42;"
	 */
	private String exclude;

	/**
	 * @parameter expression="${metainf.basedir}" default-value="${project.build.outputDirectory}"
	 */
	private File baseDir;

	/**
	 * @parameter expression="${metainf.outputFile}" default-value="${project.build.outputDirectory}/META-INF/location.resources"
	 */
	private File outputFile;

	/**
	 * @parameter expression="${metainf.prefix}" default-value="classpath:"
	 */
	private String prefix;

	@Override
	public void execute() throws MojoExecutionException {
		getLog().info("Hello world");
	}

	public String getInclude() {
		return include;
	}

	public void setInclude(String include) {
		this.include = include;
	}

	public String getExclude() {
		return exclude;
	}

	public void setExclude(String exclude) {
		this.exclude = exclude;
	}

	public File getBaseDir() {
		return baseDir;
	}

	public void setBaseDir(File baseDir) {
		this.baseDir = baseDir;
	}

	public File getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(File outputFile) {
		this.outputFile = outputFile;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

}
