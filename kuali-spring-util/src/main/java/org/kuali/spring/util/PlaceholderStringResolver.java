package org.kuali.spring.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * This class contains logic for recursively parsing/expanding PlaceholderString's. A PlaceholderString is a String that
 * contains Placeholder object(s).<br>
 * 
 * Recursion is used since the text of a Placeholder may itself be a PlaceholderString.<br>
 * 
 * Consider the text:<br>
 * 
 * <pre>
 * A ${cat} is ${${cat}.speed}.
 * </pre>
 * 
 * The PlaceholderString representing this text contains two Placeholders:<br>
 * 
 * <pre>
 * 1 - ${cat}
 * 2 - ${${cat}.speed}
 * </pre>
 * 
 * The second Placeholder has a nested PlaceholderString <code>${cat}.speed</code> which must be resolved in order to
 * locate the correct replacement value.
 * 
 * Given a properties file (or other property source) with the following mappings:<br>
 * 
 * <pre>
 * cat=cheetah
 * cheetah.speed=fast
 * </pre>
 * 
 * The text <code>A ${cat} is ${${cat}.speed}.</code> should be resolved to <code>A cheetah is fast.</code><br>
 * 
 * The object graph created to model the data is:<br>
 * 
 * <pre>
 * PlaceholderString 
 *     + text: A ${cat} is ${${cat}.speed}.
 *     + resolvedText: A cheetah is fast.
 *  - Placeholder 
 *     + value: cheetah
 *     - PlaceholderString
 *       + text: cat
 *       + resolvedText: cat
 *  - Placeholder 
 *     + value: fast
 *     - PlaceholderString
 *           + text: ${cat}.speed
 *           + resolvedText: cheetah.speed
 *        - Placeholder 
 *           + value: cheetah
 *           - PlaceholderString
 *             + text: cat
 *             + resolvedText: cat
 * </pre>
 */
