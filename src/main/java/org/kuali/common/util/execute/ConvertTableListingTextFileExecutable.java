/**
 * Copyright 2010-2013 The Kuali Foundation
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
package org.kuali.common.util.execute;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.kuali.common.util.LocationUtils;
import org.springframework.util.Assert;

public class ConvertTableListingTextFileExecutable implements Executable {

	private static String SQL = "sql";
	private static String METAINF = "META-INF";

	List<String> vendors = Arrays.asList("mysql", "oracle");
	String suffix = SQL;
	String prefix = SQL;
	String artifactId;
	File outputDir;
	boolean skip;

	@Override
	public void execute() {
		Assert.notNull(artifactId, "artifactId is null");
		Assert.notNull(vendors, "vendors is null");
		Assert.notNull(suffix, "suffix is null");
		Assert.notNull(prefix, "prefix is null");
		Assert.notNull(outputDir, "outputDir is null");

		for (String vendor : vendors) {
			String tableListing = SQL + "/" + vendor + "/" + artifactId + "-tables.txt";
			List<String> tableNames = LocationUtils.readLines(tableListing);
			List<String> resources = getResources(tableNames, vendor, prefix, suffix);
			validateResources(resources);
			File outputFile = getOutputFile(outputDir, prefix, vendor, artifactId);
			writeLines(outputFile, resources);
		}
	}

	protected void validateResources(List<String> resources) {
		for (String resource : resources) {
			Assert.isTrue(LocationUtils.exists(resource), "[" + resource + "] does not exist");
		}
	}

	protected void writeLines(File file, List<String> lines) {
		try {
			FileUtils.writeLines(file, lines, "\n");
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	protected File getOutputFile(File outputDir, String prefix, String vendor, String artifactId) {
		String filename = METAINF + "/" + prefix + "/" + vendor + "/" + artifactId + "-data.resources";
		return new File(outputDir, filename);

	}

	protected List<String> getResources(List<String> tableNames, String vendor, String prefix, String suffix) {
		List<String> resources = new ArrayList<String>();
		for (String tableName : tableNames) {
			String resource = "classpath:" + prefix + "/" + vendor + "/" + tableName + "." + suffix;
			resources.add(resource);
		}
		return resources;
	}

	public List<String> getVendors() {
		return vendors;
	}

	public void setVendors(List<String> vendors) {
		this.vendors = vendors;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	public File getOutputDir() {
		return outputDir;
	}

	public void setOutputDir(File outputDir) {
		this.outputDir = outputDir;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

}
