package org.kuali.spring.util;

public enum PropertySource {
    SYSTEM, // A system property
    ENVIRONMENT, // An environment property
    RESOURCE, // A property loaded from a Resource
    LOCAL, // A property from a properties object injected into the factory bean
    OTHER; // Some place besides one of the above
}
