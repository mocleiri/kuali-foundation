package org.kuali.maven.plugins;

import java.io.File;

public class Deployable {
    GAV gav;
    File file;

    public GAV getGav() {
        return gav;
    }

    public void setGav(GAV gav) {
        this.gav = gav;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
