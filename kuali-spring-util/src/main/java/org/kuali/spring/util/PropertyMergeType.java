package org.kuali.spring.util;

public enum PropertyMergeType {
    NULL_NEW_VALUE, // New value was null, nothing changed
    IDENTICAL_VALUES, // New value is the same as the old value, nothing changed
    EXISTING_PROPERTY_WINS, // Existing property won out over the new property
    ADD, // No existing property, property was added
    OVERRIDE; // Existing property was overridden by the new property
}
