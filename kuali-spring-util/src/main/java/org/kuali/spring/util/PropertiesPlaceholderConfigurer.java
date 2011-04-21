package org.kuali.spring.util;

import java.util.Properties;

import org.kuali.spring.util.event.DefaultVisitListener;
import org.kuali.spring.util.event.VisitListener;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionVisitor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.util.StringValueResolver;

/**
 * 
 */
public class PropertiesPlaceholderConfigurer extends PlaceholderConfigurer {

	private PropertiesLoader loader = new PropertiesLoader();
	private PropertiesConverter converter = new PropertiesConverter();
	private PropertiesResolver resolver = new PropertiesResolver();
	private Properties properties;
	private String nullValue;

	protected boolean currentBeanIsMe(String currentBean, ConfigurableListableBeanFactory beanFactoryToProcess) {
		if (!currentBean.equals(getBeanName())) {
			return false;
		}
		if (!beanFactoryToProcess.equals(getBeanFactory())) {
			return false;
		}
		return true;
	}

	protected BeanDefinitionVisitor getBeanDefinitionVisitor(StringValueResolver valueResolver) {
		EnhancedBeanDefinitionVisitor visitor = new EnhancedBeanDefinitionVisitor();
		visitor.setValueResolver(valueResolver);
		VisitListener listener = new DefaultVisitListener();
		visitor.addListener(listener);
		return visitor;
	}

	protected void processBeans(ConfigurableListableBeanFactory beanFactory, StringValueResolver valueResolver) {
		BeanDefinitionVisitor visitor = getBeanDefinitionVisitor(valueResolver);
		String[] beanNames = beanFactory.getBeanDefinitionNames();
		for (String curName : beanNames) {
			// Skip processing our own bean definition
			if (currentBeanIsMe(curName, beanFactory)) {
				continue;
			}
			BeanDefinition bd = beanFactory.getBeanDefinition(curName);
			try {
				visitor.visitBeanDefinition(bd);
			} catch (Exception e) {
				throw new BeanDefinitionStoreException(bd.getResourceDescription(), curName, e.getMessage(), e);
			}
		}
	}

	protected StringValueResolver getStringValueResolver() {
		ValueRetriever retriever = new PropertiesRetriever(this.properties);
		DefaultStringValueResolver dsvr = new DefaultStringValueResolver();
		dsvr.setResolver(this.resolver);
		dsvr.setNullValue(this.nullValue);
		dsvr.setRetriever(retriever);
		return dsvr;
	}

	@Override
	protected void processPlaceholders(ConfigurableListableBeanFactory beanFactoryToProcess) {
		this.properties = this.loader.loadProperties();
		this.converter.convert(this.properties);
		this.resolver.resolve(this.properties);

		StringValueResolver valueResolver = getStringValueResolver();

		processBeans(beanFactoryToProcess, valueResolver);

		// New in Spring 2.5: resolve placeholders in alias target names and aliases as well.
		beanFactoryToProcess.resolveAliases(valueResolver);

		// New in Spring 3.0: resolve placeholders in embedded values such as annotation attributes.
		beanFactoryToProcess.addEmbeddedValueResolver(valueResolver);

	}

	public PropertiesLoader getLoader() {
		return loader;
	}

	public void setLoader(PropertiesLoader loader) {
		this.loader = loader;
	}

	public PropertiesConverter getConverter() {
		return converter;
	}

	public void setConverter(PropertiesConverter converter) {
		this.converter = converter;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public PropertiesResolver getResolver() {
		return resolver;
	}

	public void setResolver(PropertiesResolver resolver) {
		this.resolver = resolver;
	}

	public String getNullValue() {
		return nullValue;
	}

	public void setNullValue(String nullValue) {
		this.nullValue = nullValue;
	}

}
