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

import java.io.IOException;
import java.util.ArrayList;
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
    private static final String KSB_REMOTING_URL_PROPERTY_NAME = "ksb.remoting.url";
    private static final String REMOTING_URL_SUFFIX = "/remoting";
    private static Properties properties = null;

    @Override
    public Object getObject() throws Exception {
        return getProperties();
    }

    @Override
    public Class<?> getObjectType() {
        return Properties.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public synchronized static Properties getProperties() {
        if (properties == null) {
            properties = loadProperties();
        }
        return properties;
    }

    protected static Properties loadProperties() {
        try {
            // Load properties from all the necessary locations
            Properties riceProperties = getRiceProperties();
            Properties ingesterProperties = getIngesterProperties();
            Properties jdbcVendorProperties = getJdbcVendorProperties();
            Properties externalProperties = getExternalProperties();

            // Add them in the correct order
            Properties p = new Properties();
            p.putAll(riceProperties);
            p.putAll(ingesterProperties);
            p.putAll(jdbcVendorProperties);
            p.putAll(externalProperties);

            // Setup the KSB remoting URL
            String remotingUrl = p.getProperty(APPLICATION_URL_KEY) + REMOTING_URL_SUFFIX;
            p.put(KSB_REMOTING_URL_PROPERTY_NAME, remotingUrl);

            // Log some info
            LOG.info(KSB_REMOTING_URL_PROPERTY_NAME + " set to " + p.getProperty(KSB_REMOTING_URL_PROPERTY_NAME));
            LOG.info("Loaded " + p.size() + " properties");
            debug(p);

            // Return the necessary properties
            return p;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Load properties from <code>classpath:META-INF/common-config-defaults.xml</code>
     */
    protected static Properties getRiceProperties() {
        String commonConfigDefaults = "classpath:META-INF/common-config-defaults.xml";
        LOG.info("Loading " + commonConfigDefaults);
        List<String> riceConfig = Collections.singletonList(commonConfigDefaults);
        JAXBConfigImpl riceXmlConfigurer = new JAXBConfigImpl(riceConfig);
        return riceXmlConfigurer.getProperties();
    }

    /**
     * Load <code>ingester.properties</code>
     */
    protected static Properties getIngesterProperties() throws IOException {
        String path = "classpath:" + CONFIGURATION_FILE_NAME + ".properties";
        LOG.info("Loading " + path);
        Properties properties = new Properties();
        PropertyUtils.load(properties, path);
        return properties;
    }

    /**
     * Load database specific properties.
     */
    protected static Properties getJdbcVendorProperties() throws IOException {
        String value = System.getProperty("jdbc.vendor");
        if (StringUtils.isBlank(value)) {
            return new Properties();
        }
        String location = "classpath:ingester-" + value + ".properties";
        Properties p = new Properties();
        PropertyUtils.load(p, location);
        return p;
    }

    /**
     * Load external properties (if any were specified)
     */
    protected static Properties getExternalProperties() throws IOException {
        Properties properties = new Properties();
        if (!StringUtils.isBlank(ALT_CONFIG_LOCATION)) {
            PropertyUtils.load(properties, ALT_CONFIG_LOCATION);
        } else {
            LOG.info("${" + ALT_CONFIG_LOCATION_PROPERTY + "} is empty, skipping");
        }

        for (String key : properties.stringPropertyNames()) {
            String value = properties.getProperty(key);
            String resolvedValue = SystemPropertyUtils.resolvePlaceholders(value);
            properties.setProperty(key, resolvedValue);
        }
        return properties;
    }

    protected static void debug(Properties p) {
        List<String> names = new ArrayList<String>(p.stringPropertyNames());
        Collections.sort(names);
        for (String name : names) {
            String value = p.getProperty(name);
            LOG.debug(name + "=" + value);
        }
    }
}
