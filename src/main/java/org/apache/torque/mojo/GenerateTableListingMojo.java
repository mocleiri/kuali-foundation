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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * Examine the directory that SQL files were generated to and produce a file that contains a listing of any tables that contained data at
 * the point in time the export was run.
 *
 * @goal generatetablelisting
 */
public class GenerateTableListingMojo extends BaseMojo {
	private static final String FS = File.separator;

	/**
	 * The extension for SQL files
	 *
	 * @parameter expression="${impex.extension}" default-value="sql"
	 * @required
	 */
	private String extension;

	/**
	 * The database vendor being targeted
	 *
	 * @parameter expression="${impex.targetDatabase}"
	 * @required
	 */
	private String targetDatabase;

	/**
	 * The directory where SQL is being generated - <code>targetDatabase</code> is automatically appended to this value
	 *
	 * @parameter expression="${impex.baseSqlDir}" default-value="${project.build.directory}/impex/sql"
	 * @required
	 */
	private File baseSqlDir;

	/**
	 * The artifactId (aka "schema") for this set of impex data
	 *
	 * @parameter expression="${impex.artifactId}" default-value="${project.artifactId}"
	 * @required
	 */
	private String artifactId;

	/**
	 * The suffix to append to the end of the file stored in META-INF
	 *
	 * @parameter expression="${impex.metaFileSuffix}" default-value="tables"
	 * @required
	 */
	private String metaFileSuffix;

	@Override
	protected void executeMojo() throws MojoExecutionException, MojoFailureException {
		File databaseSQLDir = new File(baseSqlDir, targetDatabase);
		if (!databaseSQLDir.exists()) {
			throw new MojoExecutionException(databaseSQLDir + " does not exist");
		}
		try {
			getLog().info("Examining " + databaseSQLDir.getCanonicalPath());
			List<File> files = getFileListing(databaseSQLDir);
			getLog().info("Located " + files.size() + " " + extension + " files");
			List<String> tableNames = getTableNames(files);
			List<String> locations = getLocations(tableNames);
			getLog().info("Located " + tableNames.size() + " tables");
			String outputFilename = databaseSQLDir.getAbsolutePath() + FS + ".." + FS + ".." + FS + "META-INF" + FS + targetDatabase + FS + artifactId + "." + metaFileSuffix;
			File outputFile = new File(outputFilename);
			getLog().info("Generating " + outputFile.getCanonicalPath());
			FileUtils.writeLines(outputFile, locations);
		} catch (IOException e) {
			throw new MojoExecutionException("Unexpected IO error", e);
		}
	}

	protected List<String> getLocations(List<String> tableNames) {
		List<String> locations = new ArrayList<String>();
		for (String tableName : tableNames) {
			locations.add("classpath:sql/" + targetDatabase + "/" + tableName + ".sql");
		}
		return locations;
	}

	@SuppressWarnings("unchecked")
	protected List<File> getFileListing(File dir) {
		String[] extensions = new String[] { extension };
		Collection<File> files = FileUtils.listFiles(dir, extensions, false);
		List<File> fileList = new ArrayList<File>();
		if (files != null) {
			fileList.addAll(files);
		}
		return fileList;
	}

	protected List<String> getTableNames(List<File> files) {
		String[] skipTokens = new String[] { artifactId + "." + extension, artifactId + "-constraints." + extension };
		List<String> tableNames = new ArrayList<String>();
		for (File file : files) {
			if (isSkip(file, skipTokens)) {
				continue;
			}
			String fragment = file.getName();
			int beginIndex = 0;
			int endIndex = fragment.length() - (extension.length() + 1);
			String fragmentMinusExtension = fragment.substring(beginIndex, endIndex);
			tableNames.add(fragmentMinusExtension);
		}
		Collections.sort(tableNames);
		return tableNames;
	}

	protected boolean isSkip(File file, String[] skipTokens) {
		String path = file.getAbsolutePath();
		for (String skipToken : skipTokens) {
			if (path.endsWith(skipToken)) {
				return true;
			}
		}
		return false;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getTargetDatabase() {
		return targetDatabase;
	}

	public void setTargetDatabase(String targetDatabase) {
		this.targetDatabase = targetDatabase;
	}

	public String getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	public File getBaseSqlDir() {
		return baseSqlDir;
	}

	public void setBaseSqlDir(File sqlDir) {
		this.baseSqlDir = sqlDir;
	}

	public String getMetaFileSuffix() {
		return metaFileSuffix;
	}

	public void setMetaFileSuffix(String metaFileSuffix) {
		this.metaFileSuffix = metaFileSuffix;
	}

}
