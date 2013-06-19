package org.kuali.maven.plugins.ecl.filter;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class FileExtensionFilter implements FileFilter {
    List<String> extensions = new ArrayList<String>();

    @Override
    public boolean accept(File file) {
        String filename = file.getName();
        for (String extension : extensions) {
            if (filename.endsWith(extension)) {
                return true;
            }
        }
        return false;
    }

    public List<String> getExtensions() {
        return extensions;
    }

    public void setExtensions(List<String> extensions) {
        this.extensions = extensions;
    }

}
