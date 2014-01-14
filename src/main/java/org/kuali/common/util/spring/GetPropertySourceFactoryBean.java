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
package org.kuali.common.util.spring;

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.Assert;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.core.env.PropertySource;

/**
 * @deprecated
 */
@Deprecated
public class GetPropertySourceFactoryBean implements FactoryBean<PropertySource<?>> {

	org.kuali.common.util.ProjectContext project;
	List<org.kuali.common.util.ProjectContext> others;
	Properties properties;

	@Override
	public PropertySource<?> getObject() {
		Assert.notNull(project, "project is null");
		return SpringUtils.getGlobalPropertySource(project, others, properties);
	}

	@Override
	public Class<PropertySource<?>> getObjectType() {
		return null;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	public org.kuali.common.util.ProjectContext getProject() {
		return project;
	}

	public void setProject(org.kuali.common.util.ProjectContext project) {
		this.project = project;
	}

	public List<org.kuali.common.util.ProjectContext> getOthers() {
		return others;
	}

	public void setOthers(List<org.kuali.common.util.ProjectContext> others) {
		this.others = others;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

}
