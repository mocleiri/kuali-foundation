package org.kuali.common.util.cxf;

import java.io.File;
import java.io.FileFilter;

public class PomFileFilter implements FileFilter {

    public boolean accept(File f) {
        return f.getName().equalsIgnoreCase("pom.xml");
    }

}
