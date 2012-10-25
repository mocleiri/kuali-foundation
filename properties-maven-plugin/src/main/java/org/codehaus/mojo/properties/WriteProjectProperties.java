/**
 * Copyright 2009-2012 The Kuali Foundation
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
package org.codehaus.mojo.properties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.codehaus.plexus.util.StringUtils;
import org.springframework.util.PropertyPlaceholderHelper;

/**
 * Write project properties to a file.
 *
 * @author Jeff Caddel
 *
 * @goal write-project-properties
 */
public class WriteProjectProperties extends AbstractWritePropertiesMojo {

	/**
	 * If true, the plugin will include system properties when writing the properties file. System properties override both environment
	 * variables and project properties.
	 *
	 * @parameter default-value="false" expression="${properties.includeSystemProperties}"
	 */
	private boolean includeSystemProperties;

	/**
	 * If true, the plugin will include environment variables when writing the properties file. Environment variables are prefixed with
	 * "env". Environment variables override project properties.
	 *
	 * @parameter default-value="false" expression="${properties.includeEnvironmentVariables}"
	 */
	private boolean includeEnvironmentVariables;

	/**
	 * Comma separated set of properties to exclude when writing the properties file
	 *
	 * @parameter expression="${properties.exclude}"
	 */
	private String exclude;

	/**
	 * Comma separated set of properties to write to the properties file. If provided, only the properties matching those supplied here will
	 * be written to the properties file.
	 *
	 * @parameter expression="${properties.include}"
	 */
	private String include;

	/**
	 * If true placeholders are resolved before writing properties to the file
	 *
	 * @parameter expression="${properties.resolvePlaceholders}"
	 */
	private boolean resolvePlaceholders;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		Properties properties = new Properties();
		// Add project properties
		properties.putAll(project.getProperties());
		if (includeEnvironmentVariables) {
			// Add environment variables, overriding any existing properties with the same key
			properties.putAll(getEnvironmentVariables());
		}
		if (includeSystemProperties) {
			// Add system properties, overriding any existing properties with the same key
			properties.putAll(System.getProperties());
		}

		// Resolve placeholders
		if (resolvePlaceholders) {
			properties = getResolvedProperties(properties);
		}

		// Remove properties as appropriate
		trim(properties, exclude, include);

		getLog().info("Creating " + outputFile);
		writeProperties(this.outputFile, properties, this.outputStyle);
	}

	protected Properties getResolvedProperties(Properties props) {
		PropertyPlaceholderHelper pph = new PropertyPlaceholderHelper("${", "}");
		List<String> keys = new ArrayList<String>(props.stringPropertyNames());
		Collections.sort(keys);
		Properties newProps = new Properties();
		for (String key : keys) {
			String originalValue = props.getProperty(key);
			String resolvedValue = pph.replacePlaceholders(originalValue, props);
			newProps.setProperty(key, resolvedValue);
		}
		return newProps;

	}

	protected static Properties getEnvironmentVariables() {
		String prefix = "env";
		Map<String, String> map = System.getenv();
		Properties props = new Properties();
		for (String key : map.keySet()) {
			String newKey = prefix + "." + key;
			String value = map.get(key);
			props.setProperty(newKey, value);
		}
		return props;
	}

	protected void trim(Properties properties, String excludeCSV, String includeCSV) {
		List<String> omitKeys = ReadPropertiesMojo.getListFromCSV(excludeCSV);
		for (String key : omitKeys) {
			properties.remove(key);
		}
		if (StringUtils.isBlank(includeCSV)) {
			return;
		}
		List<String> includeKeys = ReadPropertiesMojo.getListFromCSV(includeCSV);
		Set<String> keys = properties.stringPropertyNames();
		for (String key : keys) {
			if (!includeKeys.contains(key)) {
				properties.remove(key);
			}
		}
	}

	public boolean isIncludeSystemProperties() {
		return includeSystemProperties;
	}

	public void setIncludeSystemProperties(boolean includeSystemProperties) {
		this.includeSystemProperties = includeSystemProperties;
	}

	public boolean isIncludeEnvironmentVariables() {
		return includeEnvironmentVariables;
	}

	public void setIncludeEnvironmentVariables(boolean includeEnvironmentVariables) {
		this.includeEnvironmentVariables = includeEnvironmentVariables;
	}

	public String getExclude() {
		return exclude;
	}

	public void setExclude(String exclude) {
		this.exclude = exclude;
	}

	public String getInclude() {
		return include;
	}

	public void setInclude(String include) {
		this.include = include;
	}

	public boolean isResolvePlaceholders() {
		return resolvePlaceholders;
	}

	public void setResolvePlaceholders(boolean resolvePlaceholders) {
		this.resolvePlaceholders = resolvePlaceholders;
	}
}
