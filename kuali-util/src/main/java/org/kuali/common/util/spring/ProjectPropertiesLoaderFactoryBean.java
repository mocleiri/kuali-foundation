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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.kuali.common.util.Assert;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.ProjectProperties;
import org.kuali.common.util.property.ProjectPropertiesComparator;
import org.kuali.common.util.property.PropertiesContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.util.CollectionUtils;

/**
 * 
 */
public class ProjectPropertiesLoaderFactoryBean implements FactoryBean<Properties> {

	private static final Logger logger = LoggerFactory.getLogger(ProjectPropertiesLoaderFactoryBean.class);

	List<String> locations;
	boolean singleton = true;
	ProjectPropertiesComparator comparator;

	@Override
	public Properties getObject() {
		Assert.isFalse(CollectionUtils.isEmpty(locations), "locations is empty");
		long start = System.currentTimeMillis();
		List<ProjectProperties> pps = new ArrayList<ProjectProperties>();
		// Extract any ProjectProperties beans anywhere in the context
		for (String location : locations) {
			Map<String, ProjectProperties> beans = SpringUtils.getAllBeans(location, ProjectProperties.class);
			logger.info("Located {} sets of project properties", beans.size());

			List<ProjectProperties> list = new ArrayList<ProjectProperties>();
			// Add them to a list
			for (ProjectProperties bean : beans.values()) {
				list.add(bean);
			}
			// Sort them by sequence (only relevant if there is more than one which there typically is not)
			pps.addAll(list);
		}

		// Cycle through the list we have adding in properties as we go
		Properties properties = new Properties();
		for (ProjectProperties pp : pps) {
			PropertiesContext ctx = pp.getPropertiesContext();
			Properties combined = PropertyUtils.combine(properties, ctx.getProperties());
			ctx.setProperties(combined);
			Properties loaded = PropertyUtils.load(ctx);
			properties.putAll(loaded);
		}
		String elapsed = FormatUtils.getTime(System.currentTimeMillis() - start);
		logger.info("Loaded {} properties.  Total time: {}", properties.size(), elapsed);
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

	public ProjectPropertiesComparator getComparator() {
		return comparator;
	}

	public void setComparator(ProjectPropertiesComparator comparator) {
		this.comparator = comparator;
	}

}
