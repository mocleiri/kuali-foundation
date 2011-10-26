package org.kuali.maven.plugins;

import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.Executor;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 *
 * @author Jeff Caddel
 * @goal stop
 */
public class StopMojo extends AbstractMojo {

    /**
     * @parameter expression="${deployer.auth}"
     */
    private String auth;

    /**
     * @parameter expression="${deployer.server}"
     */
    private String server;

    /**
     * @parameter expression="${deployer.tomcatInstance}"
     */
    private String tomcatInstance;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        Executor exec = new DefaultExecutor();
        String commandLine = getCommandLine();
        CommandLine command = new CommandLine(commandLine);
        try {
            int exitValue = exec.execute(command);
            if (exitValue != 0) {
                throw new MojoExecutionException("Invalid exit value: " + exitValue);
            }
        } catch (IOException e) {
            throw new MojoExecutionException("Unexpected error", e);
        }

    }

    protected String getCommandLine() {
        StringBuilder sb = new StringBuilder();
        sb.append("ssh");
        sb.append(" ");
        sb.append("-i");
        sb.append(" ");
        sb.append(auth);
        sb.append(" ");
        sb.append("root@" + server);
        sb.append(" ");
        sb.append("su");
        sb.append(" ");
        sb.append("-");
        sb.append(" ");
        sb.append(tomcatInstance);
        sb.append(" ");
        sb.append("/usr/local/tomcat/bin/forced-shutdown.sh");
        return sb.toString();
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getTomcatInstance() {
        return tomcatInstance;
    }

    public void setTomcatInstance(String tomcatInstance) {
        this.tomcatInstance = tomcatInstance;
    }

}
