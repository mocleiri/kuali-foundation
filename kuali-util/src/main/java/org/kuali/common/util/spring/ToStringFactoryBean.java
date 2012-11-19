package org.kuali.common.util.spring;

import org.kuali.common.util.service.ResourceService;
import org.springframework.beans.factory.FactoryBean;

public class ToStringFactoryBean extends ToStringContext implements FactoryBean<String> {

	ResourceService service = new ResourceService();

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

	public ResourceService getService() {
		return service;
	}

	public void setService(ResourceService service) {
		this.service = service;
	}

}
