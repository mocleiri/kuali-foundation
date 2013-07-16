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

import java.util.Properties;

import org.springframework.context.annotation.Configuration;

/**
 * Extend this class in order to have project properties always "win" over properties loaded from other locations.
 */
@Configuration
public abstract class ProjectPropertySourceConfig extends BasicPropertySourceConfig {

	/**
	 * Return properties for the current project.
	 */
	protected abstract Properties getProjectProperties();

	@Override
	protected Properties getOverrides() {
		return getProjectProperties();
	}

}
