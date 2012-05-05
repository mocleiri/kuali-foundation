package org.kuali.spring.util;

public enum SystemPropertiesMode {
    SYSTEM_PROPERTIES_MODE_NEVER, // Never use system properties
    SYSTEM_PROPERTIES_MODE_FALLBACK, // Only use the system property if there is no regular property
    SYSTEM_PROPERTIES_MODE_OVERRIDE; // Always use the system property if there is one
}