public class PlaceholderStringResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlaceholderStringResolver.class);

    private static final Map<String, String> WELL_KNOWN_SIMPLE_PREFIXES = new HashMap<String, String>(4);
    public static final boolean DEFAULT_IS_IGNORE_UNRESOLVABLE_PLACEHOLDERS = false;

    static {
        WELL_KNOWN_SIMPLE_PREFIXES.put("}", "{");
        WELL_KNOWN_SIMPLE_PREFIXES.put("]", "[");
        WELL_KNOWN_SIMPLE_PREFIXES.put(")", "(");
    }

    PropertyLogger plogger = new PropertyLogger();
    String placeholderPrefix;
    String placeholderSuffix;
    String valueSeparator;
    boolean ignoreUnresolvablePlaceholders;
    String simplePrefix;

    public PlaceholderStringResolver() {
        this(DEFAULT_IS_IGNORE_UNRESOLVABLE_PLACEHOLDERS);
    }

    public PlaceholderStringResolver(boolean ignoreUnresolvablePlaceholders) {
        this(PropertyPlaceholderConfigurer.DEFAULT_PLACEHOLDER_PREFIX,
                PropertyPlaceholderConfigurer.DEFAULT_PLACEHOLDER_SUFFIX, null, ignoreUnresolvablePlaceholders);
    }

    public PlaceholderStringResolver(String placeholderPrefix, String placeholderSuffix) {
        this(placeholderPrefix, placeholderSuffix, null, DEFAULT_IS_IGNORE_UNRESOLVABLE_PLACEHOLDERS);
    }

    public PlaceholderStringResolver(String placeholderPrefix, String placeholderSuffix, String valueSeparator,
            boolean ignoreUnresolvablePlaceholders) {

        Assert.notNull(placeholderPrefix, "placeholderPrefix must not be null");
        Assert.notNull(placeholderSuffix, "placeholderSuffix must not be null");

        this.placeholderPrefix = placeholderPrefix;
        this.placeholderSuffix = placeholderSuffix;

        String simplePrefixForSuffix = WELL_KNOWN_SIMPLE_PREFIXES.get(this.placeholderSuffix);
        if (simplePrefixForSuffix != null && this.placeholderPrefix.endsWith(simplePrefixForSuffix)) {
            this.simplePrefix = simplePrefixForSuffix;
        } else {
            this.simplePrefix = this.placeholderPrefix;
        }
        this.valueSeparator = valueSeparator;
        this.ignoreUnresolvablePlaceholders = ignoreUnresolvablePlaceholders;
    }

    /**
     * Return true if the collection is null or has no elements
     */
    protected boolean isEmpty(Collection<?> c) {
        return c == null || c.size() == 0;
    }

    /**
     * Trim the prefix and suffix off of the placeholder ie change ${foo}->foo
     */
    protected String getTrimmedText(String text) {
        int prefixLength = this.placeholderPrefix.length();
        int suffixLength = this.placeholderSuffix.length();
        int beginIndex = prefixLength;
        int endIndex = text.length() - suffixLength;
        return text.substring(beginIndex, endIndex);
    }

    /**
     * Handle placeholders that have default values supplied with them ie ${jdbc.vendor=mysql}
     */
    protected String getDefaultValue(String key, ValueRetriever retriever) {
        // If they haven't specified a valueSeparator, we're done
        if (this.valueSeparator == null) {
            return null;
        }

        // If they supplied a valueSeparator but this key isn't using it, we are done
        int separatorIndex = key.indexOf(this.valueSeparator);
        if (separatorIndex == -1) {
            return null;
        }

        // Extract 'jdbc.vendor' from 'jdbc.vendor=mysql'
        String actualKey = key.substring(0, separatorIndex);

        // Extract 'mysql' from 'jdbc.vendor=mysql'
        String defaultValue = key.substring(separatorIndex + this.valueSeparator.length());

        // Give the retriever a chance to locate a value for 'jdbc.vendor'
        String value = retriever.retrieveValue(actualKey);

        // If the retriever found something, use it
        if (value != null) {
            return value;
        } else {
            // Otherwise return the default value
            return defaultValue;
        }
    }

    /**
     * Get a value for this key
     */
    protected String getRawValue(String key, ValueRetriever retriever) {
        // Give the retriever first dibs at locating a value
        String value = retriever.retrieveValue(key);

        // The retriever found something, use it
        if (value != null) {
            return value;
        }

        // There might be a default value
        return getDefaultValue(key, retriever);
    }

    /**
     * Throw an exception unless they've activated the flag for ignoring unresolved placeholders
     */
    protected void handleUnresolvedPlaceholder(String placeholderText) {
        // Value is null, meaning we could not get find a value for the Placeholder
        if (!this.ignoreUnresolvablePlaceholders) {
            // If we are not ignoring unresolved placeholder's, we are done
            throw new IllegalArgumentException("Could not resolve a value for " + placeholderText);
        } else {
            LOGGER.trace("Ignoring unresolvable placeholder: '" + placeholderText + "'");
        }
    }

    /**
     * Return the value that should be substituted for the placeholder text
     */
    protected String getValue(String key, Placeholder placeholder, ValueRetriever retriever, Set<String> resolving) {
        // Get a value for this property key
        String value = getRawValue(key, retriever);

        if (value == null) {
            // No value could be located
            String placeholderText = getPlaceholderPrefix() + placeholder.getPlaceholderString().getText()
                    + getPlaceholderSuffix();
            handleUnresolvedPlaceholder(placeholderText);
            return placeholderText;
        } else {
            // Resolve any placeholders in the value we located
            PlaceholderString phs = new PlaceholderString(value);
            resolvePlaceholderString(phs, retriever, resolving);
            return phs.getResolvedText();
        }
    }

    /**
     * Return a String that has had all of it's placeholders resolved
     */
    public String resolve(String text, ValueRetriever retriever) {
        PlaceholderString phs = new PlaceholderString(text);
        resolve(phs, retriever);
        return phs.getResolvedText();
    }

    /**
     * Resolve any Placeholders in the supplied PlaceholderString. When this method completes,
     * PlaceholderString.getResolvedText() will return the fully resolved text.
     */
    public void resolve(PlaceholderString placeholderString, ValueRetriever retriever) {
        resolvePlaceholderString(placeholderString, retriever, new HashSet<String>());
    }

    /**
     * When this method completes, PlaceholderString.getResolvedText() will return the fully resolved text
     */
    protected void resolvePlaceholderString(PlaceholderString phs, ValueRetriever retriever, Set<String> resolving) {
        // Parse the PlaceholderString to find any Placeholder's it may contain
        // phs.getPlaceholders() returns any parsed Placeholders
        parse(phs);

        // Iterate through the Placeholder objects
        StringBuilder buffer = new StringBuilder(phs.getText());
        int offset = 0;
        for (Placeholder pholder : phs.getPlaceholders()) {
            // Resolve each placeholder
            resolvePlaceholder(pholder, retriever, resolving);
            // Update the buffer with the new text
            offset += updateBuffer(buffer, pholder, offset);
        }
        // Store the new text, after all placeholders have been replaced
        phs.setResolvedText(buffer.toString());
    }

    /**
     * Update the buffer to replace the placeholder text with a value
     */
    protected int updateBuffer(StringBuilder buffer, Placeholder pholder, int offset) {
        int prefixLength = this.placeholderPrefix.length();
        int suffixLength = this.placeholderSuffix.length();
        int startIndex = pholder.getStartIndex() + offset;
        int endIndex = pholder.getEndIndex() + offset;
        buffer.replace(startIndex, endIndex, pholder.getValue());
        int textLength = pholder.getPlaceholderString().getText().length();
        int originalLength = prefixLength + textLength + suffixLength;
        int newLength = pholder.getValue().length();
        int diff = newLength - originalLength;
        return diff;
    }

    /**
     * Make sure we aren't in an infinite loop. If someone does something like this in a properties file:<br>
     * <code>
     *  a=${b} 
     *  b=${a}
     * </code>
     * 
     * We would end up with a stack overflow error without this check
     */
    protected void circularReferenceCheck(String placeholderText, Set<String> resolving) {
        LOGGER.trace("Adding '{}' to the list of placeholders being resolved", placeholderText);
        boolean added = resolving.add(placeholderText);

        if (!added) {
            // If we get here, one of the placeholders we are trying to resolve contains a reference to another
            // placeholder that we are already trying to resolve.
            throw new IllegalArgumentException("Circular reference detected on " + placeholderText);
        }
    }

    /**
     * When this method completes, Placeholder.getValue() will return the value that should be used in place of the
     * Placeholder
     */
    protected void resolvePlaceholder(Placeholder placeholder, ValueRetriever retriever, Set<String> resolving) {
        // Extract the PlaceholderString for this Placeholder
        PlaceholderString phs = placeholder.getPlaceholderString();

        // Make sure we are not in an infinite loop
        circularReferenceCheck(phs.getText(), resolving);

        // The base case is a placeholder that does not contain nested placeholder's
        if (isEmpty(phs.getPlaceholders())) {
            phs.setResolvedText(phs.getText());
        } else {
            // Recurse to handle nested placeholders
            resolvePlaceholderString(phs, retriever, resolving);
        }

        // The resolved text is our property key
        String key = phs.getResolvedText();

        // Get a value for the property
        String value = getValue(key, placeholder, retriever, resolving);

        // Store the value in the placeholder object
        placeholder.setValue(value);

        // The Placeholder is now resolved, remove it from the set
        LOGGER.trace("Removing '{}' from the list of placeholders being resolved", phs.getText());
        resolving.remove(phs.getText());
    }

    /**
     * Parse the PlaceholderString to get the List of Placeholder objects (if any). If there are none, an empty list is
     * returned.
     */
    protected void parse(PlaceholderString phs) {

        // Extract the text we will be parsing
        String text = phs.getText();

        // Locate the first occurrence of our prefix
        int startIndex = text.indexOf(this.placeholderPrefix);
        if (startIndex == -1) {
            LOGGER.trace("Skip parsing.  No placeholders found in [{}]", text);
        }

        // Storage for the placeholders we find
        List<Placeholder> placeholders = new ArrayList<Placeholder>();

        // While there is at least one prefix remaining
        while (startIndex != -1) {
            // Attempt to get a placeholder object
            Placeholder placeholder = getPlaceholder(text, startIndex);

            // No placeholder could be found
            if (placeholder == null) {
                break;
            }

            // Add our placeholder to the list
            placeholders.add(placeholder);

            // Start looking again, skipping past the placeholder we just found
            int newFromIndex = placeholder.getEndIndex();

            // Attempt to locate another prefix
            startIndex = text.indexOf(this.placeholderPrefix, newFromIndex);
        }

        // Store the Placeholder's on the PlaceHolderString
        phs.setPlaceholders(placeholders);
    }

    /**
     * Get a Placeholder object representing the first placeholder after startIndex
     */
    protected Placeholder getPlaceholder(String source, int startIndex) {
        int suffixLength = this.placeholderSuffix.length();

        // Attempt to locate the end of the placeholder
        int endIndex = findPlaceholderEndIndex(source, startIndex);

        if (endIndex == -1) {
            // Could not locate the end
            return null;
        } else {
            // Move the endIndex past the suffix
            endIndex = endIndex + suffixLength;
        }

        // The placeholder exactly as it appears in the source string ie ${foo}
        String text = source.substring(startIndex, endIndex);

        // Trim off prefix and suffix
        String trimmedText = getTrimmedText(text);

        // Create a new PlaceholderString
        PlaceholderString phs = new PlaceholderString(trimmedText);

        // Recursive call to parse the new PlaceholderString (and any Placeholder's it may contain)
        parse(phs);

        // Populate a placeholder object
        Placeholder placeholder = new Placeholder();
        placeholder.setStartIndex(startIndex);
        placeholder.setEndIndex(endIndex);
        placeholder.setPlaceholderString(phs);
        return placeholder;
    }

    /**
     * Return true if the index into the buffer is pointing at the suffix, false otherwise
     */
    protected boolean haveArrivedAtSuffix(CharSequence buf, int index) {
        return StringUtils.substringMatch(buf, index, this.placeholderSuffix);
    }

    /**
     * Return true if the index into the buffer is pointing at the simplePrefix, false otherwise
     */
    protected boolean haveArrivedAtPrefix(CharSequence buf, int index) {
        return StringUtils.substringMatch(buf, index, this.simplePrefix);
    }

    /**
     * Given a buffer and a startingIndex, find the suffix that matches our prefix. The default prefix is "${" and the
     * default suffix is "}"
     */
    protected int findPlaceholderEndIndex(CharSequence buf, int startIndex) {
        // Skip past the prefix
        int index = startIndex + this.placeholderPrefix.length();

        // Track nested place holders as we encounter them
        int nestedPlaceHolderCount = 0;

        // Iterate through the buffer looking for the closing bracket that matches our opening bracket
        while (index < buf.length()) {
            // Check to see if we've arrived at a closing bracket
            if (haveArrivedAtSuffix(buf, index)) {

                // If we aren't nested, return the index, nothing more to do
                if (nestedPlaceHolderCount == 0) {
                    return index;
                }

                // Otherwise, decrement the nested count
                nestedPlaceHolderCount--;

                // and skip past the nested suffix
                index = index + this.placeholderSuffix.length();

            } else if (haveArrivedAtPrefix(buf, index)) {
                // If we get here, we've encountered a nested placeholder

                // Increment the counter
                nestedPlaceHolderCount++;

                // Skip past the nested prefix
                index = index + this.simplePrefix.length();
            } else {
                // We are not on a prefix or a suffix, keep searching
                index++;
            }
        }

        // We never found a suffix to match our prefix
        return -1;
    }

    public String getPlaceholderPrefix() {
        return placeholderPrefix;
    }

    public void setPlaceholderPrefix(String placeholderPrefix) {
        this.placeholderPrefix = placeholderPrefix;
    }

    public String getPlaceholderSuffix() {
        return placeholderSuffix;
    }

    public void setPlaceholderSuffix(String placeholderSuffix) {
        this.placeholderSuffix = placeholderSuffix;
    }

    public String getValueSeparator() {
        return valueSeparator;
    }

    public void setValueSeparator(String valueSeparator) {
        this.valueSeparator = valueSeparator;
    }

    public boolean isIgnoreUnresolvablePlaceholders() {
        return ignoreUnresolvablePlaceholders;
    }

    public void setIgnoreUnresolvablePlaceholders(boolean ignoreUnresolvablePlaceholders) {
        this.ignoreUnresolvablePlaceholders = ignoreUnresolvablePlaceholders;
    }

    public PropertyLogger getPlogger() {
        return plogger;
    }

    public void setPlogger(PropertyLogger plogger) {
        this.plogger = plogger;
    }

    public String getSimplePrefix() {
        return simplePrefix;
    }

    public void setSimplePrefix(String simplePrefix) {
        this.simplePrefix = simplePrefix;
    }

}
