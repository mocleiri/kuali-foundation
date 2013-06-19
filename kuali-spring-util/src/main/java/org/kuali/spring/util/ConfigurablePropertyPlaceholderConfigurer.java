package org.kuali.spring.util;

import java.util.Properties;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;

public class ConfigurablePropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
    public static final boolean DEFAULT_IS_SEARCH_SYSTEM_ENVIRONMENT = true;
    public static final boolean DEFAULT_IS_IGNORE_UNRESOLVABLE_PLACEHOLDERS = false;
    public static final boolean DEFAULT_IS_LOCAL_OVERRIDE = false;
    public static final boolean DEFAULT_IS_IGNORE_RESOURCE_NOT_FOUND = false;
    private String placeholderPrefix = DEFAULT_PLACEHOLDER_PREFIX;
    private String placeholderSuffix = DEFAULT_PLACEHOLDER_SUFFIX;
    private String valueSeparator = DEFAULT_VALUE_SEPARATOR;
    private int systemPropertiesMode = SYSTEM_PROPERTIES_MODE_FALLBACK;
    private boolean searchSystemEnvironment = DEFAULT_IS_SEARCH_SYSTEM_ENVIRONMENT;
    private boolean ignoreUnresolvablePlaceholders = DEFAULT_IS_IGNORE_UNRESOLVABLE_PLACEHOLDERS;
    private boolean localOverride = DEFAULT_IS_LOCAL_OVERRIDE;
    private boolean ignoreResourceNotFound = DEFAULT_IS_IGNORE_RESOURCE_NOT_FOUND;
    private String nullValue;
    private String beanName;
    private Properties[] localProperties;
    private Resource[] locations;
    private String fileEncoding;
    private PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();

    private BeanFactory beanFactory;

    public String getSystemPropertiesModeName() {
        if (getSystemPropertiesMode() == SYSTEM_PROPERTIES_MODE_FALLBACK) {
            return "SYSTEM_PROPERTIES_MODE_FALLBACK";
        } else if (getSystemPropertiesMode() == SYSTEM_PROPERTIES_MODE_NEVER) {
            return "SYSTEM_PROPERTIES_MODE_NEVER";
        } else if (getSystemPropertiesMode() == SYSTEM_PROPERTIES_MODE_OVERRIDE) {
            return "SYSTEM_PROPERTIES_MODE_OVERRIDE";
        } else {
            throw new IllegalArgumentException("Unknown system properties mode");
        }

    }

    @Override
    public void setPlaceholderPrefix(String placeholderPrefix) {
        this.placeholderPrefix = placeholderPrefix;
        super.setPlaceholderPrefix(placeholderPrefix);
    }

    @Override
    public void setPlaceholderSuffix(String placeholderSuffix) {
        this.placeholderSuffix = placeholderSuffix;
        super.setPlaceholderSuffix(placeholderSuffix);
    }

    @Override
    public void setValueSeparator(String valueSeparator) {
        this.valueSeparator = valueSeparator;
        super.setValueSeparator(valueSeparator);
    }

    @Override
    public void setSystemPropertiesMode(int systemPropertiesMode) {
        this.systemPropertiesMode = systemPropertiesMode;
        super.setSystemPropertiesMode(systemPropertiesMode);
    }

    @Override
    public void setSearchSystemEnvironment(boolean searchSystemEnvironment) {
        this.searchSystemEnvironment = searchSystemEnvironment;
        super.setSearchSystemEnvironment(searchSystemEnvironment);
    }

    @Override
    public void setIgnoreUnresolvablePlaceholders(boolean ignoreUnresolvablePlaceholders) {
        this.ignoreUnresolvablePlaceholders = ignoreUnresolvablePlaceholders;
        super.setIgnoreUnresolvablePlaceholders(ignoreUnresolvablePlaceholders);
    }

    @Override
    public void setNullValue(String nullValue) {
        this.nullValue = nullValue;
        super.setNullValue(nullValue);
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
        super.setBeanName(beanName);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
        super.setBeanFactory(beanFactory);
    }

    @Override
    public void setSystemPropertiesModeName(String systemPropertiesModeName) {
        super.setSystemPropertiesModeName(systemPropertiesModeName);
    }

    @Override
    public void setLocations(Resource[] locations) {
        this.locations = locations;
        super.setLocations(locations);
    }

    @Override
    public void setLocalOverride(boolean localOverride) {
        this.localOverride = localOverride;
        super.setLocalOverride(localOverride);
    }

    @Override
    public void setIgnoreResourceNotFound(boolean ignoreResourceNotFound) {
        this.ignoreResourceNotFound = ignoreResourceNotFound;
        super.setIgnoreResourceNotFound(ignoreResourceNotFound);
    }

    @Override
    public void setFileEncoding(String fileEncoding) {
        this.fileEncoding = fileEncoding;
        super.setFileEncoding(fileEncoding);
    }

    @Override
    public void setPropertiesPersister(PropertiesPersister propertiesPersister) {
        this.propertiesPersister = propertiesPersister;
        super.setPropertiesPersister(propertiesPersister);
    }

    public String getPlaceholderSuffix() {
        return placeholderSuffix;
    }

    public String getValueSeparator() {
        return valueSeparator;
    }

    public int getSystemPropertiesMode() {
        return systemPropertiesMode;
    }

    public boolean isSearchSystemEnvironment() {
        return searchSystemEnvironment;
    }

    public boolean isIgnoreUnresolvablePlaceholders() {
        return ignoreUnresolvablePlaceholders;
    }

    public String getNullValue() {
        return nullValue;
    }

    public String getBeanName() {
        return beanName;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public Properties[] getLocalProperties() {
        return localProperties;
    }

    public void setLocalProperties(Properties[] localProperties) {
        this.localProperties = localProperties;
    }

    public Resource[] getLocations() {
        return locations;
    }

    public boolean isLocalOverride() {
        return localOverride;
    }

    public boolean isIgnoreResourceNotFound() {
        return ignoreResourceNotFound;
    }

    public String getFileEncoding() {
        return fileEncoding;
    }

    public PropertiesPersister getPropertiesPersister() {
        return propertiesPersister;
    }

    public String getPlaceholderPrefix() {
        return placeholderPrefix;
    }

}
