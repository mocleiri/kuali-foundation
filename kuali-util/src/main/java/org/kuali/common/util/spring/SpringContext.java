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

import org.kuali.common.util.property.GlobalPropertiesMode;
import org.springframework.util.PropertyPlaceholderHelper;

public interface SpringContext {

	GlobalPropertiesMode getGlobalPropertiesMode();

	PropertyPlaceholderHelper getHelper();

	String getEncoding();

	String getContextLocation();

	File getWorkingDir();

	boolean isFilterContext();

	List<String> getFilterIncludes();

	List<String> getFilterExcludes();

	List<String> getExportIncludes();

	List<String> getExportExcludes();

	boolean isExportProperties();

	File getExportPropertiesFile();

	String getExportPropertiesFileProperty();

	List<Properties> getPropertySources();

}