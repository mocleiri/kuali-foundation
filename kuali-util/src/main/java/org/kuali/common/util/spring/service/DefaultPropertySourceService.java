package org.kuali.common.util.spring.service;

import java.util.List;
import java.util.Map;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.spring.PropertySourceUtils;
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
		return getPropertySourceFromUntypedConfig(beans, profiles, config);
	}

	@Override
	public PropertySource<?> getPropertySourceFromUntypedConfig(Map<String, Object> beans, List<String> profiles, Class<?> config) {

		Assert.notNull(springService, "springService is null");

		SpringContext context = new SpringContext();
		context.setContextBeans(beans);
		context.setActiveProfiles(profiles);
		context.setAnnotatedClasses(CollectionUtils.asList(config));
		ConfigurableApplicationContext ctx = springService.getApplicationContext(context);
		ctx.refresh();
		List<PropertySource<?>> sources = PropertySourceUtils.getPropertySources(ctx);
		Assert.isTrue(sources.size() == 1, "sources.size() != 1");
		PropertySource<?> source = sources.get(0);
		return source;
	}

	public SpringService getSpringService() {
		return springService;
	}

	public void setSpringService(SpringService springService) {
		this.springService = springService;
	}

}
