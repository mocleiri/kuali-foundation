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

import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.processor.PropertyProcessor;
import org.springframework.beans.factory.FactoryBean;

public class PropertyProcessorFactoryBean implements FactoryBean<Properties> {

	Properties properties;
	List<PropertyProcessor> processors;

	@Override
	public Properties getObject() {
		PropertyUtils.process(properties, processors);
		return properties;
	}

	@Override
	public Class<Properties> getObjectType() {
		return Properties.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public List<PropertyProcessor> getProcessors() {
		return processors;
	}

	public void setProcessors(List<PropertyProcessor> processors) {
		this.processors = processors;
	}

}
