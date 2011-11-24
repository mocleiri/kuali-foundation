package org.kuali.maven.plugin;

import java.util.Properties;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.StringUtils;

/**
 * Stores the major version of the project as a property. The mojo assumes 3 digit versions eg "1.0.1" and counts "1.0"
 * as the major version
 *
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
     * The property where the major version will be stored
     *
     * @parameter expression="${version.majorVersion}" default-value="majorVersion"
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
        getLog().debug("Major Version: " + majorVersion);
    }

    protected String getMajorVersion(String version) {
        if (StringUtils.isEmpty(version)) {
            return null;
        }
        int pos = getPos(version);
        if (pos == -1) {
            return version;
        } else {
            return version.substring(0, pos);
        }
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

        // Both a dot and a dash, use the first one
        int pos = Math.min(pos1, pos2);

        // Attempt to peek ahead one character after the dot/dash
        int index = pos + 1;

        // If we go past the end of the string, forget it
        if (index >= version.length()) {
            return pos;
        }

        // Is that character an integer?
        char c = version.charAt(index);

        if (isInteger(c)) {
            // If so, include it
            return index + 1;
        } else {
            // If not return the original
            return pos;
        }

    }

    protected boolean isInteger(char c) {
        switch (c) {
        case '0':
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9':
            return true;
        default:
            return false;
        }
    }

    public MavenProject getProject() {
        return project;
    }

    public void setProject(MavenProject project) {
        this.project = project;
    }
}
