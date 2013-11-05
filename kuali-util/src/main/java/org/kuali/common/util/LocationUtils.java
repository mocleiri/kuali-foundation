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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.Assert;

public class LocationUtils {

	private static final Logger logger = LoggerFactory.getLogger(LocationUtils.class);

	private static final String FILE_PREFIX = "file:";
	private static final String BACK_SLASH = "\\";
	private static final String FORWARD_SLASH = "/";
	private static final String SLASH_DOT_SLASH = "/./";
	private static final String DOT_DOT_SLASH = "../";
	private static final String SLASH_DOT_DOT = "/..";
	private static final String CLASSPATH = "classpath:";
	private static final String MD5 = "MD5";

	/**
	 * Get the MD5 checksum of the location
	 */
	public static String getMD5Checksum(String location) {
		return getChecksum(location, MD5);
	}

	/**
	 * Get the MD5 checksum of the file
	 */
	public static String getMD5Checksum(File file) {
		return getChecksum(getCanonicalPath(file), MD5);
	}

	/**
	 * Open a <code>PrintStream</code> to the indicated file. Parent directories are created if necessary.
	 */
	public static final PrintStream openPrintStream(File file) throws IOException {
		return new PrintStream(FileUtils.openOutputStream(file));
	}

	/**
	 * Open a <code>Writer</code> to the indicated file. Parent directories are created if necessary.
	 */
	public static final Writer openWriter(File file) throws IOException {
		touch(file);
		return new FileWriter(file);
	}

	/**
	 * Open a <code>Writer</code> to the <code>location</code> (It must be a writable file on the local file system). Parent directories are created if necessary.
	 */
	public static final Writer openWriter(String location) throws IOException {
		return openWriter(new File(location));
	}

	public static Properties getLocationProperties(LocationPropertiesContext context) {

		Assert.notNull(context, "context is null");

		Properties properties = context.getProperties();
		String keySuffix = context.getKeySuffix();
		String locationPropertiesSuffix = context.getLocationPropertiesSuffix();
		String encoding = context.getEncoding();

		Assert.notNull(properties, "properties is null");
		Assert.notNull(keySuffix, "keySuffix is null");
		Assert.notNull(locationPropertiesSuffix, "locationPropertiesSuffix is null");

		List<String> keys = PropertyUtils.getEndsWithKeys(properties, keySuffix);

		Properties locationProperties = new Properties();
		for (String key : keys) {
			String location = properties.getProperty(key);
			if (!exists(location)) {
				continue;
			}
			String propertiesLocation = location + locationPropertiesSuffix;
			if (!exists(propertiesLocation)) {
				continue;
			}
			Properties p = PropertyUtils.load(propertiesLocation, encoding);
			locationProperties.putAll(p);
		}
		logger.info("Located {} properties for {} location listings", locationProperties.size(), keys.size());
		return locationProperties;
	}

	public static TextMetaData getTextMetaData(File file) {
		return getTextMetaData(getCanonicalPath(file));
	}

