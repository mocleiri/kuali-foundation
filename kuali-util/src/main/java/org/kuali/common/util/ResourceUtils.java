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
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.Assert;

public class ResourceUtils {

	private static final Logger logger = LoggerFactory.getLogger(ResourceUtils.class);

	/**
	 * Attempt to delete <code>location</code>.
	 */
	public static final boolean delete(String location) {
		return delete(location, false);
	}

	/**
	 * Null safe method to unconditionally attempt to delete <code>location</code> without throwing an exception.
	 */
	public static final boolean deleteQuietly(String location) {
		return delete(location, true);
	}

	/**
	 * Attempt to delete <code>location</code>. If <code>quietly</code> is false, this method either returns <code>true</code> or throws an
	 * exception.
	 *
	 * If <code>quietly</code> is false:<br>
	 *
	 * <code>IllegalArgumentException</code> is thrown when:<br>
	 * <code>location</code> is null.<br>
	 * <code>location</code> does not exist.<br>
	 * <code>location</code> is not an existing file.<br>
	 *
	 * <code>SecurityException</code> is thrown if <code>SecurityManager</code> prevents the file from being deleted.
	 */
	public static final boolean delete(String location, boolean quietly) {
		if (quietly) {
			return deleteQuietly(getFileQuietly(location));
		} else {
			// Location can't be null
			Assert.notNull(location, "location must not be null");
			// Location must exist
			Assert.isTrue(exists(location), location + " does not exist");
			// Location must be an existing file
			Assert.isTrue(isFile(location), location + " exists, but is not a file");
			// Try and delete the file (might throw SecurityException)
			return new File(location).delete();
		}
	}

	/**
	 * Null safe method for getting a handle to a <code>file</code>. If <code>location</code> is null, null is returned.
	 */
	public static final File getFileQuietly(String location) {
		if (location == null) {
			return null;
		} else {
			return new File(location);
		}
	}

	/**
	 * Null safe method for attempting to unconditionally delete <code>file</code> without throwing an exception.
	 */
	public static final boolean deleteQuietly(File file) {
		// Return false for null
		if (file == null) {
			return false;
		}
		try {
			// Attempt to delete
			return file.delete();
		} catch (SecurityException e) {
			// Warn, but do not throw an exception
			logger.warn("Unable to delete [{}] {}", file.getAbsolutePath(), e.getMessage());
			return false;
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
		InputStream in = new ByteArrayInputStream(s.getBytes());
		return readLines(in, null);
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
		return getBufferedReader(s, null);
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
	 * Null safe method for determining if <code>location</code> is an existing location.
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
