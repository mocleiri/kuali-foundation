package org.kuali.maven.plugins;

import java.io.File;

import org.apache.maven.artifact.Artifact;

public class Deployable {
    Artifact artifact;
    File file;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Artifact getArtifact() {
        return artifact;
    }

    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
    }
}
