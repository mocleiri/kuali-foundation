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
import org.kuali.common.util.FileSystemUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.LoggerUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.SimpleScanner;
import org.kuali.common.util.metainf.model.MetaInfContext;
import org.kuali.common.util.metainf.model.MetaInfResource;
import org.kuali.common.util.metainf.model.PropertiesContext;
import org.kuali.common.util.metainf.model.RelativeContext;
import org.kuali.common.util.metainf.model.ScanContext;
import org.kuali.common.util.metainf.model.ScanResult;
import org.kuali.common.util.metainf.model.WriteLines;
import org.kuali.common.util.metainf.model.WriteProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultMetaInfService implements MetaInfService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultMetaInfService.class);

	protected static final String PROPERTIES = "properties";
	protected static final String SIZE = "size";
	protected static final String LINES = "lines";

	@Override
	public ScanResult scan(MetaInfContext context) {
		List<File> files = scanFileSystem(context.getScanContext());
		List<MetaInfResource> resources = getResources(context, files);
		return new ScanResult(context, resources);
	}

	@Override
	public List<ScanResult> scan(List<MetaInfContext> contexts) {
		List<ScanResult> results = new ArrayList<ScanResult>();
		for (MetaInfContext context : contexts) {
			ScanResult result = scan(context);
			results.add(result);
		}
		return results;
	}

	@Override
	public void write(ScanResult result) {
		write(Arrays.asList(result));
	}

	protected WriteLines getWriteLines(ScanResult result) {
		List<MetaInfResource> resources = result.getResources();
		List<String> locations = new ArrayList<String>();
		for (MetaInfResource resource : resources) {
			locations.add(resource.getLocation());
		}
		MetaInfContext context = result.getContext();
		File outputFile = context.getOutputFile();
		String encoding = context.getEncoding();
		File relativeDir = context.getRelativeContext().getDirectory();
		return new WriteLines(locations, outputFile, encoding, relativeDir);
	}

	@Override
	public void write(List<ScanResult> results) {
		List<WriteLines> lines = getWriteLines(results);
		List<WriteProperties> properties = getWriteProperties(results);
		for (WriteLines element : CollectionUtils.toEmptyList(lines)) {
			String relativePath = FileSystemUtils.getRelativePathQuietly(element.getOutputFile(), element.getRelativeDir());
			logger.info("Creating [{}]", relativePath);
			write(element);
		}
		for (WriteProperties element : CollectionUtils.toEmptyList(properties)) {
			PropertyUtils.store(element.getProperties(), element.getOutputFile(), element.getEncoding());
		}
	}

	protected void write(WriteLines lines) {
		try {
			FileUtils.writeLines(lines.getOutputFile(), lines.getLines(), lines.getEncoding());
		} catch (IOException e) {
			throw new IllegalArgumentException("Unexpected IO error", e);
		}
	}

	protected List<WriteProperties> getWriteProperties(List<ScanResult> results) {
		List<WriteProperties> requests = new ArrayList<WriteProperties>();
		for (ScanResult result : results) {
			PropertiesContext context = result.getContext().getPropertiesContext();
			if (context.isIncludePropertiesFile()) {
				WriteProperties request = getWriteProperties(result);
				requests.add(request);
			}
		}
		return requests;
	}

	protected WriteProperties getWriteProperties(ScanResult result) {
		List<MetaInfResource> resources = result.getResources();
		Properties properties = new Properties();
		for (MetaInfResource resource : resources) {
			String key = getPropertyKey(resource.getLocation());
			String sizeKey = key + "." + SIZE;
			String linesKey = key + "." + LINES;
			properties.setProperty(sizeKey, Long.toString(resource.getSize()));
			properties.setProperty(linesKey, Long.toString(resource.getLineCount()));
		}
		MetaInfContext context = result.getContext();
		File outputFile = new File(context.getOutputFile().getAbsolutePath() + "." + PROPERTIES);
		String encoding = context.getEncoding();
		File relativeDir = context.getRelativeContext().getDirectory();
		return new WriteProperties(properties, outputFile, encoding, relativeDir);
	}

	protected List<WriteLines> getWriteLines(List<ScanResult> results) {
		List<WriteLines> requests = new ArrayList<WriteLines>();
		for (ScanResult result : results) {
			WriteLines request = getWriteLines(result);
			requests.add(request);
		}
		return requests;
	}

	protected List<File> scanFileSystem(ScanContext context) {
		File dir = context.getDirectory();
		Assert.isExistingDir(dir);
		logger.debug("Examining [" + LocationUtils.getCanonicalPath(dir) + "]");
		List<String> includes = context.getIncludes();
		List<String> excludes = context.getExcludes();
		logger.debug("Patterns - {}", LoggerUtils.getLogMsg(includes, excludes));
		SimpleScanner scanner = new SimpleScanner(dir, includes, excludes);
		return scanner.getFiles();
	}

	protected List<MetaInfResource> getResources(MetaInfContext context, List<File> files) {
		List<MetaInfResource> resources = new ArrayList<MetaInfResource>();
		for (File file : files) {
			MetaInfResource resource = getResource(file, context);
			resources.add(resource);
		}
		if (context.isSort()) {
			Collections.sort(resources);
		}
		return resources;
	}

	protected MetaInfResource getResource(File file, MetaInfContext context) {
		String location = getLocationURL(file, context.getRelativeContext());

		long lineCount = MetaInfResource.UNKNOWN_LINECOUNT;
		if (context.getPropertiesContext().isIncludeLineCounts()) {
			// This reads through the entire file
			// Only complete this expensive task if required to do so
			lineCount = LocationUtils.getLineCount(file);
		}

		// Create a resource object from the information we've collected
		return new MetaInfResource(location, file.length(), lineCount);
	}

	/**
	 * Get a URL string that can be used to address <code>file</code>. This is usually a Spring pseudo-url classpath location, eg - [<code>classpath:foo/bar.txt</code>]
	 * 
	 * @param file
	 *            The file to get a location url for. eg - [<code>/x/y/z/src/main/resources/foo/bar.txt</code>]
	 * @param relativeContext
	 *            Context information for generating a relative location url. (optional). eg - [<code>/x/y/z/src/main/resources</code>] and [<code>classpath:</code>].
	 * 
	 * @return A string representing a fully qualified location URL for <code>file</code>. eg - [<code>classpath:foo/bar.txt</code>] or
	 *         [file:///x/y/z/src/main/resources/foo/bar.txt] if <code>relativeContext</code> is <code>null</code>
	 */
	protected String getLocationURL(File file, RelativeContext context) {
		// Make sure file has been supplied
		Assert.notNull(file, "file is null");

		// If there is no relative context, just return the fully qualified local file system url
		if (context == null) {
			return LocationUtils.getCanonicalURLString(file);
		}

		// Get a string representing the canonical path to the relative dir
		String relativeDirPath = LocationUtils.getCanonicalPath(context.getDirectory());

		// Get a string representing the canonical path to the file
		String filePath = LocationUtils.getCanonicalPath(file);

		// Make sure file resides underneath relative dir
		Assert.isTrue(StringUtils.contains(filePath, relativeDirPath), "[" + filePath + "] does not contain [" + relativeDirPath + "]");

		// Extract the portion of the path to file that is relative to relative dir
		int relativePos = relativeDirPath.length() + 1;
		String relativePath = StringUtils.substring(filePath, relativePos);

		// Prepend the prefix and return
		return context.getUrlPrefix() + relativePath;
	}

	protected String getPropertyKey(String location) {
		location = StringUtils.replace(location, ":", ".");
		location = StringUtils.replace(location, "/", ".");
		return location;
	}

}
