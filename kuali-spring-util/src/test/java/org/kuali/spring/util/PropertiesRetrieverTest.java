package org.kuali.spring.util;

import java.io.IOException;
import java.util.Properties;

import junit.framework.Assert;

import org.junit.Test;

public class PropertiesRetrieverTest {

    @Test
    public void test1() throws IOException {
        String key = "foo";
        String val = "bar";
        Properties properties = new Properties();
        properties.setProperty(key, val);
        ValueRetriever retriever = new PropertiesRetriever(properties);

        String resolvedProperty = retriever.retrieveValue(key);
        String unresolvedProperty = retriever.retrieveValue("A-Key-That-Does-Not-Exist");

        Assert.assertEquals(val, resolvedProperty);
        Assert.assertNull(unresolvedProperty);

    }

    @Test
    public void test2() throws IOException {
        String key = "foo";
        String val = "bar";
        Properties properties = new Properties();
        properties.setProperty(key, val);
        PropertiesRetriever retriever = new PropertiesRetriever();
        retriever.setProperties(properties);

        String resolvedProperty = retriever.retrieveValue(key);
        String unresolvedProperty = retriever.retrieveValue("A-Key-That-Does-Not-Exist");

        Assert.assertEquals(val, resolvedProperty);
        Assert.assertNull(unresolvedProperty);

        Properties resolverProperties = retriever.getProperties();
        Assert.assertEquals(properties.getProperty(key), resolverProperties.getProperty(key));

    }

}
