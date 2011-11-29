package org.kuali.maven.common;

import java.util.Properties;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.util.PropertyPlaceholderHelper;

public class PropertiesUtilsTest {

    @Test
    public void test() {
        Properties properties = new Properties();
        properties.setProperty("a", "1");
        properties.setProperty("b", "2");
        properties.setProperty("c", "3");
        properties.setProperty("foo", "${a}");

        String s = "The value is ${foo}";
        PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper("${", "}");
        String expected = "The value is 1";
        String resolved = helper.replacePlaceholders(s, properties);
        Assert.assertEquals(expected, resolved);
    }

}
