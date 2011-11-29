package org.kuali.maven.mojo;

import org.apache.maven.project.MavenProject;

public class JobContext {
    String filename;
    Type type;
    MavenProject project;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type template) {
        this.type = template;
    }

    public MavenProject getProject() {
        return project;
    }

    public void setProject(MavenProject project) {
        this.project = project;
    }

}
