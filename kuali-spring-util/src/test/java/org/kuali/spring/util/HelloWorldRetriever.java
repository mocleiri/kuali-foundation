package org.kuali.spring.util;

/**
 * Always retrieve the string "Hello World"
 */
public class HelloWorldRetriever extends PropertiesRetriever {

    @Override
    public String retrieveValue(String key) {
        return "Hello World";
    }

}
