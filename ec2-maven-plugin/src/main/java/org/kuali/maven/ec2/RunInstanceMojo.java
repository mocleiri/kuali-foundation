package org.kuali.maven.ec2;

import org.apache.maven.plugin.MojoExecutionException;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.RunInstancesRequest;

/**
 * @goal runinstance
 */
public class RunInstanceMojo extends AbstractEC2Mojo {

    /**
     * The AMI to launch
     *
     * @parameter expression="${ec2.ami}"
     * @required
     */
    private String ami;

    /**
     * The name of the key to use
     *
     * @parameter expression="${ec2.key}"
     * @required
     */
    private String key;

    @Override
    public void execute() throws MojoExecutionException {
        AmazonEC2 client = getEC2Client();
        RunInstancesRequest request = new RunInstancesRequest();
        request.setImageId(ami);
        request.setKeyName(key);
        client.runInstances(request);
    }

}
