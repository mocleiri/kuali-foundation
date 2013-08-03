package org.kuali.common.util.metainf.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.SimpleScanner;
import org.kuali.common.util.metainf.model.MetaInfContext;
import org.kuali.common.util.metainf.model.MetaInfResource;
import org.kuali.common.util.metainf.model.ScanResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultMetaInfService implements MetaInfService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultMetaInfService.class);

	@Override
	public ScanResult scan(MetaInfContext context) {
		List<ScanResult> results = scan(Arrays.asList(context));
		Assert.isTrue(results.size() == 1, "size != 1");
		return results.get(0);
	}

	@Override
	public List<ScanResult> scan(List<MetaInfContext> contexts) {
		List<ScanResult> results = new ArrayList<ScanResult>();
		for (MetaInfContext context : contexts) {
			List<File> files = getFiles(context);
			List<MetaInfResource> resources = getResources(context, files);
			if (context.isAddPropertiesFile()) {
				doProperties(context, resources);
			}
		}
		return results;
	}

	protected void doLocations(MetaInfContext context, List<MetaInfResource> resources) {
		List<String> locations = getLocations(resources);
		if (context.isSort()) {
			Collections.sort(locations);
		}
		String path1 = LocationUtils.getCanonicalPath(context.getRelativeDir());
		String path2 = LocationUtils.getCanonicalPath(context.getOutputFile());
		String path = StringUtils.remove(path2, path1);
		logger.info("Creating [" + path + "] - {} resources", locations.size());
		try {
			FileUtils.writeLines(context.getOutputFile(), locations);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		}
	}

	protected List<File> getFiles(MetaInfContext context) {
		Assert.notNull(context.getRelativeDir(), "baseDir is null");
		Assert.notNull(context.getOutputFile(), "outputFile is null");
		logger.debug("Examining " + LocationUtils.getCanonicalPath(context.getRelativeDir()));
		logger.debug("Patterns - {}", getPatternLogMessage(context));
		List<String> includes = context.getIncludes();
		List<String> excludes = context.getExcludes();
		SimpleScanner scanner = new SimpleScanner(context.getRelativeDir(), includes, excludes);
		return scanner.getFiles();
	}

	protected String getPatternLogMessage(MetaInfContext context) {
		StringBuilder sb = new StringBuilder();
		String incl = CollectionUtils.getSpaceSeparatedString(context.getIncludes());
		String excl = CollectionUtils.getSpaceSeparatedString(context.getExcludes());
		sb.append("[");
		if (!StringUtils.isBlank(incl)) {
			sb.append("include: ").append(incl);
		}
		if (!StringUtils.isBlank(excl)) {
			sb.append(", exclude:").append(excl);
		}
		boolean includeEverything = StringUtils.isBlank(incl) && StringUtils.isBlank(excl);
		if (includeEverything) {
			sb.append("include: *");
		}
		sb.append("]");
		return sb.toString();
	}

	protected List<MetaInfResource> getResources(MetaInfContext context, List<File> files) {
		List<MetaInfResource> resources = new ArrayList<MetaInfResource>();
		for (File file : files) {
			MetaInfResource resource = getResource(context, file);
			resources.add(resource);
		}
		return resources;
	}

	protected MetaInfResource getResource(MetaInfContext context, File file) {
		String location = getLocationURL(file, context.getRelativeDir(), context.getPrefix());
		long size = file.length();

		long lineCount = -1;
		if (context.isAddLineCount()) {
			// This reads through the entire file
			// Only complete this expensive task if required to do so
			lineCount = LocationUtils.getLineCount(file);
		}

		// Create a resource object from the information we've collected
		MetaInfResource resource = new MetaInfResource();
		resource.setLocation(location);
		resource.setSize(size);
		resource.setLineCount(lineCount);
		return resource;
	}

	/**
	 * Get a URL string that can be used to address <code>file</code>. This is usually a Spring pseudo-url classpath location, eg - [<code>classpath:foo/bar.txt</code>]
	 * 
	 * @param file
	 *            The file to get a location url for. eg - [<code>/x/y/z/src/main/resources/foo/bar.txt</code>]
	 * @param relativeDir
	 *            The base directory (optional). eg - [<code>/x/y/z/src/main/resources</code>]. If supplied, <code>file</code> must reside underneath <code>relativeDir</code>
	 * @param relativeUrlPrefix
	 *            The prefix (optional) to prepend to the relative location. eg - [<code>classpath:</code>]
	 * 
	 * @return A string representing a fully qualified location URL for <code>file</code>. eg - [<code>classpath:foo/bar.txt</code>] or
	 *         [file:///x/y/z/src/main/resources/foo/bar.txt] if either <code>relativeDir</code> or <code>relativeUrlPrefix</code> are <code>null</code>
	 */
	protected String getLocationURL(File file, File relativeDir, String relativeUrlPrefix) {
		// Make sure file has been supplied
		Assert.notNull(file, "file is null");

		// If either of these are null just return the fully qualified local file system url
		if (relativeDir == null || relativeUrlPrefix == null) {
			return LocationUtils.getCanonicalURLString(file);
		}

		// Get a string representing the canonical path to the relative dir
		String relativeDirPath = LocationUtils.getCanonicalPath(relativeDir);

		// Get a string representing the canonical path to the file
		String filePath = LocationUtils.getCanonicalPath(file);

		// Make sure file resides underneath relative dir
		Assert.isTrue(StringUtils.contains(filePath, relativeDirPath), "[" + filePath + "] does not contain [" + relativeDirPath + "]");

		// Extract the portion of the path to file that is relative to relative dir
		int relativePos = relativeDirPath.length() + 1;
		String relativePath = StringUtils.substring(filePath, relativePos);

		// Prepend the prefix and return
		return relativeUrlPrefix + relativePath;
	}

	protected String getPropertyKey(String location) {
		String key = StringUtils.replace(location, ":", ".");
		return StringUtils.replace(key, "/", ".");
	}

	protected void doProperties(MetaInfContext context, List<MetaInfResource> resources) {
		logger.debug("doProperties()");
		Properties properties = getProperties(context, resources);
		File propertiesFile = new File(LocationUtils.getCanonicalPath(context.getOutputFile()) + ".properties");
		PropertyUtils.store(properties, propertiesFile, "UTF-8");
	}

	protected Properties getProperties(MetaInfContext context, List<MetaInfResource> resources) {
		Properties properties = new Properties();
		for (MetaInfResource resource : resources) {
			String key = getPropertyKey(resource.getLocation());
			String sizeKey = key + ".size";
			properties.setProperty(sizeKey, resource.getSize() + "");
			if (context.isAddLineCount()) {
				String linesKey = key + ".lines";
				properties.setProperty(linesKey, resource.getLineCount() + "");
			}
		}
		return properties;
	}

	protected List<String> getLocations(File relativeDir, List<File> files, String prefix) throws IOException {
		List<String> locations = new ArrayList<String>();
		for (File file : files) {
			String location = getLocationURL(file, relativeDir, prefix);
			locations.add(location);
		}
		return locations;
	}

	protected List<String> getLocations(List<MetaInfResource> resources) {
		List<String> locations = new ArrayList<String>();
		for (MetaInfResource resource : resources) {
			locations.add(resource.getLocation());
		}
		return locations;
	}

}
