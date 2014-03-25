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
import java.util.List;
import java.util.Properties;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.ComparisonResults;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.SimpleScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * This executable compares the files found by the given pattern in two different locations and populates a properties
 * object with information about the comparison
 * 
 * @author andrewlubbers
 */
public class FileListComparisonExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(FileListComparisonExecutable.class);

	private String newFilesBaseDir;
	private String filePattern;
	private String originalFilesBaseDir;
	private List<String> propertyNames;
	private Properties mavenProperties;

	@Override
	public void execute() {
		Assert.notNull(mavenProperties);
		Assert.notNull(propertyNames);
		Assert.notNull(newFilesBaseDir);
		Assert.notNull(originalFilesBaseDir);
		Assert.notNull(filePattern);

		logger.info("Starting File List Comparison");
		logger.info("Original files dir: " + originalFilesBaseDir);
		logger.info("New files dir: " + LocationUtils.getCanonicalPath(new File(newFilesBaseDir)));
		logger.info("File pattern: " + filePattern);
		logger.info("Property names: " + propertyNames);

		List<String> filePatterns = CollectionUtils.getTrimmedListFromCSV(filePattern);
		String[] includesArray = filePatterns.toArray(new String[filePatterns.size()]);

		SimpleScanner scanner = new SimpleScanner();
		scanner.setBasedir(newFilesBaseDir);
		scanner.setIncludes(includesArray);

		List<String> newLocations = LocationUtils.getAbsolutePaths(scanner.getFiles());
		List<String> newFileNames = LocationUtils.getFilenames(newLocations);

		scanner = new SimpleScanner();
		scanner.setBasedir(originalFilesBaseDir);
		scanner.setIncludes(includesArray);

		List<String> originalLocations = LocationUtils.getAbsolutePaths(scanner.getFiles());
		List<String> originalFileNames = LocationUtils.getFilenames(originalLocations);

		ComparisonResults comparison = LocationUtils.getLocationListComparison(newFileNames, originalFileNames);

		logger.info("Added {} files.", comparison.getAdded().size());
		logger.info("Updated {} existing files.", comparison.getSame().size());
		logger.info("Deleted {} files.", comparison.getDeleted().size());

		PropertyUtils.addListComparisonProperties(mavenProperties, comparison, propertyNames);
	}

	public String getFilePattern() {
		return filePattern;
	}

	public void setFilePattern(String filePattern) {
		this.filePattern = filePattern;
	}

	public Properties getMavenProperties() {
		return mavenProperties;
	}

	public void setMavenProperties(Properties mavenProperties) {
		this.mavenProperties = mavenProperties;
	}

	public String getNewFilesBaseDir() {
		return newFilesBaseDir;
	}

	public void setNewFilesBaseDir(String newFilesBaseDir) {
		this.newFilesBaseDir = newFilesBaseDir;
	}

	public String getOriginalFilesBaseDir() {
		return originalFilesBaseDir;
	}

	public void setOriginalFilesBaseDir(String originalFilesBaseDir) {
		this.originalFilesBaseDir = originalFilesBaseDir;
	}

	public List<String> getPropertyNames() {
		return propertyNames;
	}

	public void setPropertyNames(List<String> propertyNames) {
		this.propertyNames = propertyNames;
	}
}
