package org.kuali.spring.util.event;

import org.springframework.beans.factory.config.BeanDefinition;

public class BeanVisitEvent {
    BeanDefinition beanDefinition;

    public BeanVisitEvent() {
        this(null);
    }

    public BeanVisitEvent(BeanDefinition beanDefinition) {
        super();
        this.beanDefinition = beanDefinition;
    }

    public BeanDefinition getBeanDefinition() {
        return beanDefinition;
    }

    public void setBeanDefinition(BeanDefinition beanDefinition) {
        this.beanDefinition = beanDefinition;
    }

}
