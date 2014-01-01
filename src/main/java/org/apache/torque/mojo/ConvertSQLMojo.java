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
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.torque.util.SimpleScanner;

/**
 * Examine SQL files and convert them to always use the delimiter <code>/</code> on it's own line. Convert any lines ending with
 * <code>;</code> to use a <code>/</code> on it's own line.
 *
 * @goal convertsql
 */
public class ConvertSQLMojo extends AbstractMojo {
	private static final String FS = File.separator;

	/**
	 * The encoding to use when reading/writing files
	 *
	 * @parameter expression="${impex.encoding}" default-value="${project.build.sourceEncoding}"
	 */
	private String encoding;

	/**
	 * The old delimiter used to terminate a SQL statement by being present at the end of a line.
	 *
	 * @parameter expression="${impex.oldDelimiter}" default-value=";"
	 * @required
	 */
	private String oldDelimiter;

	/**
	 * The new delimiter to put on its own line
	 *
	 * @parameter expression="${impex.newDelimiter}" default-value="/"
	 * @required
	 */
	private String newDelimiter;

	/**
	 * Directory to examine for .sql files
	 *
	 * @parameter expression="${impex.sourceDir}" default-value="${project.build.directory}/sql/source"
	 * @required
	 */
	private File sourceDir;

	/**
	 * Directory to generate the converted files into
	 *
	 * @parameter expression="${impex.outputDir}" default-value="${project.build.directory}/sql/output"
	 * @required
	 */
	private File outputDir;

	/**
	 * CSV list of regex patterns for files to include
	 *
	 * @parameter expression="${impex.includes}" default-value="\*\*\/*.sql"
	 * @required
	 */
	private String includes;

	/**
	 * CSV list of regex patterns for files to exclude
	 *
	 * @parameter expression="${impex.excludes}" default-value="\*\*\/.svn/**,\*\*\/.git/**"
	 */
	private String excludes;

	/**
	 * Liquibase injects a handful of metadata at the top of each SQL file that causes "noise". All of the checksums and actual SQL may stay
	 * the same, but the Liquibase metadata makes it appear as though the resulting SQL file has changed. By omitting the metadata you can
	 * run the same change set multiple times and get the exact same SQL file as output. This makes it much easier to perform validation
	 * checks on the SQL.
	 *
	 * @parameter expression="${impex.skipIrrelevantLiquibaseMetadataLines}" default-value="false"
	 */
	private boolean skipIrrelevantLiquibaseMetadataLines;

	private List<String> liquibaseTokens = getLiquibaseTokens();

	@Override
	public void execute() throws MojoExecutionException {
		try {
			// Make sure the sourceDir exists
			FileUtils.forceMkdir(sourceDir);
			getLog().info("Source Dir - " + sourceDir.getCanonicalPath());
			getLog().info("Output Dir - " + outputDir.getCanonicalPath());
			getLog().info("Includes - " + includes);
			getLog().info("Excludes - " + excludes);
			getLog().info("Old Delimiter - " + oldDelimiter);
			getLog().info("New Delimiter - " + newDelimiter);
			getLog().info("Encoding - " + encoding);
			List<File> files = getFiles();
			if (files == null || files.size() == 0) {
				getLog().info("No files found");
				return;
			}
			getLog().info("Found " + files.size() + " SQL files to convert");
			convert(files);
		} catch (Exception e) {
			throw new MojoExecutionException("Unexpected error", e);
		}
	}

	protected void convert(List<File> files) throws IOException {
		for (File file : files) {
			convert(file);
		}
	}

	protected List<String> getLiquibaseTokens() {
		List<String> tokens = new ArrayList<String>();
		tokens.add("-- Ran at:");
		tokens.add("-- Against:");
		tokens.add("-- Liquibase version:");
		tokens.add("--  Ran at:");
		tokens.add("--  Against:");
		tokens.add("--  Liquibase version:");
		return tokens;
	}

	protected boolean isSkipLine(String line) {
		if (!skipIrrelevantLiquibaseMetadataLines) {
			return false;
		}
		for (String token : liquibaseTokens) {
			if (line.startsWith(token)) {
				return true;
			}
		}
		return false;
	}

	protected String getRelativePath(File dir, File file) throws IOException {
		String dirPath = dir.getCanonicalPath() + FS;
		String filePath = file.getCanonicalPath();
		return StringUtils.remove(filePath, dirPath);
	}

	protected void convert(File file) throws IOException {
		String outputFilename = outputDir + FS + getRelativePath(sourceDir, file);
		File outputFile = new File(outputFilename);
		@SuppressWarnings("unchecked")
		List<String> lines = FileUtils.readLines(file, encoding);
		getLog().info("Writing " + outputFile.getCanonicalPath());
		OutputStream out = null;
		try {
			out = FileUtils.openOutputStream(outputFile);
			for (String line : lines) {
				if (isSkipLine(line)) {
					continue;
				}
				String convertedLine = getConvertedLine(line);
				out.write(convertedLine.getBytes(encoding));
			}
		} finally {
			IOUtils.closeQuietly(out);
		}
	}

	protected String getConvertedLine(String line) {
		String trimmed = StringUtils.trimToNull(line);
		if (StringUtils.endsWith(trimmed, oldDelimiter)) {
			int pos = line.lastIndexOf(oldDelimiter);
			return line.substring(0, pos) + IOUtils.LINE_SEPARATOR_UNIX + newDelimiter + IOUtils.LINE_SEPARATOR_UNIX;
		} else {
			return line + IOUtils.LINE_SEPARATOR_UNIX;
		}
	}

	protected List<File> getFiles() throws IOException {
		getLog().info("Examining " + sourceDir.getCanonicalPath());
		String[] includeTokens = StringUtils.split(includes, ",");
		String[] excludeTokens = StringUtils.split(excludes, ",");
		SimpleScanner scanner = new SimpleScanner(sourceDir, includeTokens, excludeTokens);
		return scanner.getFiles();
	}

	public File getSourceDir() {
		return sourceDir;
	}

	public void setSourceDir(File inputDir) {
		this.sourceDir = inputDir;
	}

	public File getOutputDir() {
		return outputDir;
	}

	public void setOutputDir(File outputDir) {
		this.outputDir = outputDir;
	}

	public String getIncludes() {
		return includes;
	}

	public void setIncludes(String includes) {
		this.includes = includes;
	}

	public String getExcludes() {
		return excludes;
	}

	public void setExcludes(String excludes) {
		this.excludes = excludes;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getOldDelimiter() {
		return oldDelimiter;
	}

	public void setOldDelimiter(String oldDelimiter) {
		this.oldDelimiter = oldDelimiter;
	}

	public String getNewDelimiter() {
		return newDelimiter;
	}

	public void setNewDelimiter(String newDelimiter) {
		this.newDelimiter = newDelimiter;
	}

	public boolean isSkipIrrelevantLiquibaseMetadataLines() {
		return skipIrrelevantLiquibaseMetadataLines;
	}

	public void setSkipIrrelevantLiquibaseMetadataLines(boolean skipIrrelevantLiquibaseMetadataLines) {
		this.skipIrrelevantLiquibaseMetadataLines = skipIrrelevantLiquibaseMetadataLines;
	}

}
