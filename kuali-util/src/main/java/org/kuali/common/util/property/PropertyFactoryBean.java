package org.kuali.common.util.property;

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.PropertyUtils;
import org.springframework.beans.factory.FactoryBean;

public class PropertyFactoryBean implements FactoryBean<Properties> {

	List<String> locations;

	@Override
	public Properties getObject() throws Exception {
		return PropertyUtils.getProperties(locations);
	}

	@Override
	public Class<Properties> getObjectType() {
		return Properties.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public List<String> getLocations() {
		return locations;
	}

	public void setLocations(List<String> locations) {
		this.locations = locations;
	}

}
