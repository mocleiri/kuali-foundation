package org.kuali.maven.plugins.jenkins;

import java.io.File;
import java.util.Comparator;

public class FilenameComparator implements Comparator<File> {

    @Override
    public int compare(File one, File two) {
        return one.getName().compareTo(two.getName());
    }

}
