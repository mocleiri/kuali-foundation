package org.kuali.maven.plugins.ecl.filter;

import java.io.File;
import java.io.FileFilter;

public class FilenameContainsDotXFilter implements FileFilter {

    @Override
    public boolean accept(File file) {
        return file.getName().contains(".x");
    }

}
