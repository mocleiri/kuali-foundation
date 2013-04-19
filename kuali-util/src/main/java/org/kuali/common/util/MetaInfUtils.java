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
package org.kuali.common.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MetaInfUtils {

	private static final Logger logger = LoggerFactory.getLogger(MetaInfUtils.class);

	public static void scanAndCreateFiles(List<MetaInfContext> contexts) throws IOException {
		for (MetaInfContext context : contexts) {
			List<File> files = getFiles(context);
			List<MetaInfResource> resources = getResources(context, files);
			doLocations(context, resources);
			if (context.isAddPropertiesFile()) {
				doProperties(context, resources);
			}
		}
	}

	public static List<File> getFiles(MetaInfContext context) throws IOException {
		Assert.notNull(context.getBaseDir(), "baseDir is null");
		Assert.notNull(context.getOutputFile(), "outputFile is null");
		List<String> includes = context.getIncludes();
		List<String> excludes = context.getExcludes();
		logger.debug("Examining - " + context.getBaseDir().getCanonicalPath());
		String incl = CollectionUtils.getSpaceSeparatedString(includes);
		String excl = CollectionUtils.getSpaceSeparatedString(excludes);
		StringBuilder sb = new StringBuilder();
		sb.append("include: " + incl);
		if (!StringUtils.isBlank(excl)) {
			sb.append(", exclude:" + excl);
		}
		logger.info("[" + sb.toString() + "]");
		SimpleScanner scanner = new SimpleScanner(context.getBaseDir(), includes, excludes);
		List<File> files = scanner.getFiles();
		logger.debug("Located " + files.size() + " files");
		return files;
	}

	public static void doLocations(MetaInfContext context, List<MetaInfResource> resources) throws IOException {
		List<String> locations = getLocations(resources);
		if (context.isSort()) {
			Collections.sort(locations);
		}
		String path1 = LocationUtils.getCanonicalPath(context.getBaseDir());
		String path2 = LocationUtils.getCanonicalPath(context.getOutputFile());
		String path = StringUtils.remove(path2, path1);
		logger.info("Creating [" + path + "] - {} resources", locations.size());
		FileUtils.writeLines(context.getOutputFile(), locations);
	}

	public static void doProperties(MetaInfContext context, List<MetaInfResource> resources) {
		logger.debug("doProperties()");
		Properties properties = getProperties(context, resources);
		File propertiesFile = new File(LocationUtils.getCanonicalPath(context.getOutputFile()) + ".properties");
		PropertyUtils.store(properties, propertiesFile, "UTF-8");
	}

	public static Properties getProperties(MetaInfContext context, List<MetaInfResource> resources) {
		Properties properties = new Properties();
		for (MetaInfResource resource : resources) {
			String sizeKey = resource.getKey() + ".size";
			properties.setProperty(sizeKey, resource.getSize() + "");
			if (context.isAddLineCount()) {
				String linesKey = resource.getKey() + ".lines";
				properties.setProperty(linesKey, resource.getLines() + "");
			}
		}
		return properties;
	}

	public static String getPropertyKey(String location) {
		String key = StringUtils.replace(location, ":", ".");
		return StringUtils.replace(key, "/", ".");
	}

	public static void scanAndCreateFile(MetaInfContext context) throws IOException {
		scanAndCreateFiles(Arrays.asList(context));
	}

	public static List<MetaInfResource> getResources(MetaInfContext context, List<File> files) throws IOException {
		List<MetaInfResource> resources = new ArrayList<MetaInfResource>();
		for (int i = 0; i < files.size(); i++) {
			MetaInfResource resource = getResource(context, files.get(i));
			resources.add(resource);
		}
		return resources;
	}

	public static List<String> getLocations(List<MetaInfResource> resources) {
		List<String> locations = new ArrayList<String>();
		for (MetaInfResource resource : resources) {
			locations.add(resource.getLocation());
		}
		return locations;
	}

	public static List<String> getLocations(File baseDir, List<File> files, String prefix) throws IOException {
		List<String> locations = new ArrayList<String>();
		for (int i = 0; i < files.size(); i++) {
			String location = getLocation(baseDir, files.get(i), prefix);
			locations.add(location);
		}
		return locations;
	}

	public static MetaInfResource getResource(MetaInfContext context, File file) throws IOException {
		String location = getLocation(context.getBaseDir(), file, context.getPrefix());
		long size = file.length();
		long lines = -1;
		if (context.isAddLineCount()) {
			lines = LocationUtils.getLineCount(file);
		}
		String key = getPropertyKey(location);

		MetaInfResource resource = new MetaInfResource();
		resource.setLocation(location);
		resource.setSize(size);
		resource.setKey(key);
		resource.setLines(lines);
		return resource;
	}

	public static String getLocation(File baseDir, File file, String prefix) throws IOException {
		String dir = baseDir.getCanonicalPath();
		String path = file.getCanonicalPath();
		int pos = dir.length() + 1;
		return prefix + StringUtils.substring(path, pos);
	}

}
