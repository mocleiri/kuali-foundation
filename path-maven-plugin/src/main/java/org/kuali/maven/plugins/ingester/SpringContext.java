/**
 * Copyright 2011-2012 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.maven.plugins.ingester;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.kuali.rice.core.config.ConfigContext;
import org.kuali.rice.core.resourceloader.GlobalResourceLoader;
import org.kuali.rice.core.resourceloader.RiceResourceLoaderFactory;
import org.kuali.rice.core.util.RiceConstants;
import org.kuali.rice.kns.util.spring.ClassPathXmlApplicationContext;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ConfigurableApplicationContext;

import uk.ltd.getahead.dwr.create.SpringCreator;

public class SpringContext {
    protected static final Logger LOG = Logger.getLogger(SpringContext.class);
    protected static final String APPLICATION_CONTEXT_DEFINITION = "spring-rice-startup.xml";
    protected static ConfigurableApplicationContext applicationContext;
    protected static Set<Class<? extends Object>> SINGLETON_TYPES = new HashSet<Class<? extends Object>>();
    protected static Map<Class<? extends Object>, Object> SINGLETON_BEANS_BY_TYPE_CACHE = new HashMap<Class<? extends Object>, Object>();
    protected static Map<String, Object> SINGLETON_BEANS_BY_NAME_CACHE = new HashMap<String, Object>();
    @SuppressWarnings("rawtypes")
    protected static Map<Class<? extends Object>, Map> SINGLETON_BEANS_OF_TYPE_CACHE = new HashMap<Class<? extends Object>, Map>();

    /**
     * Use this method to retrieve a service which may or may not be implemented locally. (That is, defined in the main
     * Spring ApplicationContext created by Rice.
     */
    public static Object getService(String serviceName) {
        return GlobalResourceLoader.getService(serviceName);
    }

    /**
     * Use this method to retrieve a spring bean when one of the following is the case. Pass in the type of the service
     * interface, NOT the service implementation.<br>
     * 1. there is only one bean of the specified type in our spring context<br>
     * 2. there is only one bean of the specified type in our spring context, but you want the one whose bean id is the
     * same as type.getSimpleName() with the exception of the first letter being lower case in the former and upper case
     * in the latter. <br>
     * For example, there are two beans of type DateTimeService in our context dateTimeService and testDateTimeService.
     * To retrieve the former, you should specific DateTimeService.class as the type. To retrieve the latter, you should
     * specify ConfigurableDateService.class as the type. Unless you are writing a unit test and need to down cast to an
     * implementation, you do not need to cast the result of this method.
     *
     * @param <T>
     * @param type
     * @return an object that has been defined as a bean in our spring context and is of the specified type
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> type) {
        verifyProperInitialization();
        T bean = null;
        if (SINGLETON_BEANS_BY_TYPE_CACHE.containsKey(type)) {
            bean = (T) SINGLETON_BEANS_BY_TYPE_CACHE.get(type);
        } else {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Bean not already in cache: " + type + " - calling getBeansOfType() ");
            }
            Collection<T> beansOfType = getBeansOfType(type).values();
            if (!beansOfType.isEmpty()) {
                if (beansOfType.size() > 1) {
                    bean = getBean(type, StringUtils.uncapitalize(type.getSimpleName()));
                } else {
                    bean = beansOfType.iterator().next();
                }
            } else {
                try {
                    bean = getBean(type, StringUtils.uncapitalize(type.getSimpleName()));
                } catch (Exception ex) {
                    // do nothing, let fall through
                }
                if (bean == null) {
                    // unable to find bean - check GRL
                    // this is needed in case no beans of the given type exist locally
                    if (LOG.isDebugEnabled()) {
                        LOG.debug("Bean not found in local context: " + type.getName() + " - calling GRL");
                    }
                    Object remoteServiceBean = getService(StringUtils.uncapitalize(type.getSimpleName()));
                    if (remoteServiceBean != null) {
                        if (type.isAssignableFrom(remoteServiceBean.getClass())) {
                            bean = (T) remoteServiceBean;
                        }
                    }
                }
            }
            if (bean != null) {
                synchronized (SINGLETON_TYPES) {
                    if (SINGLETON_TYPES.contains(type) || hasSingletonSuperType(type, SINGLETON_TYPES)) {
                        SINGLETON_TYPES.add(type);
                        SINGLETON_BEANS_BY_TYPE_CACHE.put(type, bean);
                    }
                }
            } else {
                throw new RuntimeException(
                        "Request for non-existent bean.  Unable to find in local context or on the GRL: "
                                + type.getName());
            }
        }
        return bean;
    }

    /**
     * Use this method to retrieve all beans of a give type in our spring context. Pass in the type of the service
     * interface, NOT the service implementation.
     *
     * @param <T>
     * @param type
     * @return a map of the spring bean ids / beans that are of the specified type
     */
    @SuppressWarnings("unchecked")
    public static <T> Map<String, T> getBeansOfType(Class<T> type) {
        verifyProperInitialization();
        Map<String, T> beansOfType = null;
        if (SINGLETON_BEANS_OF_TYPE_CACHE.containsKey(type)) {
            beansOfType = SINGLETON_BEANS_OF_TYPE_CACHE.get(type);
        } else {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Bean not already in \"OF_TYPE\" cache: " + type
                        + " - calling getBeansOfType() on Spring context");
            }
            boolean allOfTypeAreSingleton = true;
            beansOfType = applicationContext.getBeansOfType(type);
            for (String key : beansOfType.keySet()) {
                if (!applicationContext.isSingleton(key)) {
                    allOfTypeAreSingleton = false;
                }
            }
            if (allOfTypeAreSingleton) {
                synchronized (SINGLETON_TYPES) {
                    SINGLETON_TYPES.add(type);
                    SINGLETON_BEANS_OF_TYPE_CACHE.put(type, beansOfType);
                }
            }
        }
        return beansOfType;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> type, String name) {
        T bean = null;
        if (SINGLETON_BEANS_BY_NAME_CACHE.containsKey(name)) {
            bean = (T) SINGLETON_BEANS_BY_NAME_CACHE.get(name);
        } else {
            try {
                bean = (T) applicationContext.getBean(name);
                if (applicationContext.isSingleton(name)) {
                    synchronized (SINGLETON_BEANS_BY_NAME_CACHE) {
                        SINGLETON_BEANS_BY_NAME_CACHE.put(name, bean);
                    }
                }
            } catch (NoSuchBeanDefinitionException nsbde) {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("Bean with name and type not found in local context: " + name + "/" + type.getName()
                            + " - calling GRL");
                }
                Object remoteServiceBean = getService(name);
                if (remoteServiceBean != null) {
                    if (type.isAssignableFrom(AopUtils.getTargetClass(remoteServiceBean))) {
                        bean = (T) remoteServiceBean;
                        // assume remote beans are services and thus singletons
                        synchronized (SINGLETON_BEANS_BY_NAME_CACHE) {
                            SINGLETON_BEANS_BY_NAME_CACHE.put(name, bean);
                        }
                    }
                }
                throw new RuntimeException(
                        "No bean of this type and name exist in the application context or from the GRL: "
                                + type.getName() + ", " + name);
            }
        }
        return bean;
    }

    private static boolean hasSingletonSuperType(Class<? extends Object> type,
            Set<Class<? extends Object>> knownSingletonTypes) {
        for (Class<? extends Object> singletonType : knownSingletonTypes) {
            if (singletonType.isAssignableFrom(type)) {
                return true;
            }
        }
        return false;
    }

    protected static Object getBean(String beanName) {
        return getBean(Object.class, beanName);
    }

    protected static String[] getBeanNames() {
        verifyProperInitialization();
        return applicationContext.getBeanDefinitionNames();
    }

    protected static void initializeApplicationContext() {
        initializeApplicationContext(APPLICATION_CONTEXT_DEFINITION, true);
    }

    protected static void initializeApplicationContextWithoutSchedule() {
        initializeApplicationContext(APPLICATION_CONTEXT_DEFINITION, false);
    }

    protected static void initializeBatchApplicationContext() {
        initializeApplicationContext(APPLICATION_CONTEXT_DEFINITION, true);
    }

    protected static void close() throws Exception {
        if (applicationContext == null) {
            applicationContext = RiceResourceLoaderFactory.getSpringResourceLoader().getContext();
        }
        DisposableBean riceConfigurer = null;
        try {
            riceConfigurer = (DisposableBean) applicationContext.getBean("rice");
        } catch (Exception ex) {
            LOG.debug("Unable to get 'rice' bean - attempting to get from the Rice ConfigContext", ex);
            riceConfigurer = (DisposableBean) ConfigContext
                    .getObjectFromConfigHierarchy(RiceConstants.RICE_CONFIGURER_CONFIG_NAME);
        }
        applicationContext = null;
        if (riceConfigurer != null) {
            riceConfigurer.destroy();
        } else {
            LOG.error("Unable to close SpringContext - unable to get a handle to a RiceConfigurer object.");
        }
        ConfigContext.destroy();
        PropertyLoadingFactoryBean.clear();
    }

    public static boolean isInitialized() {
        return applicationContext != null;
    }

    private static void verifyProperInitialization() {
        if (applicationContext == null) {
            LOG.fatal("*****************************************************************");
            LOG.fatal("*****************************************************************");
            LOG.fatal("*****************************************************************");
            LOG.fatal("*****************************************************************");
            LOG.fatal("*****************************************************************");
            LOG.fatal(
                    "Spring not initialized properly.  Initialization has begun and the application context is null.  Probably spring loaded bean is trying to use SpringContext.getBean() before the application context is initialized.",
                    new IllegalStateException());
            LOG.fatal("*****************************************************************");
            LOG.fatal("*****************************************************************");
            LOG.fatal("*****************************************************************");
            LOG.fatal("*****************************************************************");
            LOG.fatal("*****************************************************************");
            throw new IllegalStateException(
                    "Spring not initialized properly.  Initialization has begun and the application context is null.  Probably spring loaded bean is trying to use SpringContext.getBean() before the application context is initialized.");
        }
    }

    private static void initializeApplicationContext(String riceInitializationSpringFile, boolean initializeSchedule) {
        LOG.info("Starting Spring context initialization");
        // use the base config file to bootstrap the real application context started by Rice
        new ClassPathXmlApplicationContext(riceInitializationSpringFile);
        // pull the Rice application context into here for further use and efficiency
        applicationContext = RiceResourceLoaderFactory.getSpringResourceLoader().getContext();
        LOG.info("Completed Spring context initialization");

        SpringCreator.setOverrideBeanFactory(applicationContext.getBeanFactory());
    }
}
