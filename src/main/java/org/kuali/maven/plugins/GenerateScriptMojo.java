package org.kuali.maven.plugins;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * The Oracle JDBC driver download page groups drivers by release but the filenames do not contain the version. This
 * mojo recursively inspects a directory containing Oracle JDBC drivers following the Oracle style naming convention and
 * generates a shell script following the Maven style naming convention. The shell script can then be used to upload the
 * JDBC drivers to a Maven repository.
 * 
 * @author Jeff Caddel
 * @goal genscript
 */
public class GenerateScriptMojo extends AbstractMojo {
    /**
     * Directory on the file system containing Oracle JDBC drivers
     * 
     * @parameter expression="${oracle.jdbcDriverDirectory}" default-value="${user.home}/.oracle"
     */
    private String jdbcDriverHome;

    /**
     * File where the script will be created.
     * 
     * @parameter expression="${oracle.scriptDirectory}" default-value="${project.build.directory}/deploy-jars.sh"
     */
    private String scriptFile;

    /**
     * Default groupId for Oracle JDBC drivers
     * 
     * @parameter expression="${oracle.groupId}" default-value="com.oracle"
     */
    private String oracleGroupId;

    /**
     * A comma delimited list of versions to include. If a value is provided, only jars matching one of the versions
     * will be included
     * 
     * @parameter expression="${oracle.versions}"
     */
    private String versions;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        DeployUtils du = new DeployUtils();
        try {
            List<Artifact> artifacts = du.getArtifacts(new File(jdbcDriverHome), oracleGroupId, versions);
            getLog().info("Located " + artifacts.size() + " artifacts");
            getLog().info("Generating script to: " + scriptFile);
            String s = du.getShellScript(artifacts);
            du.write(scriptFile, s);
        } catch (IOException e) {
            throw new MojoExecutionException("Unexpected issue", e);
        }
    }

    public String getJdbcDriverHome() {
        return jdbcDriverHome;
    }

    public void setJdbcDriverHome(String jdbcDriverHome) {
        this.jdbcDriverHome = jdbcDriverHome;
    }

    public String getScriptFile() {
        return scriptFile;
    }

    public void setScriptFile(String scriptFile) {
        this.scriptFile = scriptFile;
    }

    public String getOracleGroupId() {
        return oracleGroupId;
    }

    public void setOracleGroupId(String oracleGroupId) {
        this.oracleGroupId = oracleGroupId;
    }

    public String getVersions() {
        return versions;
    }

    public void setVersions(String includeVersions) {
        this.versions = includeVersions;
    }
}
