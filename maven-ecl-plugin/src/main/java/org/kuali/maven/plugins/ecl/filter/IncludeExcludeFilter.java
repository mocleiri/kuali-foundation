package org.kuali.maven.plugins.ecl.filter;

import java.io.File;
import java.io.FileFilter;

public class IncludeExcludeFilter implements FileFilter {
    FileFilter include;
    FileFilter exclude;

    public IncludeExcludeFilter() {
        this(null, null);
    }

    public IncludeExcludeFilter(FileFilter include, FileFilter exclude) {
        super();
        this.include = include;
        this.exclude = exclude;
    }

    @Override
    public boolean accept(File file) {
        FileFilterList ffl = getFileFilterList();
        return ffl.accept(file);
    }

    protected FileFilterList getFileFilterList() {
        FileFilterList ffl = new FileFilterList();
        if (exclude != null) {
            ffl.add(exclude);
        }
        if (include != null) {
            ffl.add(include);
        }
        return ffl;
    }

    public FileFilter getInclude() {
        return include;
    }

    public void setInclude(FileFilter include) {
        this.include = include;
    }

    public FileFilter getExclude() {
        return exclude;
    }

    public void setExclude(FileFilter exclude) {
        this.exclude = exclude;
    }

}
