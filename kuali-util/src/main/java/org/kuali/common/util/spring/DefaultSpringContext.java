/**
 * Copyright 2010-2012 The Kuali Foundation
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
package org.kuali.common.util.spring;

import java.io.File;
import java.util.List;
import java.util.Properties;

import org.kuali.common.util.property.Constants;
import org.kuali.common.util.property.GlobalPropertiesMode;
import org.springframework.util.PropertyPlaceholderHelper;

public class DefaultSpringContext implements SpringContext {

	PropertyPlaceholderHelper helper = Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER;
	GlobalPropertiesMode globalPropertiesMode = Constants.DEFAULT_GLOBAL_PROPERTIES_MODE;
	String encoding;
	String contextLocation;
	Properties properties;
	File workingDir;
	boolean filterContext;
	List<String> filterIncludes;
	List<String> filterExcludes;
	List<String> exportIncludes;
	List<String> exportExcludes;
	boolean exportProperties;
	File exportedPropertiesFile;
	String exportedPropertiesFileProperty;

	@Override
	public PropertyPlaceholderHelper getHelper() {
		return helper;
	}

	public void setHelper(PropertyPlaceholderHelper helper) {
		this.helper = helper;
	}

	@Override
	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	@Override
	public String getContextLocation() {
		return contextLocation;
	}

	public void setContextLocation(String contextLocation) {
		this.contextLocation = contextLocation;
	}

	@Override
	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	@Override
	public File getWorkingDir() {
		return workingDir;
	}

	public void setWorkingDir(File workingDir) {
		this.workingDir = workingDir;
	}

	@Override
	public boolean isFilterContext() {
		return filterContext;
	}

	public void setFilterContext(boolean filterContext) {
		this.filterContext = filterContext;
	}

	@Override
	public List<String> getFilterIncludes() {
		return filterIncludes;
	}

	public void setFilterIncludes(List<String> filterIncludes) {
		this.filterIncludes = filterIncludes;
	}

	@Override
	public List<String> getFilterExcludes() {
		return filterExcludes;
	}

	public void setFilterExcludes(List<String> filterExcludes) {
		this.filterExcludes = filterExcludes;
	}

	@Override
	public List<String> getExportIncludes() {
		return exportIncludes;
	}

	public void setExportIncludes(List<String> exportIncludes) {
		this.exportIncludes = exportIncludes;
	}

	@Override
	public List<String> getExportExcludes() {
		return exportExcludes;
	}

	public void setExportExcludes(List<String> exportExcludes) {
		this.exportExcludes = exportExcludes;
	}

	@Override
	public boolean isExportProperties() {
		return exportProperties;
	}

	public void setExportProperties(boolean exportProperties) {
		this.exportProperties = exportProperties;
	}

	@Override
	public File getExportedPropertiesFile() {
		return exportedPropertiesFile;
	}

	public void setExportedPropertiesFile(File exportedPropertiesFile) {
		this.exportedPropertiesFile = exportedPropertiesFile;
	}

	@Override
	public String getExportedPropertiesFileProperty() {
		return exportedPropertiesFileProperty;
	}

	public void setExportedPropertiesFileProperty(String exportedPropertiesFileProperty) {
		this.exportedPropertiesFileProperty = exportedPropertiesFileProperty;
	}

	@Override
    public GlobalPropertiesMode getGlobalPropertiesMode() {
		return globalPropertiesMode;
	}

	public void setGlobalPropertiesMode(GlobalPropertiesMode globalPropertiesMode) {
		this.globalPropertiesMode = globalPropertiesMode;
	}

}
