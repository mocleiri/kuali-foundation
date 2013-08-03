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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultMetaInfService implements MetaInfService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultMetaInfService.class);

	@Override
	public void scanAndCreateFile(MetaInfContext context) {
		scanAndCreateFiles(Arrays.asList(context));
	}

	@Override
	public void scanAndCreateFiles(List<MetaInfContext> contexts) {
		for (MetaInfContext context : contexts) {
			List<File> files = getFiles(context);
			List<MetaInfResource> resources = getResources(context, files);
			doLocations(context, resources);
			if (context.isAddPropertiesFile()) {
				doProperties(context, resources);
			}
		}
	}

	protected void doLocations(MetaInfContext context, List<MetaInfResource> resources) {
		List<String> locations = getLocations(resources);
		if (context.isSort()) {
			Collections.sort(locations);
		}
		String path1 = LocationUtils.getCanonicalPath(context.getBaseDir());
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
		Assert.notNull(context.getBaseDir(), "baseDir is null");
		Assert.notNull(context.getOutputFile(), "outputFile is null");
		logger.debug("Examining " + LocationUtils.getCanonicalPath(context.getBaseDir()));
		logger.debug("Patterns - {}", getPatternLogMessage(context));
		List<String> includes = context.getIncludes();
		List<String> excludes = context.getExcludes();
		SimpleScanner scanner = new SimpleScanner(context.getBaseDir(), includes, excludes);
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

	protected String getLocation(File baseDir, File file, String prefix) {
		String dir = LocationUtils.getCanonicalPath(baseDir);
		String path = LocationUtils.getCanonicalPath(file);
		int pos = dir.length() + 1;
		return prefix + StringUtils.substring(path, pos);
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
			String sizeKey = resource.getKey() + ".size";
			properties.setProperty(sizeKey, resource.getSize() + "");
			if (context.isAddLineCount()) {
				String linesKey = resource.getKey() + ".lines";
				properties.setProperty(linesKey, resource.getLines() + "");
			}
		}
		return properties;
	}

	protected List<String> getLocations(File baseDir, List<File> files, String prefix) throws IOException {
		List<String> locations = new ArrayList<String>();
		for (File file : files) {
			String location = getLocation(baseDir, file, prefix);
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
