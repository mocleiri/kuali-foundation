package org.kuali.maven.plugin;

import org.apache.commons.lang.StringUtils;
import org.apache.maven.model.Scm;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;

/**
 * Extracts information contained in the pom and exposes it as project properties
 *
 * eg major version, scm type, scm url
 *
 * @goal extract
 */
public class ExtractorMojo extends AbstractMojo {

    /**
     * The Maven project this plugin runs in.
     *
     * @parameter expression="${project}"
     * @required
     * @readonly
     */
    private MavenProject project;

    /**
     * The project property where the major version will be stored
     *
     * @parameter expression="${extractor.majorVersionProperty}" default-value="extractor.majorVersion"
     * @required
     */
    private String majorVersionProperty;

    /**
     * The project property where the scm type will be stored
     *
     * @parameter expression="${extractor.scmTypeProperty}" default-value="extractor.scmType"
     * @required
     */
    private String scmTypeProperty;

    /**
     * The project property where the scm url (with the "scm:svn" or "scm:git" prefix stripped off) will be stored
     *
     * @parameter expression="${extractor.scmUrlProperty}" default-value="extractor.scmUrl"
     * @required
     */
    private String scmUrlProperty;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        handleMajorVersion(project);
        handleScmType(project);
        handleScmUrl(project);
    }

    protected void handleScmUrl(MavenProject project) {
        String scmUrl = getScmUrl(project.getScm());
        if (!StringUtils.isEmpty(scmUrl)) {
            project.getProperties().setProperty(scmUrlProperty, scmUrl);
            getLog().debug("Setting project property: " + scmUrlProperty + "=" + scmUrl);
        } else {
            getLog().debug("scm url could not be determined");
        }
    }

    protected void handleScmType(MavenProject project) {
        String scmType = getScmType(project.getScm());
        if (!StringUtils.isEmpty(scmType)) {
            project.getProperties().setProperty(scmTypeProperty, scmType);
            getLog().debug("Setting project property: " + scmTypeProperty + "=" + scmType);
        } else {
            getLog().debug("scm type could not be determined");
        }
    }

    protected String getScmUrl(Scm scm) {
        String devCon = scm.getDeveloperConnection();
        String con = scm.getDeveloperConnection();
        String scmUrl = StringUtils.isEmpty(devCon) ? con : devCon;
        if (StringUtils.isEmpty(scmUrl)) {
            return null;
        }
        String[] tokens = StringUtils.splitByWholeSeparatorPreserveAllTokens(scmUrl, ":");
        if (tokens == null || tokens.length < 3) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < tokens.length; i++) {
            if (i != 2) {
                sb.append(":");
            }
            sb.append(tokens[i]);
        }
        return sb.toString();
    }

    protected String getScmType(Scm scm) {
        String scmType1 = getScmType(scm.getDeveloperConnection());
        String scmType2 = getScmType(scm.getConnection());
        if (!StringUtils.isEmpty(scmType1)) {
            return scmType1;
        } else if (!StringUtils.isEmpty(scmType2)) {
            return scmType2;
        } else {
            return null;
        }
    }

    protected String getScmType(String url) {
        if (StringUtils.isEmpty(url)) {
            return null;
        }
        String[] tokens = StringUtils.splitByWholeSeparatorPreserveAllTokens(url, ":");
        if (tokens == null || tokens.length < 2) {
            return null;
        } else {
            return tokens[1];
        }
    }

    protected void handleMajorVersion(MavenProject project) {

        String majorVersion = getMajorVersion(project.getVersion());
        if (!StringUtils.isEmpty(majorVersion)) {
            project.getProperties().setProperty(majorVersionProperty, majorVersion);
            getLog().debug("Setting project property: " + majorVersionProperty + "=" + majorVersion);
        } else {
            getLog().debug("Major version could not be determined");
        }
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

    public String getMajorVersionProperty() {
        return majorVersionProperty;
    }

    public void setMajorVersionProperty(String majorVersionProperty) {
        this.majorVersionProperty = majorVersionProperty;
    }

    public String getScmTypeProperty() {
        return scmTypeProperty;
    }

    public void setScmTypeProperty(String scmTypeProperty) {
        this.scmTypeProperty = scmTypeProperty;
    }

    public String getScmUrlProperty() {
        return scmUrlProperty;
    }

    public void setScmUrlProperty(String scmUrlProperty) {
        this.scmUrlProperty = scmUrlProperty;
    }
}
