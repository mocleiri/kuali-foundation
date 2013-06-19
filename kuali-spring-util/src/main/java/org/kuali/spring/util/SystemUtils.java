package org.kuali.spring.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SystemUtils {
    final static Logger LOGGER = LoggerFactory.getLogger(SystemUtils.class);

    public static Map<String, String> getEnvironmentIgnoreExceptions() {
        try {
            return System.getenv();
        } catch (Throwable e) {
            LOGGER.warn("Unable to access environment.  {}", e.getMessage());
            return new HashMap<String, String>();
        }
    }

    public static Properties getSystemPropertiesIgnoreExceptions() {
        try {
            return System.getProperties();
        } catch (Throwable e) {
            LOGGER.warn("Unable to access system properties.  {}", e.getMessage());
            return new Properties();
        }
    }

    public static final String getSystemPropertyIgnoreExceptions(String key) {
        try {
            return System.getProperty(key);
        } catch (Throwable e) {
            LOGGER.warn("Unable to access system property '{}': {}", key, e.getMessage());
            return null;
        }
    }

    public static final String getEnvironmentPropertyIgnoreExceptions(String key) {
        try {
            return System.getenv(key);
        } catch (Throwable e) {
            LOGGER.warn("Unable to access environment property '{}': {}", key, e.getMessage());
            return null;
        }
    }

}
