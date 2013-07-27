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
	public List<PropertySource<?>> getPropertySources(Class<PropertySourceConfig> config) {
		return getPropertySources(null, null, null, config);
	}

	@Override
	public List<PropertySource<?>> getPropertySources(Map<String, Object> beans, List<String> defaultProfiles, List<String> activeProfiles, Class<PropertySourceConfig> config) {
		return getPropertySourcesInternal(beans, defaultProfiles, activeProfiles, config);
	}

	@Override
	public PropertySource<?> getPropertySource(Class<PropertySourceConfig> config) {
		return getPropertySource(null, null, null, config);
	}

	@Override
	public PropertySource<?> getPropertySource(Map<String, Object> beans, List<String> defaultProfiles, List<String> activeProfiles, Class<PropertySourceConfig> config) {
		return getPropertySourceInternal(beans, defaultProfiles, activeProfiles, config);
	}

	@Override
	@Deprecated
	public PropertySource<?> getPropertySourceFromUntypedConfig(Map<String, Object> beans, List<String> defaultProfiles, List<String> activeProfiles, Class<?> config) {
		return getPropertySourceInternal(beans, defaultProfiles, activeProfiles, config);
	}

	@Override
	@Deprecated
	public List<PropertySource<?>> getPropertySourcesFromUntypedConfig(Map<String, Object> beans, List<String> defaultProfiles, List<String> activeProfiles, Class<?> config) {
		return getPropertySourcesInternal(beans, defaultProfiles, activeProfiles, config);
	}

	protected PropertySource<?> getPropertySourceInternal(Map<String, Object> beans, List<String> defaultProfiles, List<String> activeProfiles, Class<?> config) {
		List<PropertySource<?>> sources = getPropertySourcesInternal(beans, defaultProfiles, activeProfiles, config);
		Assert.isTrue(sources.size() == 1, "sizes != 1");
		return sources.get(0);
	}

	protected List<PropertySource<?>> getPropertySourcesInternal(Map<String, Object> beans, List<String> defaultProfiles, List<String> activeProfiles, Class<?> config) {

		Assert.notNull(springService, "springService is null");

		SpringContext context = new SpringContext();
		context.setContextBeans(beans);
		context.setActiveProfiles(activeProfiles);
		context.setDefaultProfiles(defaultProfiles);
		context.setAnnotatedClasses(CollectionUtils.asList(config));
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
