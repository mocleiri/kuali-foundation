package org.kuali.common.util.spring.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.spring.PropertySourceUtils;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.PropertySource;
import org.springframework.util.Assert;

public class DefaultPropertySourceService implements PropertySourceService {

	SpringService springService;

	@Override
	public PropertySource<?> getPropertySource(Class<PropertySourceConfig> config) {
		return getPropertySource(null, null, null, config);
	}

	@Override
	public PropertySource<?> getPropertySource(Map<String, Object> beans, List<String> defaultProfiles, List<String> activeProfiles, Class<PropertySourceConfig> config) {
		return getPropertySourceInternal(beans, defaultProfiles, activeProfiles, config, null);
	}

	@Override
	public List<PropertySource<?>> getPropertySources(Map<String, Object> beans, List<String> defaultProfiles, List<String> activeProfiles, Class<?> config) {
		return getPropertySourcesInternal(beans, defaultProfiles, activeProfiles, config, null);
	}

	@Override
	public List<PropertySource<?>> getPropertySources(Map<String, Object> beans, List<String> defaultProfiles, List<String> activeProfiles, String location) {
		return getPropertySourcesInternal(beans, defaultProfiles, activeProfiles, null, location);
	}

	protected PropertySource<?> getPropertySourceInternal(Map<String, Object> beans, List<String> defaultProfiles, List<String> activeProfiles, Class<?> config, String location) {
		List<PropertySource<?>> sources = getPropertySourcesInternal(beans, defaultProfiles, activeProfiles, config, null);
		Assert.isTrue(sources.size() == 1, "sizes != 1");
		return sources.get(0);
	}

	protected List<PropertySource<?>> getPropertySourcesInternal(Map<String, Object> beans, List<String> defaultProfiles, List<String> activeProfiles, Class<?> config,
			String location) {

		Assert.notNull(springService, "springService is null");

		SpringContext context = new SpringContext();
		context.setContextBeans(beans);
		if (!StringUtils.isBlank(location)) {
			context.setLocations(Arrays.asList(location));
		} else if (config != null) {
			context.setAnnotatedClasses(CollectionUtils.asList(config));
		} else {
			throw new IllegalArgumentException("Must supply either a location or annotated config");
		}
		context.setActiveProfiles(activeProfiles);
		context.setDefaultProfiles(defaultProfiles);
		ConfigurableApplicationContext ctx = springService.getApplicationContext(context);
		ctx.refresh();
		return PropertySourceUtils.getPropertySources(ctx);
	}

	public SpringService getSpringService() {
		return springService;
	}

	public void setSpringService(SpringService springService) {
		this.springService = springService;
	}

}
