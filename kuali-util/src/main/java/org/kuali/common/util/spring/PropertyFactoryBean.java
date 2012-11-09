package org.kuali.common.util.spring;

import java.util.Properties;

import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.DefaultPropertyLoadingContext;
import org.springframework.beans.factory.FactoryBean;

public class PropertyFactoryBean extends DefaultPropertyLoadingContext implements FactoryBean<Properties> {

	private Properties factoryBeanProperties;

	@Override
	public synchronized Properties getObject() throws Exception {
		if (factoryBeanProperties == null) {
			factoryBeanProperties = PropertyUtils.getProperties(this);
		}
		return factoryBeanProperties;
	}

	@Override
	public Class<Properties> getObjectType() {
		return Properties.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
