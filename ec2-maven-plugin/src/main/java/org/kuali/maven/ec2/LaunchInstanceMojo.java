package org.kuali.maven.ec2;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.MojoExecutionException;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.InstanceType;
import com.amazonaws.services.ec2.model.RunInstancesRequest;

/**
 * @goal launchinstance
 */
public class LaunchInstanceMojo extends AbstractEC2Mojo {

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

    /**
     * The type of instance to launch
     *
     * @parameter expression="${ec2.type}" default-value="c1.medium";
     * @required
     */
    private String type;

    /**
     * The security groups into which the instance will be launched
     *
     * @parameter
     */
    private List<String> securityGroups;

    /**
     * Optional user data for the instance
     *
     * @parameter expression="${ec2.userData}"
     */
    private String userData;

    /**
     * If supplied, the contents of the file are supplied to the instance as userData
     *
     * @parameter expression="${ec2.userDataFile}"
     */
    private File userDataFile;

    /**
     * The encoding of the userDataFile
     *
     * @parameter expression="${ec2.encoding}" default-value="${project.build.sourceEncoding}"
     */
    private String encoding;

    @Override
    public void execute() throws MojoExecutionException {
        AmazonEC2 client = getEC2Client();
        RunInstancesRequest request = new RunInstancesRequest();
        request.setImageId(ami);
        request.setKeyName(key);
        request.setInstanceType(InstanceType.fromValue(type));
        request.setSecurityGroups(securityGroups);
        attachUserData(request);
        client.runInstances(request);
    }

    protected void attachUserData(RunInstancesRequest request) throws MojoExecutionException {

        if (userDataFile != null) {
            try {
                String userData = FileUtils.readFileToString(userDataFile, encoding);
                request.setUserData(userData);
            } catch (IOException e) {
                throw new MojoExecutionException("Error reading user data file", e);
            }
        } else {
            request.setUserData(userData);
        }
    }
}
