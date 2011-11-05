package org.kuali.maven.plugins.ecl.filter;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class FileFilterChain implements FileFilter {
    List<FileFilter> filters = new ArrayList<FileFilter>();

    @Override
    public boolean accept(File file) {
        for (FileFilter filter : filters) {
            if (!filter.accept(file)) {
                return false;
            }
        }
        return true;
    }

    public List<FileFilter> getFilters() {
        return filters;
    }

    public void setFilters(List<FileFilter> filters) {
        this.filters = filters;
    }

}
