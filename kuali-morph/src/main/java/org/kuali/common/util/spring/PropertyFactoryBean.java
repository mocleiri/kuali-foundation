package org.kuali.common.util.spring;

import java.util.Properties;

import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.DefaultPropertyLoadingContext;
import org.springframework.beans.factory.FactoryBean;

public class PropertyFactoryBean extends DefaultPropertyLoadingContext implements FactoryBean<Properties> {

	protected static Properties factoryBeanProperties;
	boolean singleton;

	@Override
	public Properties getObject() throws Exception {
		if (isSingleton()) {
			return getInstance();
		} else {
			return PropertyUtils.getProperties(this);
		}
	}

	protected synchronized Properties getInstance() {
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
		return this.singleton;
	}

	public void setSingleton(boolean singleton) {
		this.singleton = singleton;
	}
}
