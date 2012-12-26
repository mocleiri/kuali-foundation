/**
 * Copyright 2010-2012 The Kuali Foundation
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
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class LocationUtils {

	private static final String FILE_PREFIX = "file:";
	private static final String BACK_SLASH = "\\";
	private static final String FORWARD_SLASH = "/";
	private static final String SLASH_DOT_SLASH = "/./";
	private static final String SLASH_DOTDOT_SLASH = "/../";

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
		if (StringUtils.contains(normalizedPath, SLASH_DOT_SLASH)) {
			throw new IllegalArgumentException("[" + originalPath + "] could not be normalized. Normalized path [" + normalizedPath + "]");
		}
		if (StringUtils.contains(normalizedPath, SLASH_DOTDOT_SLASH)) {
			throw new IllegalArgumentException("[" + originalPath + "] could not be normalized. Normalized path [" + normalizedPath + "]");
		}
	}

	/**
	 * Resolve and remove <code>..</code> and <code>.</code> from <code>absolutePath</code> after converting any back slashes to forward
	 * slashes
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
	 * Null safe method to unconditionally attempt to delete <code>filename</code> without throwing an exception. If <code>filename</code>
	 * is a directory, delete it and all sub-directories.
	 */
	public static final boolean deleteFileQuietly(String filename) {
		File file = getFileQuietly(filename);
		return FileUtils.deleteQuietly(file);
	}

	/**
	 * Null safe method for getting a <code>File</code> handle from <code>filename</code>. If <code>filename</code> is null, null is
	 * returned.
	 */
	public static final File getFileQuietly(String filename) {
		if (filename == null) {
			return null;
		} else {
			return new File(filename);
		}
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
	 * Return a <code>Writer</code> that writes to <code>out</code> using the indicated encoding. <code>null</code> means use the platform's
	 * default encoding.
	 */
	public static final Writer getWriter(OutputStream out, String encoding) throws IOException {
		if (encoding == null) {
			return new BufferedWriter(new OutputStreamWriter(out));
		} else {
			return new BufferedWriter(new OutputStreamWriter(out, encoding));
		}
	}

	/**
	 * Return a <code>BufferedReader</code> that reads from <code>file</code> using the indicated encoding. <code>null</code> means use the
	 * platform's default encoding.
	 */
	public static final BufferedReader getBufferedReader(File file, String encoding) throws IOException {
		return getBufferedReader(FileUtils.openInputStream(file), encoding);
	}

	/**
	 * Return a <code>BufferedReader</code> that reads from <code>in</code> using the indicated encoding. <code>null</code> means use the
	 * platform's default encoding.
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
	 * Open an <code>InputStream</code> to <code>location</code>. If <code>location</code> is the path to an existing <code>File</code> on
	 * the local file system, a <code>FileInputStream</code> is returned. Otherwise Spring's resource loading framework is used to open an
	 * <code>InputStream</code> to <code>location</code>.
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

}
