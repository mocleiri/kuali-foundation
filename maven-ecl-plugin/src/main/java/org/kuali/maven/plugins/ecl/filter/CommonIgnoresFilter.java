package org.kuali.maven.plugins.ecl.filter;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class CommonIgnoresFilter implements FileFilter {
    List<String> excludes = new ArrayList<String>();

    public CommonIgnoresFilter() {
        super();
        excludes.add("/.svn/");
        excludes.add("/target/");
        excludes.add("/config/ide/");
        excludes.add("/db/impex/");
        excludes.add("/it/");
    }

    @Override
    public boolean accept(File pathname) {
        String name = pathname.getAbsolutePath();
        for (String exclude : excludes) {
            if (contains(name, exclude)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Return true if "s" contains pattern. Ignore differences that are based only on backslash vs forward slash
     */
    protected boolean contains(String s, String pattern) {
        s = s.replace("\\", "/");
        pattern = pattern.replace("\\", "/");
        return s.contains(pattern);
    }

}
