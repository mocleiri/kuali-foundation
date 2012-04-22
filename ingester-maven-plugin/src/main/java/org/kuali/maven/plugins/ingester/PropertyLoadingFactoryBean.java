/*
 * Copyright 2007 The Kuali Foundation
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.core.config.JAXBConfigImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.util.SystemPropertyUtils;

public class PropertyLoadingFactoryBean implements FactoryBean {
    protected static final Logger LOG = LoggerFactory.getLogger(PropertyLoadingFactoryBean.class);
    public static final String ENVIRONMENT_KEY = "environment";
    public static final String APPLICATION_URL_KEY = "application.url";
    private static final String CONFIGURATION_FILE_NAME = "ingester";
    private static final String ALT_CONFIG_LOCATION_PROPERTY = "ingester.config.location";
    private static final String ALT_CONFIG_LOCATION = System.getProperty(ALT_CONFIG_LOCATION_PROPERTY);
    private static final String PROPERTY_FILE_NAMES_KEY = "property.files";
    private static final Properties BASE_PROPERTIES = new Properties();
    private static final String KSB_REMOTING_URL_PROPERTY_NAME = "ksb.remoting.url";
    private static final String REMOTING_URL_SUFFIX = "/remoting";
    private Properties props = new Properties();

    @Override
    public Object getObject() throws Exception {
        loadBaseProperties();
        props.putAll(BASE_PROPERTIES);
        loadPropertyList(props, PROPERTY_FILE_NAMES_KEY);
        props.put(KSB_REMOTING_URL_PROPERTY_NAME, props.getProperty(APPLICATION_URL_KEY) + REMOTING_URL_SUFFIX);
        LOG.info(KSB_REMOTING_URL_PROPERTY_NAME + " set to " + props.getProperty(KSB_REMOTING_URL_PROPERTY_NAME));
        LOG.info("Loaded " + props.size() + " properties");
        if (LOG.isDebugEnabled()) {
            List<String> names = new ArrayList<String>(props.stringPropertyNames());
            Collections.sort(names);
            for (String name : names) {
                String value = props.getProperty(name);
                LOG.debug(name + "=" + value);
            }
        }
        return props;
    }

    @Override
    public Class<?> getObjectType() {
        return Properties.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    protected static void loadPropertyList(Properties props, String listPropertyName) throws IOException {
        for (String propertyFileName : getBaseListProperty(listPropertyName)) {
            LOG.info("Loading " + propertyFileName);
            PropertiesUtils.loadProperties(props, propertyFileName);
        }
    }

    public static String getBaseProperty(String propertyName) {
        loadBaseProperties();
        return BASE_PROPERTIES.getProperty(propertyName);
    }

    protected static List<String> getBaseListProperty(String propertyName) {
        loadBaseProperties();
        return Arrays.asList(BASE_PROPERTIES.getProperty(propertyName).split(","));
    }

    protected static void loadBaseProperties() {
        if (!BASE_PROPERTIES.isEmpty()) {
            return;
        }
        try {
            String commonConfigDefaults = "classpath:META-INF/common-config-defaults.xml";
            String basePropertiesPath = "classpath:" + CONFIGURATION_FILE_NAME + ".properties";
            LOG.info("Loading " + commonConfigDefaults);
            List<String> riceXmlConfigurations = new ArrayList<String>();
            riceXmlConfigurations.add(commonConfigDefaults);
            JAXBConfigImpl riceXmlConfigurer = new JAXBConfigImpl(riceXmlConfigurations);
            BASE_PROPERTIES.putAll(riceXmlConfigurer.getProperties());
            LOG.info("Loading " + basePropertiesPath);
            PropertiesUtils.loadProperties(BASE_PROPERTIES, basePropertiesPath);
            loadExternalProperties();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Load configuration external to the plugin
     */
    protected static void loadExternalProperties() throws IOException {
        Properties properties = new Properties();
        if (!StringUtils.isBlank(ALT_CONFIG_LOCATION)) {
            loadPropertiesFromLocation(properties, ALT_CONFIG_LOCATION);
        } else {
            LOG.info("${" + ALT_CONFIG_LOCATION_PROPERTY + "} is empty, skipping");
        }

        for (String key : properties.stringPropertyNames()) {
            String value = properties.getProperty(key);
            String resolvedValue = SystemPropertyUtils.resolvePlaceholders(value);
            properties.setProperty(key, resolvedValue);
        }

        BASE_PROPERTIES.putAll(properties);
    }

    protected static void loadPropertiesFromLocation(Properties props, String location) throws IOException {
        if (!PropertiesUtils.exists(location)) {
            LOG.info("Skipping '" + location + "'  Resource does not exist");
            return;
        } else {
            LOG.info("Loading " + location);
        }
        PropertiesUtils.loadProperties(props, location);
    }

    public static void clear() {
        BASE_PROPERTIES.clear();
    }
}
