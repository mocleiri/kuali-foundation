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
package org.apache.torque.mojo;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.torque.util.CloverETLTable;

/**
 * @goal convertcloveretl
 */
public class ConvertCloverETLMojo extends BaseMojo {

	/**
	 * @parameter expression="${impex.sourceDirectory}" default-value="${project.basedir}/src/main/resources"
	 * @required
	 */
	File sourceDir;

	/**
	 * @parameter expression="${impex.outputDir}" default-value="${project.build.directory}/impex"
	 * @required
	 */
	File outputDir;

	/**
	 * @parameter expression="${impex.delimiter}" default-value="|"
	 * @required
	 */
	String delimiter;

	@Override
	protected void executeMojo() throws MojoExecutionException, MojoFailureException {
		getLog().info("Examining " + sourceDir.getAbsolutePath());
		File dataDir = new File(sourceDir + "/data");
		File[] files = dataDir.listFiles();
		if (files == null) {
			getLog().info("Located 0 Clover ETL files");
		} else {
			getLog().info("Located " + files.length + " Clover ETL files");
			Arrays.sort(files);
			for (File file : files) {
				convertFile(file);
			}
		}
		try {
			File newSchemaFile = new File(outputDir + "/schema.xml");
			File oldSchemaFile = new File(sourceDir + "/schema.xml");
			getLog().info("Creating " + newSchemaFile);
			FileUtils.copyFile(oldSchemaFile, newSchemaFile);
			File newDatabaseDTDFile = new File(outputDir + "/database.dtd");
			File oldDatabaseDTDFile = new File(sourceDir + "/database.dtd");
			getLog().info("Creating " + newDatabaseDTDFile);
			FileUtils.copyFile(oldDatabaseDTDFile, newDatabaseDTDFile);
		} catch (IOException e) {
			throw new MojoExecutionException("Unexpected IO error", e);
		}
	}

	protected void convertFile(File file) {
		try {
			CloverETLTable table = getTable(file);
			String xml = getXml(table);
			File outputFile = new File(outputDir + "/" + table.getName() + ".xml");
			getLog().info("Creating " + outputFile);
			FileUtils.writeStringToFile(outputFile, xml);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		}
	}

	protected CloverETLTable getTable(File file) throws IOException {
		String filename = file.getName().toUpperCase();
		int pos = filename.indexOf(".");
		String tablename = filename.substring(0, pos);

		String content = FileUtils.readFileToString(file);
		String headerLine = content.substring(0, content.indexOf("\n"));
		String[] columns = StringUtils.splitByWholeSeparatorPreserveAllTokens(headerLine, delimiter);

		for (int i = 0; i < columns.length; i++) {
			columns[i] = columns[i].toUpperCase();
		}

		List<String[]> rows = getRows(content, columns, file);

		CloverETLTable table = new CloverETLTable();
		table.setName(tablename);
		table.setColumns(Arrays.asList(columns));
		table.setRows(rows);
		return table;
	}

	@SuppressWarnings("unchecked")
	protected List<String> readLines(String s) {
		try {
			InputStream in = new ByteArrayInputStream(s.getBytes());
			return IOUtils.readLines(in);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		}

	}

	protected List<String[]> getRows(String content, String[] columns, File file) {
		List<String> lines = readLines(content);
		List<String[]> rows = new ArrayList<String[]>();
		for (int i = 1; i < lines.size(); i++) {
			String line = lines.get(i);
			while (!line.endsWith(delimiter)) {
				i = i + 1;
				line = line + "\n" + lines.get(i);
			}
			String[] row = StringUtils.splitByWholeSeparatorPreserveAllTokens(line, delimiter);
			if (row.length != columns.length) {
				throw new IllegalStateException("Column count doesn't match. [" + file.getAbsolutePath() + ",row " + i + "] columns=" + columns.length + " row=" + row.length);
			}
			for (int j = 0; j < row.length; j++) {
				row[j] = escape(row[j]);
			}
			rows.add(row);
		}
		return rows;
	}

	protected String escape(String s) {
		return s.replace("<", "&lt;").replace("\"", "&quot;").replace("\n", "&#xa;").replace("\r", "&#xd;");
	}

	protected String getXml(CloverETLTable table) {
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		sb.append("<!--  Auto-generated by the Maven Impex Plugin -->\n");
		sb.append("<!DOCTYPE dataset SYSTEM \"data.dtd\">\n");
		sb.append("<dataset>\n");
		List<String[]> rows = table.getRows();
		List<String> columns = table.getColumns();
		for (int i = 0; i < rows.size(); i++) {
			sb.append("    <" + table.getName());
			String[] row = rows.get(i);
			for (int j = 0; j < columns.size(); j++) {
				String column = columns.get(j);
				String value = row[j];
				if (!StringUtils.isBlank(value)) {
					sb.append(" " + column + "=" + '"' + value + '"');
				}
			}
			sb.append(" />\n");
		}
		sb.append("</dataset>\n");
		return sb.toString();
	}

	public File getSourceDir() {
		return sourceDir;
	}

	public void setSourceDir(File sourceDir) {
		this.sourceDir = sourceDir;
	}

	public File getOutputDir() {
		return outputDir;
	}

	public void setOutputDir(File outputDir) {
		this.outputDir = outputDir;
	}

	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}
}
