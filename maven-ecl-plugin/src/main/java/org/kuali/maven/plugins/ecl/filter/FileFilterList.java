package org.kuali.maven.plugins.ecl.filter;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Iterator;

public class FileFilterList extends ArrayList<FileFilter> implements FileFilter {

    /**
     *
     */
    private static final long serialVersionUID = -6077750135036075422L;

    @Override
    public boolean accept(File file) {
        Iterator<FileFilter> itr = this.iterator();
        while (itr.hasNext()) {
            FileFilter filter = itr.next();
            if (filter.accept(file)) {
                return true;
            }
        }
        return false;
    }

}
