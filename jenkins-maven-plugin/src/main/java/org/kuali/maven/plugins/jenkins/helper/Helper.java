package org.kuali.maven.plugins.jenkins.helper;

import java.util.List;

import org.codehaus.plexus.util.StringUtils;

public class Helper {

    /**
     * Return true if any of the args passed in are blank
     */
    public static final boolean anyAreBlank(String... args) {
        for (String arg : args) {
            if (StringUtils.isBlank(arg)) {
                return true;
            }
        }
        return false;
    }

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
