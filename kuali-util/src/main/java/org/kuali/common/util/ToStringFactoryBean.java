package org.kuali.common.util;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.util.Assert;

public class ToStringFactoryBean implements FactoryBean<String> {

	String location;
	String encoding;

	@Override
	public String getObject() throws Exception {
		Assert.notNull("Location is null", location);
		return ResourceUtils.toString(location, encoding);
	}

	@Override
	public Class<String> getObjectType() {
		return String.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
}
