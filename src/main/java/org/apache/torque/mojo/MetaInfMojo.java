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
package org.apache.torque.mojo;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.torque.util.SimpleScanner;
import org.codehaus.plexus.util.StringUtils;

/**
 * Create a location listing file that describes resources inside of a jar file
 *
 * @author Jeff Caddel
 * @goal metainf
 */
public class MetaInfMojo extends AbstractMojo {

	/**
	 * Regular expression pattern for files to include
	 *
	 * @parameter expression="${metainf.include}" default-value="\*\*\/*"
	 * @required
	 */
	private String include;

	/**
	 * Regular expression pattern for files to exclude
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
	 * The file which will contain <code>classpath</code> references to the files that were located
	 *
	 * @parameter expression="${metainf.outputFile}" default-value="${project.build.outputDirectory}/META-INF/classpath.resources"
	 * @required
	 */
	private File outputFile;

	/**
	 * The prefix to insert before the relative path name
	 *
	 * @parameter expression="${metainf.prefix}" default-value="classpath:"
	 */
	private String prefix;

	@Override
	public void execute() throws MojoExecutionException {
		try {
			getLog().info("Examining - " + baseDir.getCanonicalPath());
			getLog().info("Include - " + include);
			getLog().info("Exclude - " + exclude);
			SimpleScanner scanner = new SimpleScanner(baseDir, include, exclude);
			List<File> files = scanner.getFiles();
			getLog().info("Located " + files.size() + " files");
			String content = getLocations(baseDir, files, prefix);
			getLog().info("Creating " + outputFile.getCanonicalPath());
			FileUtils.writeStringToFile(outputFile, content);
		} catch (Exception e) {
			throw new MojoExecutionException("Unexpected error", e);
		}
	}

	protected String getLocations(File baseDir, List<File> files, String prefix) throws IOException {
		Collections.sort(files);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < files.size(); i++) {
			if (i != 0) {
				sb.append("\n");
			}
			sb.append(getLocation(baseDir, files.get(i), prefix));
		}
		return sb.toString();
	}

	protected String getLocation(File baseDir, File file, String prefix) throws IOException {
		String dir = baseDir.getCanonicalPath();
		String path = file.getCanonicalPath();
		int pos = dir.length() + 1;
		return prefix + StringUtils.substring(path, pos);
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
