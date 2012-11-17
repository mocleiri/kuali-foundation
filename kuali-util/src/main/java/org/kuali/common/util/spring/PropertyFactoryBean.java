package org.kuali.common.util.spring;

import java.util.Properties;

import org.kuali.common.util.property.DefaultPropertyLoadContext;
import org.kuali.common.util.service.DefaultPropertyService;
import org.kuali.common.util.service.PropertyService;
import org.springframework.beans.factory.FactoryBean;

public class PropertyFactoryBean extends DefaultPropertyLoadContext implements FactoryBean<Properties> {

	protected static Properties properties;
	boolean singleton = true;
	PropertyService service = new DefaultPropertyService();

	@Override
	public Properties getObject() throws Exception {
		if (isSingleton()) {
			return getInstance();
		} else {
			return service.load(this);
		}
	}

	protected synchronized Properties getInstance() {
		if (properties == null) {
			properties = service.load(this);
		}
		return properties;
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

	public PropertyService getService() {
		return service;
	}

	public void setService(PropertyService service) {
		this.service = service;
	}
}
