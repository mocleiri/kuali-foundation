package org.kuali.spring.util;

public class PropertiesLoadException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PropertiesLoadException() {
        super();
    }

    public PropertiesLoadException(String message, Throwable cause) {
        super(message, cause);
    }

    public PropertiesLoadException(String message) {
        super(message);
    }

    public PropertiesLoadException(Throwable cause) {
        super(cause);
    }

}
