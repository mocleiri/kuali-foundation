package org.springframework.beans;

import java.io.File;
import java.io.FileFilter;

public class FilenameContainsDotXFilter implements FileFilter {

    @Override
    public boolean accept(File pathname) {
        return pathname.getAbsolutePath().contains(".x");
    }

}
