package org.kuali.maven.ec2;

import org.apache.maven.plugin.MojoExecutionException;

/**
 * @goal runinstance
 */
public class RunInstanceMojo extends AbstractEc1Mojo {

    @Override
    public void execute() throws MojoExecutionException {
        getLog().info("Hello world");
    }

}
