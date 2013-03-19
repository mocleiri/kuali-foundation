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
	public Properties getObject() throws Exception {
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
