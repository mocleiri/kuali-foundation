package org.kuali.common.util.spring.service;

import java.util.List;
import java.util.Map;

import org.springframework.core.env.PropertySource;

public interface PropertySourceService {

	PropertySource<?> getPropertySource(Map<String, Object> beans, List<String> defaultProfiles, List<String> activeProfiles, Class<PropertySourceConfig> config);

	PropertySource<?> getPropertySource(Class<PropertySourceConfig> config);

	List<PropertySource<?>> getPropertySources(Map<String, Object> beans, List<String> defaultProfiles, List<String> activeProfiles, Class<?> config);

	List<PropertySource<?>> getPropertySources(Map<String, Object> beans, List<String> defaultProfiles, List<String> activeProfiles, String location);

}
