package org.kuali.maven.plugins.ingester;

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * @goal ingest
 */
public class IngestMojo extends AbstractMojo {

    /**
     * The directory containing documents to ingest
     *
     * @parameter expression="${ingester.sourceDir}" default-value="${project.basedir}/src/main/resources"
     */
    private File sourceDir;

    /**
     * The directory containing documents to ingest
     *
     * @parameter expression="${ingester.outputDir}" default-value="${project.build.directory}/ingester"
     */
    private File outputDir;

    /**
     * Inclusion patterns. By default *.xml is included
     *
     * @parameter
     */
    private String[] includes = { "**/*.xml" };

    /**
     * Exclusion patterns.
     *
     * @parameter
     */
    private String[] excludes;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        WorkflowImporter.main(null);
    }

    public String[] getIncludes() {
        return includes;
    }

    public void setIncludes(String[] includes) {
        this.includes = includes;
    }

    public String[] getExcludes() {
        return excludes;
    }

    public void setExcludes(String[] excludes) {
        this.excludes = excludes;
    }

    public File getSourceDir() {
        return sourceDir;
    }

    public void setSourceDir(File sourceDir) {
        this.sourceDir = sourceDir;
    }

    public File getOutputDir() {
        return outputDir;
    }

    public void setOutputDir(File outputDir) {
        this.outputDir = outputDir;
    }

}
