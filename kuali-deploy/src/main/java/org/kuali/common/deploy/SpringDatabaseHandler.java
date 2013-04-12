package org.kuali.common.deploy;

import java.util.Properties;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.service.DefaultSpringService;
import org.kuali.common.util.service.PropertySourceContext;
import org.kuali.common.util.service.SpringContext;
import org.kuali.common.util.service.SpringService;
import org.kuali.common.util.spring.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.util.Assert;

public class SpringDatabaseHandler implements DatabaseHandler {

	private static final Logger logger = LoggerFactory.getLogger(SpringDatabaseHandler.class);

	SpringService service = new DefaultSpringService();
	Class<?> annotatedClass;
	Properties properties;
	boolean skip;

	@Override
	public void reset() {
		if (skip) {
			logger.info("Skipping database reset");
			return;
		}
		Assert.notNull(service);
		Assert.notNull(annotatedClass);
		Assert.notNull(properties);
		logger.info("Database reset");
		PropertiesPropertySource ps = new PropertiesPropertySource("", properties);
		PropertySourceContext psc = new PropertySourceContext();
		psc.setLastOneInWins(true);
		psc.setRemoveExistingSources(true);
		psc.setSources(SpringUtils.asList(ps));
		SpringContext sc = new SpringContext();
		sc.setPropertySourceContext(psc);
		sc.setAnnotatedClasses(CollectionUtils.asList(annotatedClass));
		service.load(sc);
	}

	public SpringService getService() {
		return service;
	}

	public void setService(SpringService service) {
		this.service = service;
	}

	public Class<?> getAnnotatedClass() {
		return annotatedClass;
	}

	public void setAnnotatedClass(Class<?> annotatedClass) {
		this.annotatedClass = annotatedClass;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

}
