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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.xml.namespace.QName;

import org.apache.avalon.framework.configuration.ConfigurationUtil;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
//import org.kuali.rice.core.api.config.ConfigContext;
import org.kuali.rice.core.api.config.property.ConfigContext;
import org.kuali.rice.core.api.resourceloader.GlobalResourceLoader;
//import org.kuali.rice.core.api.resourceloader.RiceResourceLoaderFactory;
import org.kuali.rice.core.framework.config.property.SimpleConfig;
import org.kuali.rice.core.framework.resourceloader.SpringResourceLoader;
import org.kuali.rice.core.impl.config.property.JAXBConfigImpl;
import org.springframework.context.ConfigurableApplicationContext;
import org.kuali.rice.core.api.lifecycle.BaseLifecycle;
import org.kuali.rice.core.api.lifecycle.Lifecycle;
import org.kuali.rice.core.api.config.property.Config;

//import uk.ltd.getahead.dwr.create.SpringCreator;

public class SpringContext {
    protected static final Logger LOG = Logger.getLogger(SpringContext.class);
    protected static final String BOOTSTRAP_CONTEXT_DEFINITION = "classpath:spring-rice-startup.xml";
    protected static final String APPLICATION_CONTEXT_DEFINITION = "classpath:spring-rice-configurer.xml";
    protected static final String APPLICATION_CONFIG_LOCATION = "classpath:spring-rice-config.xml";
    
    protected static SpringResourceLoader springResourceLoader;
    
    protected static ConfigurableApplicationContext applicationContext;

    /**
     * Use this method to retrieve a service which may or may not be implemented locally. (That is, defined in the main
     * Spring ApplicationContext created by Rice.
     */
    public static Object getService(String serviceName) {
        return GlobalResourceLoader.getService(serviceName);
    }
  
    public static SpringResourceLoader getPluginSpringResourceLoader() {
        if (springResourceLoader == null) {
            springResourceLoader = new SpringResourceLoader(new QName("PluginContext"), Collections.singletonList( BOOTSTRAP_CONTEXT_DEFINITION ), null);
        }
        return springResourceLoader;
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
        	applicationContext = getPluginSpringResourceLoader().getContext();
        }
        ConfigContext.destroy();
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

    private static void initializeApplicationContext(String riceInitializationSpringFile, boolean initializeSchedule)  {
    	List<Lifecycle> suiteLifeCycles = null;
    	getBaseDir("kew");
    	LOG.info("Starting Spring context initialization");
        // use the base config file to bootstrap the real application context started by Rice
        applicationContext = getPluginSpringResourceLoader().getContext();
        suiteLifeCycles = getPluginLifecycles();
    	 try {
    		 startLifecycles(suiteLifeCycles);
    	 } catch (Throwable e) {
     		e.printStackTrace();
            try {
				stopLifecycles(suiteLifeCycles);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            throw new RuntimeException(e);
        }
    }


	private static void getBaseDir(String moduleName) {
		if (System.getProperty("basedir") == null) {
            System.setProperty("basedir", FileUtils.getUserDirectory().getAbsolutePath());
        }
	}
    
    protected static void startLifecycles(List<Lifecycle> lifecycles) throws Exception {
        for (Lifecycle lifecycle : lifecycles) {
                lifecycle.start();
        }
    }
    
    protected static void stopLifecycles(List<Lifecycle> lifecycles) throws Exception {
        int lifecyclesSize = lifecycles.size() - 1;
        for (int i = lifecyclesSize; i >= 0; i--) {
            try {
            	if (lifecycles.get(i) == null) {
            		LOG.warn("Attempted to stop a null lifecycle");
            	} else {
            		if (lifecycles.get(i).isStarted()) {
                        LOG.warn("Attempting to stop a lifecycle " + lifecycles.get(i).getClass());
            			lifecycles.get(i).stop();
            		}
            	}
            } catch (Exception e) {
                LOG.error("Failed to shutdown one of the lifecycles!", e);
            }
        }
    }
       
    protected static List<Lifecycle>  getPluginLifecycles() {
        List<Lifecycle> lifecycles = new LinkedList<Lifecycle>();
        
        /**
         * Initializes Rice configuration from base plugin configuration
         */
        lifecycles.add(new BaseLifecycle() {
            @Override
			public void start() throws Exception {
                Config config = getPluginBaseConfiguration();
                ConfigContext.init(config);
                super.start();
            }
        });
        
        /**
         * Loads the Rice Configuration for datasources, etc...
         */
        lifecycles.add(getPluginSpringResourceLoader());
        
        /**
         * Establishes the specific configurations to this lifecycle (ksb, kew, etc..)
         */
        Lifecycle loadApplicationLifecycle = getLoadPluginGoalLifecycle();
        if (loadApplicationLifecycle != null) {
        	lifecycles.add(loadApplicationLifecycle);
        }
        return lifecycles;
    }
 
    // This should be made into a generic call/utility
    protected static Lifecycle getLoadPluginGoalLifecycle() {
        SpringResourceLoader springResourceLoader = new SpringResourceLoader(new QName("PluginResourceLoader"), APPLICATION_CONTEXT_DEFINITION, null);
    	springResourceLoader.setParentSpringResourceLoader(getPluginSpringResourceLoader());
    	return springResourceLoader;
    }
    
    protected static Config getPluginBaseConfiguration() throws Exception {
    	Properties baseProperties = PropertyLoadingFactoryBean.loadProperties();
        Config config = new JAXBConfigImpl(APPLICATION_CONFIG_LOCATION, baseProperties);
        config.parseConfig();
        LOG.info(config.toString());
        return config;
    }
    
}
