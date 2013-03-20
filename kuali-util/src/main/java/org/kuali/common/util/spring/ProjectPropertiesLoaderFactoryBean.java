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

import java.util.Map;
import java.util.Properties;

import org.kuali.common.util.Assert;
import org.kuali.common.util.property.ProjectPropertiesContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;

/**
 * 
 */
public class ProjectPropertiesLoaderFactoryBean implements FactoryBean<Properties> {

	private static final Logger logger = LoggerFactory.getLogger(ProjectPropertiesLoaderFactoryBean.class);

	String location;
	boolean singleton = true;

	@Override
	public Properties getObject() throws Exception {
		Assert.hasText(location, "location has no text");
		Map<String, ProjectPropertiesContext> beans = SpringUtils.getAllBeans(location, ProjectPropertiesContext.class);
		logger.info("Located {} property contexts", beans.size());
		return null;
	}

	@Override
	public Class<Properties> getObjectType() {
		return Properties.class;
	}

	@Override
	public boolean isSingleton() {
		return singleton;
	}

	public void setSingleton(boolean singleton) {
		this.singleton = singleton;
	}

}
