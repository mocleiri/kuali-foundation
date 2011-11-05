package org.kuali.maven.plugins.ecl.filter;

import java.io.FileFilter;

public class IncludeExcludeFilter extends FileFilterChain {
    FileFilter include;
    FileFilter exclude;

    public IncludeExcludeFilter(FileFilter include, FileFilter exclude) {
        super();
        if (exclude != null) {
            filters.add(exclude);
        }
        if (include != null) {
            filters.add(include);
        }
    }

}
