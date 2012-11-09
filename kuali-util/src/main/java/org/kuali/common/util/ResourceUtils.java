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
import org.apache.commons.lang.StringUtils;
import org.kuali.common.util.spring.ToStringContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.Assert;

public class ResourceUtils {

	private static final Logger logger = LoggerFactory.getLogger(ResourceUtils.class);

	public static final boolean deleteQuietly(String location) {
		return delete(location, true);
	}

	public static final boolean delete(String location, boolean quietly) {
		if (!quietly) {
			Assert.isTrue(exists(location), location + " does not exist");
			Assert.isTrue(isFile(location), location + " exists, but is not a file");
		}
		File file = new File(location);
		boolean deleted = file.delete();
		if (deleted) {
			return true;
		} else if (quietly) {
			logger.warn("Could not delete [{}]", location);
			return false;
		} else {
			throw new IllegalStateException("Could not delete " + location);
		}
	}

	public static final String toString(ToStringContext context) {
		Assert.notNull(context.getLocation());
		String s = ResourceUtils.toString(context.getLocation(), context.getEncoding());
		if (context.isDelete()) {
			ResourceUtils.delete(context.getLocation(), context.isDeleteQuietly());
		}
		return context.isTrim() ? s.trim() : s;
	}

	public static final String toString(String location) {
		return toString(location, null);
	}

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

	public static final List<String> readLinesFromString(String s) {
		InputStream in = new ByteArrayInputStream(s.getBytes());
		return readLines(in, null);
	}

	public static final List<String> readLines(String location) {
		return readLines(location, null);
	}

	public static final List<String> readLines(String location, String encoding) {
		InputStream in = null;
		try {
			in = getInputStream(location);
			return IOUtils.readLines(in, encoding);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	public static final List<String> readLines(InputStream in, String encoding) {
		try {
			return IOUtils.readLines(in, encoding);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	public static final BufferedReader getBufferedReader(String location) {
		return getBufferedReader(location, null);
	}

	public static final BufferedReader getBufferedReader(String location, String encoding) {
		InputStream in = getInputStream(location);
		return getBufferedReader(in, encoding);
	}

	public static final BufferedReader getBufferedReaderFromString(String s) {
		InputStream in = new ByteArrayInputStream(s.getBytes());
		return getBufferedReader(in, null);
	}

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

	public static final boolean isFile(String location) {
		File file = new File(location);
		return file.exists();
	}

	public static final boolean exists(String location) {
		if (isFile(location)) {
			return true;
		} else {
			ResourceLoader loader = new DefaultResourceLoader();
			Resource resource = loader.getResource(location);
			return resource.exists();
		}
	}

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
