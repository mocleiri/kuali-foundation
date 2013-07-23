package edu.calpoly.records.spring;

import java.util.Properties;

import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.execute.Executable;

public class ShowPropertiesExecutable implements Executable {

	Properties properties;

	public ShowPropertiesExecutable(Properties properties) {
		super();
		this.properties = properties;
	}

	@Override
	public void execute() {
		PropertyUtils.info(properties);
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

}
