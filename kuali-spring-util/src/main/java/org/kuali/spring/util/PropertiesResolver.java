package org.kuali.spring.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

public class PropertiesResolver extends PlaceholderStringResolver {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesResolver.class);
    public static final boolean DEFAULT_IS_SORT = true;
    boolean sort = DEFAULT_IS_SORT;

    protected class ResolvePropertyContext {
        public ResolvePropertyContext(List<String> keys, String key, Properties properties,
                Properties resolvedProperties, ValueRetriever retriever) {
            super();
            this.keys = keys;
            this.key = key;
            this.properties = properties;
            this.resolvedProperties = resolvedProperties;
            this.retriever = retriever;
        }

        List<String> keys;
        String key;
        Properties properties;
        Properties resolvedProperties;
        ValueRetriever retriever;

        public List<String> getKeys() {
            return keys;
        }

        public void setKeys(List<String> keys) {
            this.keys = keys;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public Properties getProperties() {
            return properties;
        }

        public void setProperties(Properties properties) {
            this.properties = properties;
        }

        public Properties getResolvedProperties() {
            return resolvedProperties;
        }

        public void setResolvedProperties(Properties resolvedProperties) {
            this.resolvedProperties = resolvedProperties;
        }

        public ValueRetriever getRetriever() {
            return retriever;
        }

        public void setRetriever(ValueRetriever retriever) {
            this.retriever = retriever;
        }
    }

    /**
     * Resolve any placeholders in the Properties object passed in
     */
    public Properties resolve(Properties properties) {
        return resolve(properties, new PropertiesRetriever(properties));
    }

    /**
     * Resolve any placeholders in the Properties object passed in, using the ValueRetriever passed in to locate
     * replacement values for placeholder text
     */
    public Properties resolve(Properties properties, ValueRetriever retriever) {
        // Storage for resolved properties
        Properties resolvedProperties = new Properties();
        // Store the keys in a list so we can sort them if needed
        List<String> keys = new ArrayList<String>(properties.stringPropertyNames());
        if (isSort()) {
            // Resolve them in sorted order
            Collections.sort(keys);
        }
        // Iterate through the keys, resolving both keys and values as we go
        for (String key : keys) {
            resolveProperty(new ResolvePropertyContext(keys, key, properties, resolvedProperties, retriever));
        }
        return resolvedProperties;
    }

    protected void resolveProperty(ResolvePropertyContext ctx) {
        String key = ctx.getKey();
        Properties properties = ctx.getProperties();
        // First resolve the key
        String resolvedKey = resolve(key, ctx.getRetriever());

        // Emit an info level message when a key is resolved to a new value
        if (!key.equals(resolvedKey)) {
            LOGGER.info("Resolved key [{}]->[{}]", key, resolvedKey);
        }

        // Obtain a value for the key
        String value = properties.getProperty(key);
        // Resolve any placeholders in the value
        String resolvedValue = resolve(value, ctx.getRetriever());

        // Check for duplicates
        duplicatePropertyCheck(ctx, resolvedKey, resolvedValue);

        // Emit an info level message when a value gets resolved to something new
        if (!value.equals(resolvedValue)) {
            LOGGER.info("Resolved value for '" + resolvedKey + "'  [{}]->[{}]",
                    plogger.getLogValue(resolvedKey, value), plogger.getLogValue(resolvedKey, resolvedValue));
        }
        LOGGER.debug("Adding resolved property {}=[{}]", resolvedKey, plogger.getLogValue(resolvedKey, resolvedValue));
        // Store the resolved key/value pair
        ctx.getResolvedProperties().setProperty(resolvedKey, resolvedValue);
    }

    /**
     * A duplicate property can occur when resolving placeholders inside a key.
     * 
     * For example, given a properties file with the following entries:<br>
     * <code>
     * cat=lion
     * lion.speed=fast
     * ${cat}.speed=slow
     * </code>
     * 
     * The key ${cat}.speed will resolve to 'lion.speed' which duplicates the original entry in the properties file
     */
    protected void duplicatePropertyCheck(ResolvePropertyContext ctx, String resolvedKey, String resolvedValue) {
        String rawKey = ctx.getKey();
        Properties properties = ctx.getProperties();
        // The keys are the same, can't be a duplicate property, nothing to do
        if (rawKey.equals(resolvedKey)) {
            return;
        }

        // The keys are not the same, but resolvedKey is a new key, nothing to do
        if (!ctx.getKeys().contains(resolvedKey)) {
            return;
        }

        // If we get this far, the keys are not the same, and resolved key points to a property that already exists

        // Extract the existing value
        String existingValue = properties.getProperty(resolvedKey);

        // Check the values
        if (ObjectUtils.nullSafeEquals(existingValue, resolvedValue)) {
            // The values are the same, just emit a warning and proceed
            LOGGER.warn("Duplicate property detected for '" + resolvedKey + "'.  '" + rawKey + "' resolved to '"
                    + resolvedKey + "' which already has a value. Both values are the same: [{}]",
                    plogger.getLogValue(resolvedKey, existingValue));
            return;
        } else {
            // Otherwise we have an issue
            StringBuilder sb = new StringBuilder();
            sb.append("Duplicate property value detected for '" + resolvedKey + "'.");
            sb.append("  The key '" + rawKey + "' resolved to '" + resolvedKey
                    + "' and there is a value for that key already present.");
            sb.append("  Existing value:[" + existingValue + "]  New Value:[" + resolvedValue + "]");
            throw new IllegalArgumentException(sb.toString());
        }
    }

    public boolean isSort() {
        return sort;
    }

    public void setSort(boolean sort) {
        this.sort = sort;
    }

}
