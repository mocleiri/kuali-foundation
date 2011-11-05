package org.kuali.maven.plugins.ecl.mojo;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.kuali.maven.plugins.ecl.MisplacedXMLPrologContext;
import org.kuali.maven.plugins.ecl.ProblemFileContext;
import org.kuali.maven.plugins.ecl.ProblemFileDetector;
import org.kuali.maven.plugins.ecl.filter.CommonIgnoresFilter;

/**
 * @goal checkxmlprolog
 */
public class CheckXMLPrologMojo extends AbstractMojo {
    ProblemFileDetector detector = new ProblemFileDetector();

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
            ProblemFileContext context = new MisplacedXMLPrologContext(basedir.getAbsolutePath());
            if (excludes != null) {
                CommonIgnoresFilter filter = new CommonIgnoresFilter();
                if (useDefaultExcludes) {
                    filter.getExcludes().addAll(excludes);
                } else {
                    filter.setExcludes(excludes);
                }
                context.setExclude(filter);
            }
            List<File> files = detector.getProblemFiles(context);
            for (File file : files) {
                getLog().info(file.getAbsolutePath());
            }
        } catch (IOException e) {
            throw new MojoExecutionException("Unexpected error", e);
        }
    }
}
