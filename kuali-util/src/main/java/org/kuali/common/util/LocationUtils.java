package org.kuali.common.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
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

	public static final List<String> getLocationsFromList(String locationList) {
		return getLocations(Collections.singletonList(locationList));
	}

	public static final List<String> getLocations(List<String> locationLists) {
		List<String> locations = new ArrayList<String>();
		for (String locationList : locationLists) {
			List<String> lines = readLines(locationList);
			locations.addAll(lines);
		}
		return locations;
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
	 * Convert the contents of <code>location</code> into a <code>String</code> using the platform default encoding.
	 */
	public static final String toString(String location) {
		return toString(location, null);
	}

	/**
	 * Convert the contents of <code>location</code> into a <code>String</code> using the encoding indicated.
	 */
	public static final String toString(String location, String encoding) {
		InputStream in = null;
		try {
			in = getInputStream(location);
			return IOUtils.toString(in, encoding);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	/**
	 * Get the contents of <code>s</code> as a list of <code>String</code> one entry per line
	 */
	public static final List<String> readLinesFromString(String s) {
		return readLinesFromString(s, null);
	}

	/**
	 * Get the contents of <code>s</code> as a list of <code>String</code> one entry per line
	 */
	public static final List<String> readLinesFromString(String s, String encoding) {
		InputStream in = new ByteArrayInputStream(s.getBytes());
		return readLines(in, encoding);
	}

	/**
	 * Get the contents of <code>location</code> as a list of <code>String</code> one entry per line using the platform default encoding
	 */
	public static final List<String> readLines(String location) {
		return readLines(location, null);
	}

	/**
	 * Get the contents of <code>location</code> as a list of <code>String</code> one entry per line using the encoding indicated.
	 */
	public static final List<String> readLines(String location, String encoding) {
		InputStream in = null;
		try {
			in = getInputStream(location);
			return readLines(in, encoding);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	/**
	 * Get the contents of <code>in</code> as a list of <code>String</code> one entry per line using the encoding indicated.
	 */
	public static final List<String> readLines(InputStream in, String encoding) {
		try {
			return IOUtils.readLines(in, encoding);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	/**
	 * Return a <code>BufferedReader</code> for the location indicated using the platform default encoding.
	 */
	public static final BufferedReader getBufferedReader(String location) {
		return getBufferedReader(location, null);
	}

	/**
	 * Return a <code>BufferedReader</code> for the location indicated using the encoding indicated.
	 */
	public static final BufferedReader getBufferedReader(String location, String encoding) {
		InputStream in = getInputStream(location);
		return getBufferedReader(in, encoding);
	}

	/**
	 * Return a <code>BufferedReader</code> that reads from <code>s</code>
	 */
	public static final BufferedReader getBufferedReaderFromString(String s) {
		return getBufferedReaderFromString(s, null);
	}

	/**
	 * Return a <code>BufferedReader</code> that reads from <code>s</code>
	 */
	public static final BufferedReader getBufferedReaderFromString(String s, String encoding) {
		InputStream in = new ByteArrayInputStream(s.getBytes());
		return getBufferedReader(in, encoding);
	}

	/**
	 * Return a <code>Writer</code> that writes to <code>out</code> using the indicated encoding. <code>null</code> means use the platform's
	 * default encoding.
	 */
	public static final Writer getWriter(OutputStream out, String encoding) {
		try {
			if (StringUtils.isBlank(encoding)) {
				return new BufferedWriter(new OutputStreamWriter(out));
			} else {
				return new BufferedWriter(new OutputStreamWriter(out, encoding));
			}
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		}
	}

	/**
	 * Return a <code>BufferedReader</code> that reads from <code>file</code> using the indicated encoding. <code>null</code> means use the
	 * platform's default encoding.
	 */
	public static final BufferedReader getBufferedReader(File file, String encoding) {
		try {
			return getBufferedReader(FileUtils.openInputStream(file), encoding);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		}
	}

	/**
	 * Return a <code>BufferedReader</code> that reads from <code>in</code> using the indicated encoding. <code>null</code> means use the
	 * platform's default encoding.
	 */
	public static final BufferedReader getBufferedReader(InputStream in, String encoding) {
		try {
			if (StringUtils.isBlank(encoding)) {
				return new BufferedReader(new InputStreamReader(in));
			} else {
				return new BufferedReader(new InputStreamReader(in, encoding));
			}
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		}
	}

	/**
	 * Null safe method for determining if <code>location</code> is an existing file.
	 */
	public static final boolean isFile(String location) {
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
		if (isFile(location)) {
			return true;
		} else {
			ResourceLoader loader = new DefaultResourceLoader();
			Resource resource = loader.getResource(location);
			return resource.exists();
		}
	}

	/**
	 * Open an <code>InputStream</code> to the indicated location.
	 */
	public static final InputStream getInputStream(String location) {
		try {
			if (isFile(location)) {
				return new FileInputStream(location);
			}
			ResourceLoader loader = new DefaultResourceLoader();
			Resource resource = loader.getResource(location);
			return resource.getInputStream();
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		}
	}
}
