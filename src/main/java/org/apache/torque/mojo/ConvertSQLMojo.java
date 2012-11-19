/**
 * Copyright 2006-2012 The Kuali Foundation
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
 * Examine SQL files and convert the delimiter style from using a <code>;</code> at the end of the line to a <code>/</code> on it's own line
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
	 * Director to examine for .sql files
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
	 * Files to include
	 *
	 * @parameter expression="${impex.includes}" default-value="*.sql"
	 */
	private String includes;

	/**
	 * Files to exclude
	 *
	 * @parameter expression="${impex.excludes}"
	 */
	private String excludes;

	/**
	 * Liquibase injects a handful of metadata at the top of each .sql file that causes "noise". All of the checksums and actual .sql may be
	 * exactly the same as before, but the Liquibase metadata makes it appear as though the resulting .sql file has changed. By omitting the
	 * metadata you can run the same change set multiple times and get the exact same .sql file as output. This makes it much easier to
	 * perform validation checks on the SQL.
	 *
	 * @parameter expression="${impex.skipIrrelevantLiquibaseMetadataLines}" default-value="false"
	 */
	private boolean skipIrrelevantLiquibaseMetadataLines;

	@Override
	public void execute() throws MojoExecutionException {
		try {
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
		return tokens;
	}

	protected boolean isSkipLine(String line) {
		if (!skipIrrelevantLiquibaseMetadataLines) {
			return false;
		}
		List<String> tokens = getLiquibaseTokens();
		for (String token : tokens) {
			if (line.startsWith(token)) {
				return true;
			}
		}
		return false;
	}

	protected void convert(File file) throws IOException {
		String outputFilename = outputDir + FS + file.getName();
		File outputFile = new File(outputFilename);
		@SuppressWarnings("unchecked")
		List<String> lines = FileUtils.readLines(file, encoding);
		getLog().info("Creating " + outputFile.getCanonicalPath());
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
		String trimmed = StringUtils.trim(line);
		if (trimmed.endsWith(oldDelimiter)) {
			int pos = line.lastIndexOf(oldDelimiter);
			return line.substring(0, pos) + IOUtils.LINE_SEPARATOR_UNIX + newDelimiter + IOUtils.LINE_SEPARATOR_UNIX;
		} else {
			return line + IOUtils.LINE_SEPARATOR_UNIX;
		}
	}

	protected List<File> getFiles() throws IOException {
		FileUtils.forceMkdir(sourceDir);
		getLog().info("Examining " + sourceDir.getCanonicalPath());
		SimpleScanner scanner = new SimpleScanner(sourceDir, includes, excludes);
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
