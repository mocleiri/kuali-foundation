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
     * The directory to recursively scan for files containing BOM's
     *
     * @parameter expression="${ingester.directory}" default-value="${project.basedir}/src/main/resources/workflow"
     */
    private File directory;

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

    public File getDirectory() {
        return directory;
    }

    public void setDirectory(File directory) {
        this.directory = directory;
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

}
