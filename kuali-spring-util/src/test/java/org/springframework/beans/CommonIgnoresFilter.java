package org.springframework.beans;

import java.io.File;
import java.io.FileFilter;

public class CommonIgnoresFilter implements FileFilter {

    @Override
    public boolean accept(File pathname) {
        String name = pathname.getAbsolutePath();
        if (name.contains("/.svn/")) {
            return true;
        }
        if (contains(name, "/config/ide/")) {
            return true;
        }
        if (contains(name, "/db/impex/")) {
            return true;
        }
        if (contains(name, "/target/")) {
            return true;
        }
        return false;
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
