package org.kuali.spring.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.util.Assert;

public class DelegatingPropertyPlaceholderConfigurer extends ConfigurablePropertyPlaceholderConfigurer {

    ConfigurablePropertyPlaceholderConfigurer delegate;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        Assert.notNull(delegate);
        BeanUtils.copyProperties(this, delegate);
        delegate.postProcessBeanFactory(beanFactory);
    }

    public ConfigurablePropertyPlaceholderConfigurer getDelegate() {
        return delegate;
    }

    public void setDelegate(ConfigurablePropertyPlaceholderConfigurer delegate) {
        this.delegate = delegate;
    }

}
