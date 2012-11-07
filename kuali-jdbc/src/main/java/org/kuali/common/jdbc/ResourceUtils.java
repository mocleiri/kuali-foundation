package org.kuali.common.jdbc;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class ResourceUtils {

	private static final String XML_EXTENSION = ".xml";

	public static final Properties getProperties(String location) throws IOException {
		InputStream in = null;
		try {
			in = getInputStream(location);
			Properties properties = new Properties();
			boolean xml = location.toLowerCase().endsWith(XML_EXTENSION);
			if (xml) {
				properties.loadFromXML(in);
			} else {
				properties.load(in);
			}
			return properties;
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
		return getBufferedReaderFromString(s, null);
	}

	public static final BufferedReader getBufferedReaderFromString(String s, String encoding) {
		InputStream in = new ByteArrayInputStream(s.getBytes());
		return getBufferedReader(in, encoding);
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

	public static final InputStream getInputStream(String location) {
		try {
			File file = new File(location);
			if (file.exists()) {
				return new FileInputStream(file);
			}
			ResourceLoader loader = new DefaultResourceLoader();
			Resource resource = loader.getResource(location);
			return resource.getInputStream();
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		}
	}
}
