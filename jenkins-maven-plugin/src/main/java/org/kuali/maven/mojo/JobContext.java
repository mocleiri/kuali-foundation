package org.kuali.maven.mojo;

import org.apache.maven.project.MavenProject;

public class JobContext {
    String filename;
    String type;
    MavenProject project;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public MavenProject getProject() {
        return project;
    }

    public void setProject(MavenProject project) {
        this.project = project;
    }
}
