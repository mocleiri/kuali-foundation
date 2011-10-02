package org.kuali.maven.plugins;

import java.io.IOException;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * @author Jeff Caddel
 * @goal genscript
 */
public class GenerateScriptMojo extends AbstractMojo {
    /**
     * 
     * @parameter expression="${oracle.jdbcDriverDirectory}" default-value="${user.home}/.oracle"
     */
    private String jdbcDriverDirectory;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        DeployUtils du = new DeployUtils();
        try {
            String s = du.getShellScript(jdbcDriverDirectory);
            getLog().info(s);
        } catch (IOException e) {
            throw new MojoExecutionException("Unexpected issue", e);
        }

    }
}
