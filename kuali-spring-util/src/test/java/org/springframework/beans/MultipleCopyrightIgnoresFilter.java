package org.springframework.beans;

import java.io.File;

public class MultipleCopyrightIgnoresFilter extends CommonIgnoresFilter {

    @Override
    public boolean accept(File file) {
        String path = file.getAbsolutePath();

        if (path.endsWith(".xsd") && contains(path, "/impl/src/main/resources/schema/")) {
            return false;
        } else {
            return super.accept(file);
        }
    }
}
