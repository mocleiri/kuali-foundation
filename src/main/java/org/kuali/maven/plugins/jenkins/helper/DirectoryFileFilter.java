package org.kuali.maven.plugins.jenkins.helper;

import java.io.File;
import java.io.FileFilter;

public class DirectoryFileFilter implements FileFilter {

    @Override
    public boolean accept(File file) {
        return file.isDirectory();
    }

}
