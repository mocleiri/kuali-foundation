package org.kuali.common.util.spring.service;

import java.util.List;
import java.util.Map;

import org.kuali.common.util.CollectionUtils;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.PropertySource;
import org.springframework.util.Assert;

public class DefaultPropertySourceService implements PropertySourceService {

	SpringService springService;

	@Override
	public PropertySource<?> getPropertySource(Class<PropertySourceConfig> config) {
		return getPropertySource(null, null, config);
	}

	@Override
	public PropertySource<?> getPropertySource(Map<String, Object> beans, List<String> profiles, Class<PropertySourceConfig> config) {

		Assert.notNull(springService, "springService is null");

		SpringContext context = new SpringContext();
		context.setContextBeans(beans);
		context.setActiveProfiles(profiles);
		context.setAnnotatedClasses(CollectionUtils.asList(config));
		ConfigurableApplicationContext ctx = springService.getApplicationContext(context);
		ctx.refresh();
		return null;
	}

	public SpringService getSpringService() {
		return springService;
	}

	public void setSpringService(SpringService springService) {
		this.springService = springService;
	}

}
