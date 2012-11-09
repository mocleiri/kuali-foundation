package org.kuali.common.util.spring;

import org.kuali.common.util.ResourceUtils;
import org.springframework.beans.factory.FactoryBean;

public class ToStringFactoryBean extends ToStringContext implements FactoryBean<String> {

	@Override
	public String getObject() throws Exception {
		return ResourceUtils.toString(this);
	}

	@Override
	public Class<String> getObjectType() {
		return String.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

}
