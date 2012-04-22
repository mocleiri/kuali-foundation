package org.kuali.maven.plugins.ingester;

import java.io.File;

public class DirectoryStructure {
    File pendingDir;
    File completedDir;
    File problemDir;

    public File getPendingDir() {
        return pendingDir;
    }

    public void setPendingDir(File pendingDir) {
        this.pendingDir = pendingDir;
    }

    public File getCompletedDir() {
        return completedDir;
    }

    public void setCompletedDir(File completedDir) {
        this.completedDir = completedDir;
    }

    public File getProblemDir() {
        return problemDir;
    }

    public void setProblemDir(File problemDir) {
        this.problemDir = problemDir;
    }

}
