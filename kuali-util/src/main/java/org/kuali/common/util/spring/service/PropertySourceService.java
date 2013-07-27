package org.kuali.common.util.spring.service;

import java.util.List;
import java.util.Map;

import org.springframework.core.env.PropertySource;

public interface PropertySourceService {

	PropertySource<?> getPropertySource(Map<String, Object> beans, List<String> profiles, Class<PropertySourceConfig> config);

}
