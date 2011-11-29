package org.kuali.maven.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.codehaus.plexus.util.StringUtils;
import org.springframework.util.PropertyPlaceholderHelper;

public class PropertiesUtils {
	PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper("${", "}");
	ResourceUtils resourceUtils = new ResourceUtils();

	public String[] getTokens(String csv) {
		return getTokens(csv, true);
	}

	public String[] getTokens(String csv, boolean trim) {
		String[] tokens = StringUtils.split(csv, ",");
		for (String token : tokens) {
			token = trim ? token.trim() : token;
		}
		return tokens;
	}

	public String getResolvedValue(String value, Properties properties) {
		return helper.replacePlaceholders(value, properties);
	}

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
			in = resourceUtils.getInputStream(location);
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
}
