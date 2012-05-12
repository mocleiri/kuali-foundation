package org.kuali.maven.ec2;

import org.apache.maven.plugin.MojoExecutionException;

import com.amazonaws.services.ec2.AmazonEC2;

/**
 *
 * @goal describeinstances
 */
public class DescribeInstancesMojo extends AbstractEC2Mojo {

    @Override
    public void execute() throws MojoExecutionException {
        AmazonEC2 client = getEC2Client();
        getLog().info("Hello world");
    }

}
