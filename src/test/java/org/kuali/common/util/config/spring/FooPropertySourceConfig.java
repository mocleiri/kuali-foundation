/**
 * Copyright 2010-2013 The Kuali Foundation
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
package org.kuali.common.util.config.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @deprecated
 */
@Configuration
@PropertySource({ FooPropertySourceConfig.PROJECT_PROPERTIES, FooPropertySourceConfig.CONFIG_METADATA_PROPERTIES })
@Deprecated
public class FooPropertySourceConfig extends BasicPropertySourceConfig {

	public static final String META_CLASSPATH_PREFIX = "META-INF/org/kuali/common/kuali-util";
	public static final String PROJECT_PROPERTIES = META_CLASSPATH_PREFIX + "project.properties";
	public static final String CONFIG_METADATA_PROPERTIES = META_CLASSPATH_PREFIX + "/config/metadata.properties";
	public static final String[] PROPERTY_SOURCE_LOCATIONS = { PROJECT_PROPERTIES, CONFIG_METADATA_PROPERTIES };

}
