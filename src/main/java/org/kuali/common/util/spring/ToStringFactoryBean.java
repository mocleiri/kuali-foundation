package org.kuali.common.util.spring;

import org.kuali.common.util.service.LocationService;
import org.springframework.beans.factory.FactoryBean;

public class ToStringFactoryBean extends ToStringContext implements FactoryBean<String> {

	LocationService service = new LocationService();

	@Override
	public String getObject() throws Exception {
		return service.toString(this);
	}

	@Override
	public Class<String> getObjectType() {
		return String.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	public LocationService getService() {
		return service;
	}

	public void setService(LocationService service) {
		this.service = service;
	}

}
