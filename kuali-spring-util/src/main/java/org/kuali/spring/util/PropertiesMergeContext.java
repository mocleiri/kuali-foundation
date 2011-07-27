package org.kuali.spring.util;

import java.util.Properties;

public class PropertiesMergeContext {
    public static final boolean DEFAULT_IS_SORT = true;
    public static final boolean DEFAULT_IS_OVERRIDE = true;
    boolean override = DEFAULT_IS_OVERRIDE;
    boolean sort = DEFAULT_IS_SORT;
    Properties currentProperties;
    Properties newProperties;
    PropertySource source;

    public PropertiesMergeContext() {
        this(null, null, DEFAULT_IS_OVERRIDE, null, DEFAULT_IS_SORT);
    }

    public PropertiesMergeContext(Properties currentProperties, Properties newProperties, PropertySource source) {
        this(currentProperties, newProperties, DEFAULT_IS_OVERRIDE, source, DEFAULT_IS_SORT);
    }

    public PropertiesMergeContext(Properties currentProperties, Properties newProperties, boolean override,
            PropertySource source) {
        this(currentProperties, newProperties, override, source, DEFAULT_IS_SORT);
    }

    public PropertiesMergeContext(Properties currentProperties, Properties newProperties, boolean override,
            PropertySource source, boolean sort) {
        super();
        this.currentProperties = currentProperties;
        this.newProperties = newProperties;
        this.override = override;
        this.source = source;
        this.sort = sort;
    }

    public Properties getCurrentProperties() {
        return currentProperties;
    }

    public void setCurrentProperties(Properties currentProperties) {
        this.currentProperties = currentProperties;
    }

    public Properties getNewProperties() {
        return newProperties;
    }

    public void setNewProperties(Properties newProperties) {
        this.newProperties = newProperties;
    }

    public boolean isOverride() {
        return override;
    }

    public void setOverride(boolean override) {
        this.override = override;
    }

    public boolean isSort() {
        return sort;
    }

    public void setSort(boolean sort) {
        this.sort = sort;
    }

    public PropertySource getSource() {
        return source;
    }

    public void setSource(PropertySource source) {
        this.source = source;
    }
}
