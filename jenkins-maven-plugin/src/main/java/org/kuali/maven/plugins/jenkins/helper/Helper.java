package org.kuali.maven.plugins.jenkins.helper;

import java.util.List;

public class Helper {

    public static final boolean isEmpty(String... args) {
        return args == null || args.length == 0;
    }

    public static final void addToList(List<String> list, String... args) {
        if (isEmpty(args)) {
            return;
        }
        for (String arg : args) {
            list.add(arg);
        }
    }

}
