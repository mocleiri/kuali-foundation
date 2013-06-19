package org.springframework.beans;

import java.io.File;
import java.io.FileFilter;

public class CommonIgnoresFilter implements FileFilter {

    @Override
    public boolean accept(File pathname) {
        String name = pathname.getAbsolutePath();

        if (contains(name, "/.svn/")) {
            return false;
        }
        if (contains(name, "/config/ide/")) {
            return false;
        }
        if (contains(name, "/db/impex/")) {
            return false;
        }
        if (contains(name, "/target/")) {
            return false;
        }
        if (contains(name, "/it/")) {
            return false;
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
