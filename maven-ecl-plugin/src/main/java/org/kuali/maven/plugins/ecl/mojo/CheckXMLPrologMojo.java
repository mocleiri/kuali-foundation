package org.kuali.maven.plugins.ecl.mojo;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.kuali.maven.plugins.ecl.Scanner;
import org.kuali.maven.plugins.ecl.filter.CommonIgnoresFilter;
import org.kuali.maven.plugins.ecl.filter.FileExtensionFilter;
import org.kuali.maven.plugins.ecl.filter.MisplacedXMLPrologFilter;
import org.kuali.maven.plugins.ecl.filter.PathContainsExcludeFilter;
import org.kuali.maven.plugins.ecl.filter.XMLRelatedFilter;

/**
 * @goal checkxmlprolog
 */
public class CheckXMLPrologMojo extends AbstractMojo {
    Scanner scanner = new Scanner();

    /**
     * @parameter expression="${ecl.basedir}" default-value="${project.basedir}"
     * @required
     */
    private File basedir;

    /**
     * @parameter
     */
    private List<String> includes;

    /**
     * @parameter
     */
    private List<String> excludes;

    /**
     * @parameter expression="${ecl.useDefaultExcludes}" default-value="true"
     * @required
     */
    private boolean useDefaultExcludes;

    /**
     * @parameter expression="${ecl.useDefaultIncludes}" default-value="true"
     * @required
     */
    private boolean useDefaultIncludes;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            FileFilter exclude = getExcludeFilter();
            FileFilter include = getIncludeFilter();
            FileFilter problem = new MisplacedXMLPrologFilter();
            getLog().info("Scanning " + basedir.getAbsolutePath());
            List<File> filesToCheck = scanner.getRecursiveFileList(basedir, include, exclude);
            getLog().info("Located " + filesToCheck.size() + " files to examine");
            List<File> problems = scanner.getFiles(filesToCheck, problem);
            if (problems.size() == 0) {
                getLog().info("No files containing XML prolog issues were located");
            } else {
                getLog().warn("These files contain an XML prolog that is not on the first line");
                for (File file : problems) {
                    getLog().warn(file.getAbsolutePath());
                }
            }
        } catch (IOException e) {
            throw new MojoExecutionException("Unexpected error", e);
        }
    }

    protected FileFilter getIncludeFilter() {
        FileExtensionFilter filter = new XMLRelatedFilter();
        if (includes == null) {
            return filter;
        }
        if (useDefaultIncludes) {
            filter.getExtensions().addAll(includes);
        } else {
            filter.setExtensions(includes);
        }
        return filter;
    }

    protected FileFilter getExcludeFilter() {
        PathContainsExcludeFilter filter = new CommonIgnoresFilter();
        if (excludes == null) {
            return filter;
        }
        if (useDefaultExcludes) {
            filter.getExcludes().addAll(excludes);
        } else {
            filter.setExcludes(excludes);
        }
        return filter;
    }
}
