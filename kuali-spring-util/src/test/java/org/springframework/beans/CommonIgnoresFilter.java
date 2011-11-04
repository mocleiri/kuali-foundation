package org.springframework.beans;

import java.io.File;
import java.io.FileFilter;

public class CommonIgnoresFilter implements FileFilter {

    @Override
    public boolean accept(File pathname) {
        String name = pathname.getAbsolutePath();
        if (name.contains(".svn")) {
            return true;
        }
        if (name.contains("config\\ide")) {
            return true;
        }
        if (name.contains("db\\impex")) {
            return true;
        }
        return false;
    }

}
