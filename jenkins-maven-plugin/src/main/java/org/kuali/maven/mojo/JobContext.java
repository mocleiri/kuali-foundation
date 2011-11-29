package org.kuali.maven.mojo;

import java.util.Properties;

import org.apache.maven.project.MavenProject;

public class JobContext {
    String filename;
    Template template;
    MavenProject project;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public MavenProject getProject() {
        return project;
    }

    public void setProject(MavenProject project) {
        this.project = project;
    }

}
