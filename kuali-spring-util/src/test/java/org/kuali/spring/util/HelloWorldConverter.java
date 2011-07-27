package org.kuali.spring.util;

/**
 * Convert everything to "Hello World"
 */
public class HelloWorldConverter extends PropertiesConverter {

    @Override
    protected String convert(String originalValue) {
        return "Hello world";
    }

}
