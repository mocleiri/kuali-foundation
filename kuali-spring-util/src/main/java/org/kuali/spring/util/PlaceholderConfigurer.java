package org.kuali.spring.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;

/**
 * 
 */
public abstract class PlaceholderConfigurer implements BeanNameAware, BeanFactoryAware, BeanFactoryPostProcessor,
		PriorityOrdered {

	private int order = Ordered.LOWEST_PRECEDENCE; // default: same as non-Ordered

	private String beanName;
	private BeanFactory beanFactory;

	protected abstract void processPlaceholders(ConfigurableListableBeanFactory beanFactory);

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		try {
			processPlaceholders(beanFactory);
		} catch (Exception e) {
			throw new BeanInitializationException("Could not complete placeholder configuration", e);
		}
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public int getOrder() {
		return this.order;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public BeanFactory getBeanFactory() {
		return beanFactory;
	}

	public void setBeanFactory(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}

}
