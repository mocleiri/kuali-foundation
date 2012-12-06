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
import java.util.List;
import java.util.Properties;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.Constants;
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
	 * List of properties to include
	 *
	 * @parameter
	 */
	private List<String> includes;

	/**
	 * List of properties to exclude
	 *
	 * @parameter
	 */
	private List<String> excludes;

	/**
	 * If true placeholders are resolved before writing properties to the file
	 *
	 * @parameter expression="${properties.resolvePlaceholders}" default-value="false"
	 */
	private boolean resolvePlaceholders;

	/**
	 * @parameter expression="${properties.includeStandardMavenProperties}" default-value="false"
	 */
	private boolean includeStandardMavenProperties;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		Properties properties = new Properties();

		// Add project properties
		properties.putAll(project.getProperties());

		if (includeStandardMavenProperties) {
			properties.putAll(getStandardMavenProperties(project));
		}

		// Add environment variables, overriding any existing properties with the same key
		if (includeEnvironmentVariables) {
			properties.putAll(PropertyUtils.getEnvAsProperties());
		}

		// Add system properties, overriding any existing properties with the same key
		if (includeSystemProperties) {
			properties.putAll(System.getProperties());
		}

		List<String> includeList = getList(includes, include);
		List<String> excludeList = getList(excludes, exclude);

		override(properties, includeList);

		// Resolve placeholders
		if (resolvePlaceholders) {
			properties = getResolvedProperties(properties);
		}

		// Remove properties as appropriate
		PropertyUtils.trim(properties, includeList, excludeList);

		getLog().info("Creating " + outputFile);

		// Save the properties to a file
		writeProperties(this.outputFile, properties, this.outputStyle, this.prefix);
	}

	protected void override(Properties properties, List<String> includes) {
		List<String> keys = getKeys(properties, includes);
		Properties global = PropertyUtils.getGlobalProperties(properties);
		properties.clear();
		for (String key : keys) {
			String value = global.getProperty(key);
			if (value != null) {
				properties.setProperty(key, value);
			}
		}
	}

	protected List<String> getKeys(Properties properties, List<String> keys) {
		List<String> newKeys = PropertyUtils.getSortedKeys(properties);
		for (String key : keys) {
			if (!newKeys.contains(key)) {
				newKeys.add(key);
			}
		}
		return newKeys;
	}

	protected Properties getResolvedProperties(Properties props) {
		PropertyPlaceholderHelper pph = Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER;
		List<String> keys = PropertyUtils.getSortedKeys(props);
		Properties newProps = new Properties();
		for (String key : keys) {
			String originalValue = props.getProperty(key);
			String resolvedValue = pph.replacePlaceholders(originalValue, props);
			newProps.setProperty(key, resolvedValue);
		}
		return newProps;

	}

	protected List<String> getList(List<String> list, String csv) {
		List<String> newList = new ArrayList<String>();
		if (!CollectionUtils.isEmpty(list)) {
			list.addAll(list);
		}
		List<String> csvList = CollectionUtils.getTrimmedListFromCSV(csv);
		for (String element : csvList) {
			if (!list.contains(element)) {
				newList.add(element);
			}
		}
		return newList;
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

	public boolean isIncludeStandardMavenProperties() {
		return includeStandardMavenProperties;
	}

	public void setIncludeStandardMavenProperties(boolean includeStandardMavenProperties) {
		this.includeStandardMavenProperties = includeStandardMavenProperties;
	}

	public List<String> getIncludes() {
		return includes;
	}

	public void setIncludes(List<String> includes) {
		this.includes = includes;
	}

}
