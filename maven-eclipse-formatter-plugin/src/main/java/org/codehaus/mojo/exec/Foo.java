package org.codehaus.mojo.exec;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class Foo {
    public static void main(String[] args) {
        Properties props = System.getProperties();
        List<String> keys = new ArrayList<String>(props.stringPropertyNames());
        Collections.sort(keys);
        for (String key : keys) {
            if (key.startsWith("os") || key.startsWith("java")) {
                System.out.println(key + "=" + props.getProperty(key));
            } else {
                System.out.println(key);
            }
        }
        System.out.println(props.getProperty("java.home"));

    }
}
