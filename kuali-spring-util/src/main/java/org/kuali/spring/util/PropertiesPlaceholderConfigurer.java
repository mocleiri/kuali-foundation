package org.kuali.spring.util;

import java.util.List;
import java.util.Properties;

import org.kuali.spring.util.event.DefaultVisitListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionVisitor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.util.StringValueResolver;

/**
 * 
 */
public class PropertiesPlaceholderConfigurer extends PlaceholderConfigurer {
    private final Logger logger = LoggerFactory.getLogger(PropertiesPlaceholderConfigurer.class);

    private PropertiesLoader loader = new PropertiesLoader();
    private PropertiesConverter converter = new PropertiesConverter();
    private PropertiesResolver resolver = new PropertiesResolver();
    private Properties properties;
    private String nullValue;
    private List<String> skipNames;

    protected boolean isSkip(String currentBeanName) {
        if (skipNames == null) {
            return false;
        } else {
            return skipNames.contains(currentBeanName);
        }
    }

    protected BeanDefinitionVisitor getBeanDefinitionVisitor(StringValueResolver valueResolver) {
        EnhancedBeanDefinitionVisitor visitor = new EnhancedBeanDefinitionVisitor();
        visitor.setValueResolver(valueResolver);
        DefaultVisitListener listener = new DefaultVisitListener();
        listener.setPlogger(loader.getPlogger());
        visitor.addListener(listener);
        return visitor;
    }

    protected void processBeans(ConfigurableListableBeanFactory beanFactory, StringValueResolver valueResolver) {
        BeanDefinitionVisitor visitor = getBeanDefinitionVisitor(valueResolver);
        String[] beanNames = beanFactory.getBeanDefinitionNames();
        for (String curName : beanNames) {
            if (isSkip(curName)) {
                logger.info("Skipping placeholder processing on bean '" + curName + '"');
                continue;
            } else {
                logger.info("Processing placeholders on bean '" + curName + '"');
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

        // New in Spring 2.5: resolve placeholders in alias target names and
        // aliases as well.
        beanFactoryToProcess.resolveAliases(valueResolver);

        // New in Spring 3.0: resolve placeholders in embedded values such as
        // annotation attributes.
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

    public List<String> getSkipNames() {
        return skipNames;
    }

    public void setSkipNames(List<String> skipNames) {
        this.skipNames = skipNames;
    }

}
