package org.kuali.common.util.main;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.kuali.common.util.Assert;
import org.kuali.common.util.spring.service.PropertySourceConfig;
import org.kuali.common.util.spring.service.PropertySourceService;
import org.springframework.core.env.PropertySource;

public class DefaultMainService implements MainService {

	private final PropertySourceService service;

	public DefaultMainService(PropertySourceService service) {
		Assert.noNulls(service);
		this.service = service;
	}

	@Override
	public PropertySource<?> getPropertySource(MainContext context, Class<? extends PropertySourceConfig> config) {
		Assert.noNulls(context, config);
		Map<String, Object> beans = Collections.singletonMap(MainUtils.MAIN_CONTEXT_BEAN_NAME, (Object) context);
		List<String> activeProfiles = Arrays.asList(MainUtils.MAIN_PROFILE_NAME);
		return service.getPropertySource(beans, null, activeProfiles, config);
	}

	public PropertySourceService getService() {
		return service;
	}

}
