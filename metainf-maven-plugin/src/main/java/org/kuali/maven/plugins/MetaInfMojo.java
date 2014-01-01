/**
 * Copyright 2004-2014 The Kuali Foundation
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
import java.io.IOException;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.MavenUtils;
import org.kuali.common.util.MetaInfContext;
import org.kuali.common.util.MetaInfUtils;

/**
 * Create a file in META-INF that lists resources bundled into a jar file
 *
 * @author Jeff Caddel
 *
 * @goal metainf
 * @phase prepare-package
 */
public class MetaInfMojo extends AbstractMojo implements MetaInfContext {

	/**
	 * Maven project
	 *
	 * @parameter default-value="${project}"
	 * @required
	 * @readonly
	 */
	private MavenProject project;

	/**
	 * Comma separated list of regular expression patterns for files to include
	 *
	 * @parameter expression="${metainf.include}" default-value="\*\*\/*"
	 * @required
	 */
	private String include;

	/**
	 * Comma separated list of regular expression patterns for files to exclude
	 *
	 * @parameter expression="${metainf.exclude}" default-value="\*\*\/META-INF/*"
	 */
	private String exclude;

	/**
	 * The directory to scan using the include/exclude patterns. Paths in <code>outputFile</code> are generated relative to this directory
	 *
	 * @parameter expression="${metainf.basedir}" default-value="${project.build.outputDirectory}"
	 * @required
	 */
	private File baseDir;

	/**
	 * The file which will contain <code>classpath:</code> references to the files that were located
	 *
	 * @parameter expression="${metainf.outputFile}"
	 *            default-value="${project.build.outputDirectory}/META-INF/${project.artifactId}.resources"
	 * @required
	 */
	private File outputFile;

	/**
	 * The prefix to insert before the relative path name
	 *
	 * @parameter expression="${metainf.prefix}" default-value="classpath:"
	 */
	private String prefix;

	/**
	 * If true, the location list is sorted.
	 *
	 * @parameter expression="${metainf.sort}" default-value="true"
	 */
	private boolean sort;

	/**
	 * By default, execution of this mojo is automatically skipped for Maven projects with a packaging of type <code>pom</code>. If
	 * <code>forceMojoExecution</code> is <code>true</code> this mojo will always execute. <code>forceMojoExecution</code> overrides
	 * <code>skip</code>.
	 *
	 * @parameter expression="${metainf.forceMojoExecution}" default-value="false"
	 */
	private boolean forceMojoExecution;

	/**
	 * By default, execution of this mojo is automatically skipped for Maven projects with a packaging of type <code>pom</code>. Set this
	 * parameter to <code>true</code> to explicitly skip executing this mojo in other scenarios. NOTE: <code>forceMojoExecution</code>
	 * overrides <code>skip</code>.
	 *
	 * @parameter expression="${metainf.skip}" default-value="false"
	 */
	private boolean skip;

	@Override
	public void execute() throws MojoExecutionException {
		try {
			// Might be skipping execution altogether
			if (MavenUtils.skip(forceMojoExecution, skip, project.getPackaging())) {
				return;
			}

			// Scan the directory and create a .resources file containing the list of locations we find
			MetaInfUtils.scanAndCreateFile(this);
		} catch (IOException e) {
			throw new MojoExecutionException("Unexpected error", e);
		}
	}

	@Override
	public List<String> getIncludes() {
		return CollectionUtils.getTrimmedListFromCSV(include);
	}

	@Override
	public List<String> getExcludes() {
		return CollectionUtils.getTrimmedListFromCSV(exclude);
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

	@Override
	public File getBaseDir() {
		return baseDir;
	}

	public void setBaseDir(File baseDir) {
		this.baseDir = baseDir;
	}

	@Override
	public File getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(File outputFile) {
		this.outputFile = outputFile;
	}

	@Override
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	@Override
	public boolean isSort() {
		return sort;
	}

	public void setSort(boolean sort) {
		this.sort = sort;
	}

	public boolean isForceMojoExecution() {
		return forceMojoExecution;
	}

	public void setForceMojoExecution(boolean forceMojoExecution) {
		this.forceMojoExecution = forceMojoExecution;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

}
