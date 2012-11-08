package org.kuali.common.util;

import java.util.List;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.util.Assert;

public class ReadLinesFactoryBean implements FactoryBean<List<String>> {

	String location;
	String encoding;

	@Override
	public List<String> getObject() throws Exception {
		Assert.notNull("Location is null", location);
		return ResourceUtils.readLines(location);
	}

	@Override
	public Class<?> getObjectType() {
		return List.class;
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