	public static TextMetaData getTextMetaData(String location) {
		long lines = 0;
		long size = 0;
		BufferedReader in = null;
		try {
			in = getBufferedReader(location);
			String s = in.readLine();
			while (s != null) {
				lines++;
				size += s.length();
				s = in.readLine();
			}
			return new TextMetaData(lines, size);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	/**
	 * Count the lines of text in the file.
	 * 
	 * @param file
	 *            The file to read lines of text from
	 * @return A count representing the number of lines of text
	 * @throws IllegalStateException
	 *             If there is an i/o exception reading the file
	 * @see BufferedReader
	 */
	public static long getLineCount(File file) {
		return getLineCount(file, null);
	}

	public static long getLineCount(File file, String encoding) {
		return getLineCount(getCanonicalPath(file));
	}

	/**
	 * Count the lines of text in the location.
	 * 
	 * @param location
	 *            The location to read lines of text from
	 * @return A count representing the number of lines of text
	 * @throws IllegalStateException
	 *             If there is an i/o exception reading the file
	 * @see BufferedReader
	 * @deprecated Use getLineCount(location,encoding) instead
	 */
	@Deprecated
	public static long getLineCount(String location) {
		return getLineCount(location, null);
	}

	/**
	 * Count the lines of text in the location.
	 * 
	 * @param location
	 *            The location to read lines of text from
	 * @return A count representing the number of lines of text
	 * @throws IllegalStateException
	 *             If there is an i/o exception reading the file
	 * @see BufferedReader
	 */
	public static long getLineCount(String location, String encoding) {
		long count = 0;
		BufferedReader in = null;
		try {
			in = getBufferedReader(location, encoding);
			while (in.readLine() != null) {
				count++;
			}
			return count;
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	public static final void copyLocationsToFiles(List<String> locations, List<File> files) {
		Assert.isTrue(locations.size() == files.size());
		for (int i = 0; i < locations.size(); i++) {
			String location = locations.get(i);
			File destination = files.get(i);
			copyLocationToFile(location, destination);
		}
	}

	/**
	 * Return the text that appears after <code>classpath:</code>. Throws <code>IllegalArgumentException</code> if location does not start with <code>classpath:</code>
	 */
	public static final String getClasspathFilename(String location) {
		return getClasspathFilenames(Arrays.asList(location)).get(0);
	}

	/**
	 * Return the text that appears after <code>classpath:</code>. Throws <code>IllegalArgumentException</code> if any locations do not start with <code>classpath:</code>
	 */
	public static final List<String> getClasspathFilenames(List<String> locations) {
		List<String> classpathFilenames = new ArrayList<String>();
		for (String location : locations) {
			if (!isClasspathLocation(location)) {
				throw new IllegalArgumentException(location + " must start with " + CLASSPATH);
			} else {
				classpathFilenames.add(StringUtils.substring(location, CLASSPATH.length()));
			}
		}
		return classpathFilenames;
	}

	/**
	 * Return <code>true</code> if location starts with <code>classpath:</code>
	 */
	public static final boolean isClasspathLocation(String location) {
		return StringUtils.startsWith(location, CLASSPATH);
	}

	public static final List<String> getNormalizedPathFragments(String absolutePath, boolean directory) {
		String normalized = getNormalizedAbsolutePath(absolutePath);
		String[] tokens = StringUtils.split(normalized, FORWARD_SLASH);
		List<String> fragments = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		sb.append(FORWARD_SLASH);
		int length = directory ? tokens.length : tokens.length - 1;
		for (int i = 0; i < length; i++) {
			if (i != 0) {
				sb.append(FORWARD_SLASH);
			}
			sb.append(tokens[i]);
			fragments.add(sb.toString());
		}
		return fragments;
	}

	public static final List<String> getCanonicalPaths(List<File> files) {
		List<String> paths = new ArrayList<String>();
		for (File file : files) {
			String path = getCanonicalPath(file);
			paths.add(path);
		}
		return paths;
	}

	public static final List<String> getLocations(String location, LocationType type, String encoding) {
		switch (type) {
		case LOCATION:
			return Collections.singletonList(location);
		case LOCATIONLIST:
			return getLocations(location, encoding);
		default:
			throw new IllegalArgumentException("Location type '" + type + "' is unknown");
		}
	}

	public static final List<String> getLocations(String location, LocationType type) {
		return getLocations(location, type, null);
	}

	public static final List<String> getLocations(String locationListing) {
		return getLocations(Collections.singletonList(locationListing), null);
	}

	public static final List<String> getLocations(String locationListing, String encoding) {
		return getLocations(Collections.singletonList(locationListing), encoding);
	}

	public static final List<String> getLocations(List<String> locationListings) {
		return getLocations(locationListings, null);
	}

	public static final void copyLocationToFile(String location, File destination) {
		Assert.notNull(location);
		Assert.notNull(destination);
		logger.debug("Copying [{}]->[{}]", location, destination);
		InputStream in = null;
		try {
			in = getInputStream(location);
			FileUtils.copyInputStreamToFile(in, destination);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	public static final List<File> getFiles(File dir, List<String> filenames) {
		List<File> files = new ArrayList<File>();
		for (String filename : filenames) {
			File file = new File(dir, filename);
			files.add(file);
		}
		return files;
	}

	public static final List<String> getFilenames(List<String> locations) {
		Assert.notNull(locations);
		List<String> filenames = new ArrayList<String>();
		for (String location : locations) {
			filenames.add(getFilename(location));
		}
		return filenames;
	}

	/**
	 * Throw IllegalArgumentException if the locationListing does not exist, or if any of the locations inside the locationListing do not exist
	 */
	public static final void validateLocationListing(String locationListing) {
		validateLocationListings(Collections.singletonList(locationListing));
	}

	/**
	 * Throw IllegalArgumentException if any of the locationListings do not exist, or if any of the locations inside any of the locationListings do not exist
	 */
	public static final void validateLocationListings(List<String> locationListings) {
		for (String locationListing : locationListings) {
			validateLocation(locationListing);
			List<String> locations = getLocations(locationListing);
			validateLocations(locations);
		}
	}

	public static final void validateLocations(List<String> locations) {
		for (String location : locations) {
			validateLocation(location);
		}
	}

	/**
	 * Throw IllegalArgumentException if the location does not exist
	 */
	public static final void validateLocation(String location) {
		validateLocation(location, "[" + location + "] does not exist");
	}

	/**
	 * Throw IllegalArgumentException if the location does not exist
	 */
	public static final void validateLocation(String location, String message) {
		Assert.isTrue(exists(location), message);
	}

	public static final List<String> getLocations(List<String> locationListings, String encoding) {
		List<String> locations = new ArrayList<String>();
		for (String locationListing : locationListings) {
			List<String> lines = readLines(locationListing, encoding);
			locations.addAll(lines);
		}
		return locations;
	}

	public static final String getCanonicalURLString(File file) {
		if (file == null) {
			return null;
		}
		String path = getCanonicalPath(file);
		File canonical = new File(path);
		return getURLString(canonical);
	}

	public static final void validateNormalizedPath(String originalPath, String normalizedPath) {
		if (CollectionUtils.containsAny(normalizedPath, Arrays.asList(SLASH_DOT_DOT, SLASH_DOT_SLASH, DOT_DOT_SLASH))) {
			throw new IllegalArgumentException("[" + originalPath + "] could not be normalized. Normalized path [" + normalizedPath + "]");
		}
	}

	/**
	 * Resolve and remove <code>..</code> and <code>.</code> from <code>absolutePath</code> after converting any back slashes to forward slashes
	 */
	public static final String getNormalizedAbsolutePath(String absolutePath) {
		if (absolutePath == null) {
			return null;
		}
		String replaced = StringUtils.replace(absolutePath, BACK_SLASH, FORWARD_SLASH);
		boolean absolute = StringUtils.startsWith(replaced, FORWARD_SLASH);
		if (!absolute) {
			throw new IllegalArgumentException("[" + absolutePath + "] is not an absolute path.");
		}
		String prefixed = FILE_PREFIX + replaced;
		try {
			URI rawURI = new URI(prefixed);
			URI normalizedURI = rawURI.normalize();
			URL normalizedURL = normalizedURI.toURL();
			String externalForm = normalizedURL.toExternalForm();
			String trimmed = StringUtils.substring(externalForm, FILE_PREFIX.length());
			validateNormalizedPath(absolutePath, trimmed);
			return trimmed;
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException(e);
		} catch (URISyntaxException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static final String getURLString(File file) {
		if (file == null) {
			return null;
		}
		try {
			URI uri = file.toURI();
			URL url = uri.toURL();
			return url.toExternalForm();
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static final void forceMkdir(File file) {
		try {
			FileUtils.forceMkdir(file);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unexpected IO error", e);
		}
	}

	public static final void touch(File file) {
		try {
			FileUtils.touch(file);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unexpected IO error", e);
		}
	}

	public static final String getCanonicalPath(File file) {
		try {
			return file.getCanonicalPath();
		} catch (IOException e) {
			throw new IllegalArgumentException("Unexpected IO error", e);
		}
	}

	/**
	 * Null safe method to unconditionally attempt to delete <code>filename</code> without throwing an exception. If <code>filename</code> is a directory, delete it and all
	 * sub-directories.
	 */
	public static final boolean deleteFileQuietly(String filename) {
		File file = getFileQuietly(filename);
		return FileUtils.deleteQuietly(file);
	}

	/**
	 * Null safe method for getting a <code>File</code> handle from <code>filename</code>. If <code>filename</code> is null, null is returned.
	 */
	public static final File getFileQuietly(String filename) {
		if (filename == null) {
			return null;
		} else {
			return new File(filename);
		}
	}

	/**
	 * Get the contents of <code>file</code> as a <code>String</code> using the platform's default character encoding.
	 */
	public static final String toString(File file) {
		return toString(file, null);
	}

	/**
	 * Get the contents of <code>file</code> as a <code>String</code> using the specified character encoding.
	 */
	public static final String toString(File file, String encoding) {
		return toString(getCanonicalPath(file), encoding);
	}

	/**
	 * Get the contents of <code>location</code> as a <code>String</code> using the platform's default character encoding.
	 */
	public static final String toString(String location) {
		return toString(location, null);
	}

	/**
	 * Get the contents of <code>location</code> as a <code>String</code> using the specified character encoding.
	 */
	public static final String toString(String location, String encoding) {
		InputStream in = null;
		try {
			in = getInputStream(location);
			if (encoding == null) {
				return IOUtils.toString(in);
			} else {
				return IOUtils.toString(in, encoding);
			}
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	/**
	 * Get the contents of <code>s</code> as a list of <code>String's</code> one entry per line
	 */
	public static final List<String> readLinesFromString(String s) {
		Reader reader = getBufferedReaderFromString(s);
		return readLinesAndClose(reader);
	}

	public static final List<String> readLinesAndClose(InputStream in) {
		return readLinesAndClose(in, null);
	}

	public static final List<String> readLinesAndClose(InputStream in, String encoding) {
		Reader reader = null;
		try {
			reader = getBufferedReader(in, encoding);
			return readLinesAndClose(reader);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			IOUtils.closeQuietly(reader);
		}
	}

	public static final List<String> readLinesAndClose(Reader reader) {
		try {
			return IOUtils.readLines(reader);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			IOUtils.closeQuietly(reader);
		}
	}

	/**
	 * Get the contents of <code>file</code> as a list of <code>String's</code> one entry per line using the platform default encoding
	 */
	public static final List<String> readLines(File file) {
		return readLines(getCanonicalPath(file));
	}

	/**
	 * Get the contents of <code>location</code> as a list of <code>String's</code> one entry per line using the platform default encoding
	 */
	public static final List<String> readLines(String location) {
		return readLines(location, null);
	}

	/**
	 * Get the contents of <code>location</code> as a list of <code>String's</code> one entry per line using the encoding indicated.
	 */
	public static final List<String> readLines(String location, String encoding) {
		Reader reader = null;
		try {
			reader = getBufferedReader(location, encoding);
			return readLinesAndClose(reader);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			IOUtils.closeQuietly(reader);
		}
	}

	/**
	 * Return a <code>BufferedReader</code> for the location indicated using the platform default encoding.
	 */
	public static final BufferedReader getBufferedReader(String location) throws IOException {
		return getBufferedReader(location, null);
	}

	/**
	 * Return a <code>BufferedReader</code> for the location indicated using the encoding indicated.
	 */
	public static final BufferedReader getBufferedReader(String location, String encoding) throws IOException {
		try {
			InputStream in = getInputStream(location);
			return getBufferedReader(in, encoding);
		} catch (IOException e) {
			throw new IOException("Unexpected IO error", e);
		}
	}

	/**
	 * Return a <code>BufferedReader</code> that reads from <code>s</code>
	 */
	public static final BufferedReader getBufferedReaderFromString(String s) {
		return new BufferedReader(new StringReader(s));
	}

	/**
	 * Return a <code>Writer</code> that writes to <code>out</code> using the indicated encoding. <code>null</code> means use the platform's default encoding.
	 */
	public static final Writer getWriter(OutputStream out, String encoding) throws IOException {
		if (encoding == null) {
			return new BufferedWriter(new OutputStreamWriter(out));
		} else {
			return new BufferedWriter(new OutputStreamWriter(out, encoding));
		}
	}

	/**
	 * Return a <code>BufferedReader</code> that reads from <code>file</code> using the indicated encoding. <code>null</code> means use the platform's default encoding.
	 */
	public static final BufferedReader getBufferedReader(File file, String encoding) throws IOException {
		return getBufferedReader(FileUtils.openInputStream(file), encoding);
	}

	/**
	 * Return a <code>BufferedReader</code> that reads from <code>in</code> using the indicated encoding. <code>null</code> means use the platform's default encoding.
	 */
	public static final BufferedReader getBufferedReader(InputStream in, String encoding) throws IOException {
		if (encoding == null) {
			return new BufferedReader(new InputStreamReader(in));
		} else {
			return new BufferedReader(new InputStreamReader(in, encoding));
		}
	}

	/**
	 * Null safe method for determining if <code>location</code> is an existing file.
	 */
	public static final boolean isExistingFile(String location) {
		if (location == null) {
			return false;
		}
		File file = new File(location);
		return file.exists();
	}

	/**
	 * Null safe method for determining if <code>location</code> exists.
	 */
	public static final boolean exists(File file) {
		if (file == null) {
			return false;
		}
		String location = getCanonicalPath(file);
		if (isExistingFile(location)) {
			return true;
		} else {
			Resource resource = getResource(location);
			return resource.exists();
		}
	}

	public static void validateExists(List<String> locations) {
		StringBuilder sb = new StringBuilder();
		for (String location : locations) {
			if (!LocationUtils.exists(location)) {
				sb.append("Location [" + location + "] does not exist\n");
			}
		}
		if (sb.length() > 0) {
			throw new IllegalArgumentException(sb.toString());
		}
	}

	/**
	 * Null safe method for determining if <code>location</code> exists.
	 */
	public static final boolean exists(String location) {
		if (location == null) {
			return false;
		}
		if (isExistingFile(location)) {
			return true;
		} else {
			Resource resource = getResource(location);
			return resource.exists();
		}
	}

	/**
	 * Open an <code>InputStream</code> to <code>location</code>. If <code>location</code> is the path to an existing <code>File</code> on the local file system, a
	 * <code>FileInputStream</code> is returned. Otherwise Spring's resource loading framework is used to open an <code>InputStream</code> to <code>location</code>.
	 */
	public static final InputStream getInputStream(String location) throws IOException {
		if (isExistingFile(location)) {
			return FileUtils.openInputStream(new File(location));
		}
		Resource resource = getResource(location);
		return resource.getInputStream();
	}

	public static final Resource getResource(String location) {
		if (location == null) {
			return null;
		}
		ResourceLoader loader = new DefaultResourceLoader();
		return loader.getResource(location);
	}

	public static final String getFilename(String location) {
		if (location == null) {
			return null;
		}
		if (isExistingFile(location)) {
			return getFileQuietly(location).getName();
		} else {
			Resource resource = getResource(location);
			return resource.getFilename();
		}
	}

	public static final List<String> getAbsolutePaths(List<File> files) {
		List<String> results = new ArrayList<String>(files.size());

		for (File f : files) {
			results.add(f.getAbsolutePath());
		}

		return results;
	}

	public static final ComparisonResults getLocationListComparison(List<String> newLocations, List<String> originalLocations) {
		ComparisonResults result = new ComparisonResults();

		result.setAdded(new ArrayList<String>());
		result.setSame(new ArrayList<String>());
		result.setDeleted(new ArrayList<String>());

		for (String newLocation : newLocations) {
			if (originalLocations.contains(newLocation)) {
				// if a location is in both lists, add it to the "same" list
				result.getSame().add(newLocation);
			} else {
				// if a location is only in the new list, add it to the "added" list
				result.getAdded().add(newLocation);
			}
		}

		// the "deleted" list will contain all locations from the original list that are NOT in the new list
		result.getDeleted().addAll(originalLocations);
		result.getDeleted().removeAll(newLocations);

		return result;
	}

	public static String getChecksum(String location, String algorithm) {
		byte[] b = createChecksum(location, algorithm);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < b.length; i++) {
			sb.append(Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}

	public static byte[] createChecksum(String location, String algorithm) {
		InputStream in = null;
		try {
			in = LocationUtils.getInputStream(location);
			return createChecksum(in, algorithm);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	public static byte[] createChecksum(InputStream in, String algorithm) throws IOException {
		try {
			byte[] buffer = new byte[1024];
			MessageDigest complete = MessageDigest.getInstance(algorithm);
			int numRead;
			do {
				numRead = in.read(buffer);
				if (numRead > 0) {
					complete.update(buffer, 0, numRead);
				}
			} while (numRead != -1);
			IOUtils.closeQuietly(in);
			return complete.digest();
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("Unexpected message digest error", e);
		} catch (IOException e) {
			throw e;
		}
	}
}
