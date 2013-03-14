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
package org.kuali.common.util.property.processor;

import java.util.Properties;

import org.kuali.common.util.Mode;
import org.kuali.common.util.Project;
import org.kuali.common.util.property.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultOrgProcessor implements PropertyProcessor {
	private static final Logger logger = LoggerFactory.getLogger(DefaultOrgProcessor.class);

	Mode propertyOverwriteMode = Constants.DEFAULT_PROPERTY_OVERWRITE_MODE;

	String orgIdProperty = "project.orgId";
	String orgCodeProperty = "project.orgId.code";
	String orgPathProperty = "project.orgId.path";
	String groupIdProperty = "project.groupId";

	@Override
	public void process(Properties properties) {
	}

	protected Project getProject(Properties properties) {
		Project project = new Project();
		project.setOrgId(properties.getProperty("project.orgId"));
		project.setOrgCode(properties.getProperty("project.orgId.code"));
		project.setOrgPath(properties.getProperty("project.orgId.path"));
		project.setOrgId(properties.getProperty("project.orgId"));
		return project;
	}

}
