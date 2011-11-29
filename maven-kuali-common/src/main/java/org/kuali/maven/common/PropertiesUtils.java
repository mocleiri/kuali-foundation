package org.kuali.maven.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class PropertiesUtils {
	public Properties getProperties(List<String> locations) throws IOException {
		Properties properties = new Properties();
		for (String location : locations) {
			properties.putAll(getProperties(location));
		}
		return properties;
	}

	public Properties getEnvironmentProperties() {
		String prefix = "env.";
		Map<String, String> env = System.getenv();
		Properties properties = new Properties();
		for (Map.Entry<String, String> pair : env.entrySet()) {
			String key = prefix + pair.getKey();
			String value = pair.getValue();
			properties.setProperty(key, value);
		}
		return properties;
	}

	public Properties getProperties(String location) throws IOException {
		InputStream in = null;
		try {
			in = getInputStream(location);
			Properties properties = new Properties();
			if (isXml(location)) {
				properties.loadFromXML(in);
			} else {
				properties.load(in);
			}
			return properties;
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	public boolean isXml(String location) {
		return location.toLowerCase().endsWith(".xml");
	}

	public InputStream getInputStream(String location) throws IOException {
		File file = new File(location);
		if (file.exists()) {
			return new FileInputStream(file);
		}
		ResourceLoader loader = new DefaultResourceLoader();
		Resource resource = loader.getResource(location);
		if (!resource.exists()) {
			throw new IOException("Unable to locate " + location);
		}
		return resource.getInputStream();
	}

}
