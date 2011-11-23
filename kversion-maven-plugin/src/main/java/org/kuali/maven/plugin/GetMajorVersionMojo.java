package org.kuali.maven.plugin;

import java.util.Properties;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.StringUtils;

/**
 * @goal get-major-version
 */
public class GetMajorVersionMojo extends AbstractMojo {

    /**
     * The Maven project this plugin runs in.
     *
     * @parameter expression="${project}"
     * @required
     * @readonly
     */
    private MavenProject project;

    /**
     *
     * @parameter expression="${kversion.majorVersion}" default-value="majorVersion"
     * @required
     */
    private String majorVersionProperty;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        Properties properties = project.getProperties();
        if (properties.getProperty(majorVersionProperty) != null) {
            getLog().debug(majorVersionProperty + " already set");
            return;
        }
        String majorVersion = getMajorVersion(project.getVersion());
        properties.setProperty(majorVersionProperty, majorVersion);
        getLog().info("Major Version: " + majorVersion);
    }

    protected String getMajorVersion(String version) {
        if (StringUtils.isEmpty(version)) {
            return null;
        }
        int pos = getPos(version);
        if (pos == -1) {
            return version;
        }
        return version.substring(pos);
    }

    protected int getPos(String version) {
        int pos1 = version.indexOf(".");
        int pos2 = version.indexOf("-");
        // No dot or dash
        if (pos1 == pos2 && pos2 == -1) {
            return -1;
        }
        // Dash but no dot
        if (pos1 == -1 && pos2 != -1) {
            return pos1;
        }
        // Dot but no dash
        if (pos1 != -1 && pos2 == -1) {
            return pos2;
        }
        // Both a dot and a dash, return whichever one occurs first
        return Math.min(pos1, pos2);
    }

    public MavenProject getProject() {
        return project;
    }

    public void setProject(MavenProject project) {
        this.project = project;
    }
}
