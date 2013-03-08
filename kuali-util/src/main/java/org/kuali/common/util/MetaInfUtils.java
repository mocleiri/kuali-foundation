package org.kuali.common.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.codehaus.plexus.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MetaInfUtils {

	private static final Logger logger = LoggerFactory.getLogger(MetaInfUtils.class);

	public static void scanAndCreateFiles(List<MetaInfContext> contexts) throws IOException {
		for (MetaInfContext context : contexts) {
			List<File> files = getFiles(context);
			logger.info("Located " + files.size() + " files");
			List<MetaInfResource> resources = getResources(context.getBaseDir(), files, context.getPrefix());
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
		logger.info("Examining - " + context.getBaseDir().getCanonicalPath());
		logger.info("Include - " + CollectionUtils.getSpaceSeparatedString(includes));
		logger.info("Exclude - " + CollectionUtils.getSpaceSeparatedString(excludes));
		SimpleScanner scanner = new SimpleScanner(context.getBaseDir(), includes, excludes);
		return scanner.getFiles();
	}

	public static void doLocations(MetaInfContext context, List<MetaInfResource> resources) throws IOException {
		List<String> locations = getLocations(resources);
		if (context.isSort()) {
			Collections.sort(locations);
		}
		logger.info("Creating " + context.getOutputFile().getCanonicalPath());
		FileUtils.writeLines(context.getOutputFile(), locations);
	}

	public static void doProperties(MetaInfContext context, List<MetaInfResource> resources) {
		Properties properties = getProperties(resources);
		File propertiesFile = new File(LocationUtils.getCanonicalPath(context.getOutputFile()) + ".properties");
		PropertyUtils.store(properties, propertiesFile, "UTF-8");
	}

	public static Properties getProperties(List<MetaInfResource> resources) {
		Properties properties = new Properties();
		for (MetaInfResource resource : resources) {
			String sizeKey = resource.getKey() + ".size";
			properties.setProperty(sizeKey, resource.getSize() + "");
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

	public static List<MetaInfResource> getResources(File baseDir, List<File> files, String prefix) throws IOException {
		List<MetaInfResource> resources = new ArrayList<MetaInfResource>();
		for (int i = 0; i < files.size(); i++) {
			MetaInfResource resource = getResource(baseDir, files.get(i), prefix);
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

	public static MetaInfResource getResource(File baseDir, File file, String prefix) throws IOException {
		String location = getLocation(baseDir, file, prefix);
		long size = file.length();
		String key = getPropertyKey(location);

		MetaInfResource resource = new MetaInfResource();
		resource.setLocation(location);
		resource.setSize(size);
		resource.setKey(key);
		return resource;
	}

	public static String getLocation(File baseDir, File file, String prefix) throws IOException {
		String dir = baseDir.getCanonicalPath();
		String path = file.getCanonicalPath();
		int pos = dir.length() + 1;
		return prefix + StringUtils.substring(path, pos);
	}

}
