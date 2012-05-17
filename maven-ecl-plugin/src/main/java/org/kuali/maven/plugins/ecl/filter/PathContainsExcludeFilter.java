package org.kuali.maven.plugins.ecl.filter;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PathContainsExcludeFilter implements FileFilter {
    List<String> excludes = new ArrayList<String>();

    @Override
    public boolean accept(File file) {
        String path = getPath(file);
        for (String exclude : excludes) {
            if (contains(path, exclude)) {
                return false;
            }
        }
        return true;
    }

    protected String getPath(File file) {
        try {
            return file.getCanonicalPath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Return true if "path" contains "pattern".
     *
     * Ignore differences due only to backslash vs forward slash
     */
    protected boolean contains(String path, String pattern) {
        path = path.replace("\\", "/");
        pattern = pattern.replace("\\", "/");
        return path.contains(pattern);
    }

    public List<String> getExcludes() {
        return excludes;
    }

    public void setExcludes(List<String> excludes) {
        this.excludes = excludes;
    }

}
