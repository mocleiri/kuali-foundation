package org.kuali.common.util.metainf.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.FileSystemUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.SimpleScanner;
import org.kuali.common.util.file.CanonicalFile;
import org.kuali.common.util.log.LoggerUtils;
import org.kuali.common.util.metainf.model.MetaInfContext;
import org.kuali.common.util.metainf.model.MetaInfResource;
import org.kuali.common.util.metainf.model.ScanResult;
import org.kuali.common.util.metainf.model.WriteLines;
import org.kuali.common.util.metainf.model.WriteProperties;
import org.kuali.common.util.metainf.model.WriteRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultMetaInfService implements MetaInfService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultMetaInfService.class);

	protected static final String PROPERTIES = "properties";
	protected static final String SIZE = "size";
	protected static final String LINES = "lines";

	@Override
	public ScanResult scan(MetaInfContext context) {
		List<File> files = scanFileSystem(context);
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
		File relativeDir = context.getRelativeDir();
		WriteRequest request = new WriteRequest(outputFile, encoding, relativeDir);
		return new WriteLines(request, locations);
	}

	@Override
	public void write(List<ScanResult> results) {
		List<WriteLines> lines = getWriteLines(results);
		List<WriteProperties> properties = getWriteProperties(results);
		for (WriteLines element : CollectionUtils.toEmptyList(lines)) {
			WriteRequest request = element.getRequest();
			String relativePath = FileSystemUtils.getRelativePathQuietly(request.getRelativeDir(), request.getOutputFile());
			logger.info("Creating [{}] - {} resources", relativePath, element.getLines().size());
			write(request, element.getLines());
		}
		for (WriteProperties element : CollectionUtils.toEmptyList(properties)) {
			WriteRequest request = element.getRequest();
			PropertyUtils.store(element.getProperties(), request.getOutputFile(), request.getEncoding());
		}
	}

	protected void write(WriteRequest request, List<String> lines) {
		try {
			FileUtils.writeLines(request.getOutputFile(), request.getEncoding(), lines);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unexpected IO error", e);
		}
	}

	protected List<WriteProperties> getWriteProperties(List<ScanResult> results) {
		List<WriteProperties> requests = new ArrayList<WriteProperties>();
		for (ScanResult result : results) {
			MetaInfContext context = result.getContext();
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
		File canonical = new CanonicalFile(context.getOutputFile());
		File outputFile = new File(canonical.getPath() + "." + PROPERTIES);
		String encoding = context.getEncoding();
		File relativeDir = context.getRelativeDir();
		WriteRequest request = new WriteRequest(outputFile, encoding, relativeDir);
		return new WriteProperties(request, properties);
	}

	protected List<WriteLines> getWriteLines(List<ScanResult> results) {
		List<WriteLines> requests = new ArrayList<WriteLines>();
		for (ScanResult result : results) {
			WriteLines request = getWriteLines(result);
			requests.add(request);
		}
		return requests;
	}

	protected List<File> scanFileSystem(MetaInfContext context) {
		File dir = context.getScanDir();
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
			if (context.getComparator().isPresent()) {
				Comparator<MetaInfResource> comparator = context.getComparator().get();
				Collections.sort(resources, comparator);
			} else {
				Collections.sort(resources);
			}
		}
		return resources;
	}

	protected MetaInfResource getResource(File resourceFile, MetaInfContext context) {
		String location = getLocationURL(new CanonicalFile(resourceFile), context);

		long lineCount = MetaInfResource.UNKNOWN_LINECOUNT;

		// Only read through the file if we've been explicitly configured to do so
		if (context.isIncludeLineCounts()) {

			// Make sure an encoding has been supplied
			Assert.noBlanks(context.getEncoding());

			// Read through the entire file keeping track of how many lines of text we encounter
			lineCount = LocationUtils.getLineCount(resourceFile, context.getEncoding());
		}

		// Create a resource object from the information we've collected
		return new MetaInfResource(location, resourceFile.length(), lineCount);
	}

	protected String getLocationURL(CanonicalFile resourceFile, MetaInfContext context) {
		if (!context.isRelativePaths()) {
			return LocationUtils.getCanonicalURLString(resourceFile);
		} else {
			return getRelativeLocationURL(resourceFile, context);
		}
	}

	/**
	 * Get a URL string that can be used to address <code>file</code>. This is usually a Spring pseudo-url classpath location, eg - [<code>classpath:foo/bar.txt</code>]
	 * 
	 * @param resourceFile
	 *            The file to get a location url for. eg - [<code>/x/y/z/src/main/resources/foo/bar.txt</code>]
	 * @param context
	 *            Context information for generating a relative location url. eg - [<code>/x/y/z/src/main/resources</code>] and [<code>classpath:</code>].
	 * 
	 * @return A string representing a fully qualified location URL for <code>file</code>. eg - [<code>classpath:foo/bar.txt</code>]
	 */
	protected String getRelativeLocationURL(CanonicalFile resourceFile, MetaInfContext context) {

		// Extract the parent directory
		CanonicalFile parent = new CanonicalFile(context.getRelativeDir());

		// Make sure it is an existing directory
		Assert.isExistingDir(parent);

		// Get a string representing the path to the parent dir
		String parentPath = parent.getPath();

		// Get a string representing the path to the resource file
		String resourcePath = resourceFile.getPath();

		// Make sure the resource file resides underneath the parent dir
		Assert.isTrue(StringUtils.contains(resourcePath, parentPath), "[" + resourcePath + "] does not contain [" + parentPath + "]");

		// Extract the portion of the path to the resource file that is relative to the parent dir
		int relativePos = parentPath.length() + 1;
		String relativePath = StringUtils.substring(resourcePath, relativePos);

		// Prepend the prefix and return
		return context.getUrlPrefix() + relativePath;
	}

	protected String getPropertyKey(String location) {
		location = StringUtils.replace(location, ":", ".");
		location = StringUtils.replace(location, "/", ".");
		return location;
	}

}
