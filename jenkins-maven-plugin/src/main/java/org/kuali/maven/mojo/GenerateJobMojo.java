package org.kuali.maven.mojo;

import java.io.IOException;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.StringUtils;
import org.kuali.maven.common.Extractor;

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
     * @parameter expression="${jenkins.type}" default-value="publish"
     * @required
     */
    private String type;

    /**
     * @parameter expression="${jenkins.filename}"
     */
    private String filename;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            JobContext context = getContext();
            getLog().info("Generating: " + context.getFilename());
            generator.generate(context);
        } catch (IOException e) {
            throw new MojoExecutionException("Unexpected error", e);
        }
    }

    protected JobContext getContext() {
        Extractor extractor = new Extractor();
        String scmType = extractor.getScmType(project.getScm());
        String scmUrl = extractor.getScmUrl(project.getScm());
        String majorVersion = extractor.getMajorVersion(project.getVersion());

        JobContext context = new JobContext();
        context.setFilename(filename);
        context.setProject(project);
        context.setJobType(type);
        context.setScmType(scmType);
        context.setScmUrl(scmUrl);
        context.setMajorVersion(majorVersion);

        if (StringUtils.isEmpty(filename)) {
            String defaultFilename = generator.getDefaultFilename(context);
            setFilename(defaultFilename);
        }
        return context;
    }

    public MavenProject getProject() {
        return project;
    }

    public void setProject(MavenProject project) {
        this.project = project;
    }

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
}
