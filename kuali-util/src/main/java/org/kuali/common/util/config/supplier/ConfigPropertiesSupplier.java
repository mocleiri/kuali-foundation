package org.kuali.common.util.config.supplier;

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.config.ConfigService;
import org.springframework.util.Assert;

public class ConfigPropertiesSupplier implements PropertiesSupplier {

	ConfigService service;
	List<String> configIds;

	@Override
	public Properties getProperties() {

		Assert.notNull(service, "service is null");
		Assert.notNull(configIds, "configIds is null");

		return service.getProperties(configIds);
	}

	public ConfigService getService() {
		return service;
	}

	public void setService(ConfigService service) {
		this.service = service;
	}

	public List<String> getConfigIds() {
		return configIds;
	}

	public void setConfigIds(List<String> configIds) {
		this.configIds = configIds;
	}

}
