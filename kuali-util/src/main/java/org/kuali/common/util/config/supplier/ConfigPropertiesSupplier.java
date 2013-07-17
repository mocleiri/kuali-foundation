package org.kuali.common.util.config.supplier;

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.config.ConfigService;
import org.kuali.common.util.config.DefaultConfigService;
import org.springframework.util.Assert;

public class ConfigPropertiesSupplier implements PropertiesSupplier {

	public static final ConfigService DEFAULT_CONFIG_SERVICE = new DefaultConfigService();

	ConfigService service = DEFAULT_CONFIG_SERVICE;
	List<String> configIds;
	List<String> locations;

	@Override
	public Properties getProperties() {

		Assert.notNull(service, "service is null");
		Assert.notNull(configIds, "configIds is null");

		Properties overrides = getOverrides(locations);
		return service.getProperties(configIds, overrides);
	}

	protected Properties getOverrides(List<String> locations) {
		Properties overrides = new Properties();
		for (String location : CollectionUtils.toEmptyList(locations)) {
			Properties loaded = PropertyUtils.load(location);
			overrides.putAll(loaded);
		}
		return overrides;
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
