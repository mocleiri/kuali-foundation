package org.kuali.maven.plugins.jenkins.helper;

import java.io.File;
import java.io.FileFilter;

public class XmlFileFilter implements FileFilter {

    @Override
    public boolean accept(File file) {
        if (file.isDirectory()) {
            return false;
        }
        String name = file.getName();
        return name.endsWith(JenkinsHelper.XML_EXTENSION);
    }

}
