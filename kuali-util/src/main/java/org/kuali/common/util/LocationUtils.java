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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class LocationUtils {

	public static final List<String> getLocations(String location, LocationType type, CharSequence encoding) {
		switch (type) {
		case LOCATION:
			return Collections.singletonList(location);
		case LOCATIONLIST:
			return getLocations(location, encoding);
		default:
			throw new IllegalArgumentException(type + " is unknown");
		}
	}

	public static final List<String> getLocations(String location, LocationType type) {
		return getLocations(location, type, null);
	}

	public static final List<String> getLocations(String locationListing) {
		return getLocations(Collections.singletonList(locationListing), null);
	}

	public static final List<String> getLocations(String locationListing, CharSequence encoding) {
		return getLocations(Collections.singletonList(locationListing), encoding);
	}

	public static final List<String> getLocations(List<String> locationListings) {
		return getLocations(locationListings, null);
	}

	public static final List<String> getLocations(List<String> locationListings, CharSequence encoding) {
		List<String> locations = new ArrayList<String>();
		for (String locationListing : locationListings) {
			List<String> lines = readLines(locationListing, encoding);
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
	public static final boolean deleteFileQuietly(CharSequence filename) {
		File file = getFileQuietly(filename);
		return FileUtils.deleteQuietly(file);
	}

	/**
	 * Null safe method for getting a <code>File</code> handle from <code>filename</code>. If <code>filename</code> is null, null is
	 * returned.
	 */
	public static final File getFileQuietly(CharSequence filename) {
		if (filename == null) {
			return null;
		} else {
			return new File(filename.toString());
		}
	}

	/**
	 * Get the contents of <code>location</code> as a <code>String</code> using the platform's default character encoding.
	 */
	public static final String toString(CharSequence location) {
		return toString(location, null);
	}

	/**
	 * Get the contents of <code>location</code> as a <code>String</code> using the specified character encoding.
	 */
	public static final String toString(CharSequence location, CharSequence encoding) {
		InputStream in = null;
		try {
			in = getInputStream(location);
			if (encoding == null) {
				return IOUtils.toString(in);
			} else {
				return IOUtils.toString(in, encoding.toString());
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

	public static final List<String> readLinesAndClose(Reader reader) {
		try {
			return IOUtils.readLines(reader);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			IOUtils.closeQuietly(reader);
		}
	}

	public static final List<String> readLinesAndClose(InputStream in, CharSequence encoding) {
		Reader reader = null;
		try {
			reader = getBufferedReader(in, encoding);
			return IOUtils.readLines(reader);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			IOUtils.closeQuietly(reader);
		}
	}

	/**
	 * Get the contents of <code>location</code> as a list of <code>String's</code> one entry per line using the platform default encoding
	 */
	public static final List<String> readLines(CharSequence location) {
		return readLines(location, null);
	}

	/**
	 * Get the contents of <code>location</code> as a list of <code>String's</code> one entry per line using the encoding indicated.
	 */
	public static final List<String> readLines(CharSequence location, CharSequence encoding) {
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
	public static final BufferedReader getBufferedReader(CharSequence location) throws IOException {
		return getBufferedReader(location, null);
	}

	/**
	 * Return a <code>BufferedReader</code> for the location indicated using the encoding indicated.
	 */
	public static final BufferedReader getBufferedReader(CharSequence location, CharSequence encoding) throws IOException {
		InputStream in = null;
		try {
			in = getInputStream(location);
			return getBufferedReader(in, encoding);
		} catch (IOException e) {
			throw new IOException("Unexpected IO error");
		} finally {
			IOUtils.closeQuietly(in);
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
	public static final Writer getWriter(OutputStream out, CharSequence encoding) throws IOException {
		if (encoding == null) {
			return new BufferedWriter(new OutputStreamWriter(out));
		} else {
			return new BufferedWriter(new OutputStreamWriter(out, encoding.toString()));
		}
	}

	/**
	 * Return a <code>BufferedReader</code> that reads from <code>file</code> using the indicated encoding. <code>null</code> means use the
	 * platform's default encoding.
	 */
	public static final BufferedReader getBufferedReader(File file, CharSequence encoding) throws IOException {
		return getBufferedReader(FileUtils.openInputStream(file), encoding);
	}

	/**
	 * Return a <code>BufferedReader</code> that reads from <code>in</code> using the indicated encoding. <code>null</code> means use the
	 * platform's default encoding.
	 */
	public static final BufferedReader getBufferedReader(InputStream in, CharSequence encoding) throws IOException {
		if (encoding == null) {
			return new BufferedReader(new InputStreamReader(in));
		} else {
			return new BufferedReader(new InputStreamReader(in, encoding.toString()));
		}
	}

	/**
	 * Null safe method for determining if <code>location</code> is an existing file.
	 */
	public static final boolean isExistingFile(CharSequence location) {
		if (location == null) {
			return false;
		}
		File file = new File(location.toString());
		return file.exists();
	}

	/**
	 * Null safe method for determining if <code>location</code> exists.
	 */
	public static final boolean exists(CharSequence location) {
		if (location == null) {
			return false;
		}
		if (isExistingFile(location)) {
			return true;
		} else {
			ResourceLoader loader = new DefaultResourceLoader();
			Resource resource = loader.getResource(location.toString());
			return resource.exists();
		}
	}

	/**
	 * Open an <code>InputStream</code> to the indicated location.
	 */
	public static final InputStream getInputStream(CharSequence location) throws IOException {
		if (isExistingFile(location)) {
			return FileUtils.openInputStream(new File(location.toString()));
		}
		ResourceLoader loader = new DefaultResourceLoader();
		Resource resource = loader.getResource(location.toString());
		return resource.getInputStream();
	}
}
