package org.kuali.common.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
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

	public static final InputStream getInputStream(String location) throws IOException {
		File file = new File(location);
		if (file.exists()) {
			return new FileInputStream(file);
		}
		ResourceLoader loader = new DefaultResourceLoader();
		Resource resource = loader.getResource(location);
		return resource.getInputStream();
	}

	public static final List<String> getLines(String location) throws IOException {
		return getLines(location, null);
	}

	public static final List<String> getLines(String location, String encoding) throws IOException {
		InputStream in = null;
		try {
			in = getInputStream(location);
			return IOUtils.readLines(in, encoding);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}
}
