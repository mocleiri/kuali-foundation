package org.kuali.spring.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

public class PropertiesConverter {
    final Logger logger = LoggerFactory.getLogger(PropertiesConverter.class);

    public static final boolean DEFAULT_IS_SORT = true;
    boolean sort = DEFAULT_IS_SORT;

    PropertyLogger plogger = new PropertyLogger();

    /**
     * Perform any conversion on the supplied Properties as needed.
     * <p>
     * The default implementation invokes {@link #convert(String,String)} for each property value and replaces the
     * original with the converted value if the converted value is different
     * 
     * @param properties
     *            the Properties to convert
     */
    public void convert(Properties properties) {
        // Get a handle to the property names
        List<String> keys = new ArrayList<String>(properties.stringPropertyNames());
        // Sort if needed
        if (isSort()) {
            Collections.sort(keys);
        }
        // Iterate through the properties
        for (String key : keys) {
            // Extract the current value
            String oldValue = properties.getProperty(key);
            // Get the converted value
            String newValue = convert(key, oldValue);
            // Check them for equality
            if (!ObjectUtils.nullSafeEquals(newValue, oldValue)) {
                // The converted value is different, update our properties object
                logger.info("Converted value for '" + key + "' [{}]->[{}]", plogger.getLogValue(key, oldValue),
                        plogger.getLogValue(key, newValue));
                properties.setProperty(key, newValue);
            }
        }
    }

    /**
     * Convert the given property from the properties source to the value which should be used.
     * <p>
     * The default implementation calls {@link #convert(String)}.
     * 
     * @param propertyName
     *            the name of the property
     * @param propertyValue
     *            the original value from the properties source
     * 
     * @return the converted value, to be used for processing
     * @see #convert(String)
     */
    protected String convert(String propertyName, String propertyValue) {
        return convert(propertyValue);
    }

    /**
     * Convert the given property value from the properties source to the value which should be used.
     * <p>
     * The default implementation simply returns the original value. Can be overridden in subclasses, for example to
     * detect encrypted values and decrypt them accordingly.
     * 
     * @param originalValue
     *            the original value from the properties source
     * 
     * @return the converted value, to be used for processing
     * 
     * @see #convert(String, String)
     */
    protected String convert(String originalValue) {
        return originalValue;
    }

    public boolean isSort() {
        return sort;
    }

    public void setSort(boolean sort) {
        this.sort = sort;
    }

    public PropertyLogger getPlogger() {
        return plogger;
    }

    public void setPlogger(PropertyLogger plogger) {
        this.plogger = plogger;
    }

}
