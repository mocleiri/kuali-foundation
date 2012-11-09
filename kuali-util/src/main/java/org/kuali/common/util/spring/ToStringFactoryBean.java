package org.kuali.common.util.spring;

import org.kuali.common.util.ResourceUtils;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.util.Assert;

public class ToStringFactoryBean extends ToStringContext implements FactoryBean<String> {

	@Override
	public String getObject() throws Exception {
		Assert.notNull("location is null", location);
		String s = ResourceUtils.toString(location, encoding);
		if (delete) {
			ResourceUtils.delete(location, deleteQuietly);
		}
		return (trim) ? s.trim() : s;
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
