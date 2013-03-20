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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.kuali.common.util.Assert;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.ProjectPropertiesContext;
import org.kuali.common.util.property.PropertiesLoaderContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;

/**
 * 
 */
public class ProjectPropertiesLoaderFactoryBean implements FactoryBean<Properties> {

	private static final Logger logger = LoggerFactory.getLogger(ProjectPropertiesLoaderFactoryBean.class);

	List<String> locations;
	List<String> gavs;
	boolean singleton = true;

	@Override
	public Properties getObject() throws Exception {
		long start = System.currentTimeMillis();
		Assert.notNull(locations, "locations is null");
		Map<String, ProjectPropertiesContext> beans = SpringUtils.getAllBeans(locations, ProjectPropertiesContext.class);
		logger.info("Located {} property contexts", beans.size());
		List<ProjectPropertiesContext> list = new ArrayList<ProjectPropertiesContext>();
		for (ProjectPropertiesContext bean : beans.values()) {
			list.add(bean);
		}
		Properties properties = new Properties();
		for (ProjectPropertiesContext ppc : list) {
			for (PropertiesLoaderContext ctx : ppc.getLoaderContexts()) {
				Properties combined = PropertyUtils.combine(properties, ctx.getProperties());
				ctx.setProperties(combined);
				Properties loaded = PropertyUtils.load(ctx);
				properties.putAll(loaded);
			}
		}
		String elapsed = FormatUtils.getTime(System.currentTimeMillis() - start);
		logger.info("Located {} properties.  Total time: {}", properties.size(), elapsed);
		return properties;
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

	public List<String> getLocations() {
		return locations;
	}

	public void setLocations(List<String> locations) {
		this.locations = locations;
	}

	public List<String> getGavs() {
		return gavs;
	}

	public void setGavs(List<String> gavs) {
		this.gavs = gavs;
	}

}
