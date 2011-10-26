package org.kuali.spring.util;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.util.Assert;

/**
 * This class provides a simple mechanism for enabling Spring to create proxied objects. By default a NoOp CallBack will
 * be associated with the proxy object (not very useful) but you can inject any custom CallBack as desired.
 */
public class SimpleProxyFactoryBean implements FactoryBean<Object> {
    public static final Callback DEFAULT_CALLBACK = NoOp.INSTANCE;
    public static final boolean DEFAULT_IS_COPY_SOURCE_BEAN_PROPERTIES = true;

    Callback callback = DEFAULT_CALLBACK;
    String classname;
    Object sourceBean;
    boolean copySourceBeanProperties = DEFAULT_IS_COPY_SOURCE_BEAN_PROPERTIES;

    public SimpleProxyFactoryBean() {
        this(null, DEFAULT_CALLBACK);
    }

    public SimpleProxyFactoryBean(String classname) {
        this(classname, DEFAULT_CALLBACK);
    }

    public SimpleProxyFactoryBean(String classname, Callback callback) {
        super();
        this.classname = classname;
        this.callback = callback;
    }

    @Override
    public Object getObject() throws Exception {
        Assert.isTrue(this.classname != null || this.sourceBean != null);
        Assert.notNull(this.callback);
        if (this.copySourceBeanProperties) {
            Assert.notNull(this.sourceBean);
        }

        Class<?> targetClass = getTargetClass();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetClass);
        enhancer.setCallback(getCallback());
        Object proxy = enhancer.create();
        if (this.copySourceBeanProperties) {
            BeanUtils.copyProperties(this.sourceBean, proxy);
        }
        return proxy;
    }

    protected Class<?> getTargetClass() throws ClassNotFoundException {
        if (this.classname != null) {
            return Class.forName(this.classname);
        } else {
            return this.sourceBean.getClass();
        }
    }

    @Override
    public Class<?> getObjectType() {
        return Object.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    public Callback getCallback() {
        return callback;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public Object getSourceBean() {
        return sourceBean;
    }

    public void setSourceBean(Object source) {
        this.sourceBean = source;
    }

}
