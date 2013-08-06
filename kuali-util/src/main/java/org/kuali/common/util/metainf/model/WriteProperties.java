package org.kuali.common.util.metainf.model;

import java.util.Properties;

import org.kuali.common.util.Assert;
import org.kuali.common.util.property.ImmutableProperties;

public class WriteProperties {

	private final WriteRequest request;
	private final Properties properties;

	public WriteProperties(WriteRequest request, Properties properties) {
		Assert.noNulls(request, properties);
		this.request = request;
		this.properties = new ImmutableProperties(properties);
	}

	public Properties getProperties() {
		return properties;
	}

	public WriteRequest getRequest() {
		return request;
	}

}
