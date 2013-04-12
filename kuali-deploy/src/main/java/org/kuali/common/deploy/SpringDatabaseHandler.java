package org.kuali.common.deploy;

import java.util.List;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.service.DefaultSpringService;
import org.kuali.common.util.service.SpringContext;
import org.kuali.common.util.service.SpringService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class SpringDatabaseHandler implements DatabaseHandler {

	private static final Logger logger = LoggerFactory.getLogger(SpringDatabaseHandler.class);

	SpringService service = new DefaultSpringService();
	Class<?> annotatedClass;
	List<Object> beans;
	List<String> beanNames;
	boolean skip;

	@Override
	public void reset() {
		if (skip) {
			logger.info("Skipping database reset");
			return;
		}
		Assert.notNull(service);
		Assert.notNull(annotatedClass);
		logger.info("Database reset");
		SpringContext sc = new SpringContext();
		sc.setAnnotatedClasses(CollectionUtils.asList(annotatedClass));
		sc.setBeanNames(beanNames);
		sc.setBeans(beans);
		service.load(sc);
	}

	public SpringService getService() {
		return service;
	}

	public void setService(SpringService service) {
		this.service = service;
	}

	public List<Object> getBeans() {
		return beans;
	}

	public void setBeans(List<Object> beans) {
		this.beans = beans;
	}

	public List<String> getBeanNames() {
		return beanNames;
	}

	public void setBeanNames(List<String> beanNames) {
		this.beanNames = beanNames;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

}
