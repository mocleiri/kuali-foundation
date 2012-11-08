package org.kuali.common.util.spring;

import java.util.Properties;

import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.DefaultPropertyLoadingContext;
import org.springframework.beans.factory.FactoryBean;

public class PropertyFactoryBean extends DefaultPropertyLoadingContext implements FactoryBean<Properties> {

	@Override
	public Properties getObject() throws Exception {
		return PropertyUtils.getProperties(this);
	}

	@Override
	public Class<Properties> getObjectType() {
		return Properties.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}
}
