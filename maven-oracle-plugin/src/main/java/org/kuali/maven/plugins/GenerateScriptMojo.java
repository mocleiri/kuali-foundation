package org.kuali.maven.plugins;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * @author Jeff Caddel
 * @goal genscript
 */
public class GenerateScriptMojo extends AbstractMojo {
    /**
     * @parameter expression="${oracle.jdbcDriverDirectory}" default-value="${user.home}/.oracle"
     */
    private String jdbcDriverDirectory;

    /**
     * @parameter expression="${oracle.scriptDirectory}" default-value="${project.build.directory}/deploy-jars.sh"
     */
    private String scriptFile;

    /**
     * @parameter expression="${oracle.groupId}" default-value="com.oracle"
     */
    private String oracleGroupId;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        DeployUtils du = new DeployUtils();
        OutputStream out = null;
        try {
            List<Artifact> artifacts = du.getArtifacts(new File(jdbcDriverDirectory), oracleGroupId);
            getLog().info("Located " + artifacts.size() + " artifacts");
            getLog().info("Generating script to: " + scriptFile);
            String s = du.getShellScript(artifacts);
            out = new FileOutputStream(scriptFile);
            IOUtils.write(s, out);
        } catch (IOException e) {
            throw new MojoExecutionException("Unexpected issue", e);
        } finally {
            IOUtils.closeQuietly(out);
        }

    }
}
