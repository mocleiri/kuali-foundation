package org.kuali.maven.mojo;

import java.io.IOException;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;

/**
 * @goal generatejob
 */
public class GenerateJobMojo extends AbstractMojo {
    Generator generator = new Generator();

    /**
     * The Maven project this plugin runs in.
     * 
     * @parameter expression="${project}"
     * @required
     * @readonly
     */
    private MavenProject project;

    /**
     * @parameter expression="${jenkins.type}" default-value="PUBLISH"
     * @required
     */
    private Type type;

    /**
     * @parameter expression="${jenkins.filename}"
     */
    private String filename;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            JobContext context = new JobContext();
            context.setFilename(filename);
            context.setProject(project);
            context.setType(type);
            generator.generate(context);
        } catch (IOException e) {
            throw new MojoExecutionException("Unexpected error", e);
        }
    }

    public MavenProject getProject() {
        return project;
    }

    public void setProject(MavenProject project) {
        this.project = project;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
