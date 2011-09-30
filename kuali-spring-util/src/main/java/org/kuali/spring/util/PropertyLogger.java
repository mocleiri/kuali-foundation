package org.kuali.spring.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertyLogger {
    final Logger logger = LoggerFactory.getLogger(PropertyLogger.class);

    // Matches any string containing "password" (case insensitive)
    public static final String DEFAULT_MASK_EXPRESSION = ".*((?i)password).*";
    public static final String DEFAULT_MASKED_VALUE = "******";
    public static final boolean DEFAULT_IS_MASK_PROPERTY_VALUES = true;
    public static final boolean DEFAULT_IS_FLATTEN_PROPERTY_VALUES = false;
    public static final boolean DEFAULT_IS_TRIM_PROPERTY_VALUES = false;
    public static final boolean DEFAULT_IS_LOG_IN_SORTED_ORDER = true;
    // Space
    public static final String DEFAULT_REPLACEMENT_STRING = " ";
    // Carriage return
    public static final String CR = "\r";
    // Line feed
    public static final String LF = "\n";

    // If true, log entries from a Properties object sorted by key
    boolean logInSortedOrder = DEFAULT_IS_LOG_IN_SORTED_ORDER;
    // If true, replace \n and \r when logging values
    boolean flattenPropertyValues = DEFAULT_IS_FLATTEN_PROPERTY_VALUES;
    // The value to replace linefeeds with
    String linefeedReplacement = DEFAULT_REPLACEMENT_STRING;
    // The value to replace carriage returns with
    String carriageReturnReplacement = DEFAULT_REPLACEMENT_STRING;
    // If true, call trim() on property values before logging them
    boolean trimPropertyValues = DEFAULT_IS_TRIM_PROPERTY_VALUES;
    // If true, mask values for keys that match any of the maskExpressions
    boolean maskPropertyValues = DEFAULT_IS_MASK_PROPERTY_VALUES;
    // Regular expressions to compare property keys against
    String[] maskExpressions;
    // The value to log in place of the actual value if we need to mask it
    String maskValue = DEFAULT_MASKED_VALUE;
    // Compiled representation of the regular expression
    Pattern[] patterns;

    public PropertyLogger() {
        super();
        setMaskExpression(DEFAULT_MASK_EXPRESSION);
    }

    /**
     * This setter invokes setMaskExpressions() - plural
     * 
     * @param maskExpression
     */
    public void setMaskExpression(String maskExpression) {
        setMaskExpressions(new String[] { maskExpression });
    }

    /**
     * This setter invokes Pattern.compile() for each maskExpression
     * 
     * @param maskExpressions
     */
    public void setMaskExpressions(String[] maskExpressions) {
        this.maskExpressions = maskExpressions;
        this.patterns = new Pattern[maskExpressions.length];
        for (int i = 0; i < patterns.length; i++) {
            patterns[i] = Pattern.compile(maskExpressions[i]);
        }
    }

    public String getLogEntry(String key, String value) {
        return key + "=" + getLogValue(key, value);
    }

    protected boolean isEmpty(String s) {
        if (s == null) {
            return true;
        }
        if (s.trim().length() == 0) {
            return true;
        }
        return false;
    }

    public String getLogEntry(Properties properties) {
        if (properties == null || properties.size() == 0) {
            return "No properties to log";
        }
        StringBuilder sb = new StringBuilder();
        List<String> keys = new ArrayList<String>(properties.stringPropertyNames());
        if (isLogInSortedOrder()) {
            Collections.sort(keys);
        }
        for (String key : keys) {
            String value = properties.getProperty(key);
            sb.append(getLogEntry(key, value) + "\n");
        }
        return sb.toString();
    }

    protected boolean isMatch(Pattern[] patterns, String key) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(key);
            if (matcher.matches()) {
                return true;
            }
        }
        return false;
    }

    public String getLogValue(String key, String value) {
        if (isFlattenPropertyValues()) {
            value = value.replace(LF, getLinefeedReplacement());
            value = value.replace(CR, getCarriageReturnReplacement());
        }
        if (isTrimPropertyValues()) {
            value = value.trim();
        }
        if (!isMaskPropertyValues()) {
            return value;
        }
        boolean match = isMatch(getPatterns(), key);
        if (match) {
            return getMaskValue();
        } else {
            return value;
        }
    }

    public boolean isFlattenPropertyValues() {
        return flattenPropertyValues;
    }

    public void setFlattenPropertyValues(boolean flattenPropertyValues) {
        this.flattenPropertyValues = flattenPropertyValues;
    }

    public boolean isMaskPropertyValues() {
        return maskPropertyValues;
    }

    public void setMaskPropertyValues(boolean maskPropertyValues) {
        this.maskPropertyValues = maskPropertyValues;
    }

    public String getMaskValue() {
        return maskValue;
    }

    public void setMaskValue(String maskValue) {
        this.maskValue = maskValue;
    }

    public String getLinefeedReplacement() {
        return linefeedReplacement;
    }

    public void setLinefeedReplacement(String linefeedReplacement) {
        this.linefeedReplacement = linefeedReplacement;
    }

    public String getCarriageReturnReplacement() {
        return carriageReturnReplacement;
    }

    public void setCarriageReturnReplacement(String carriageReturnReplacement) {
        this.carriageReturnReplacement = carriageReturnReplacement;
    }

    public boolean isTrimPropertyValues() {
        return trimPropertyValues;
    }

    public void setTrimPropertyValues(boolean trimPropertyValues) {
        this.trimPropertyValues = trimPropertyValues;
    }

    public String[] getMaskExpressions() {
        return maskExpressions;
    }

    public Pattern[] getPatterns() {
        return patterns;
    }

    public boolean isLogInSortedOrder() {
        return logInSortedOrder;
    }

    public void setLogInSortedOrder(boolean logInSortedOrder) {
        this.logInSortedOrder = logInSortedOrder;
    }

}
