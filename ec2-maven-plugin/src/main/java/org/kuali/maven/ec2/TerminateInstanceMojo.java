package org.kuali.maven.ec2;

import java.util.Collections;
import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.InstanceStateChange;
import com.amazonaws.services.ec2.model.TerminateInstancesRequest;
import com.amazonaws.services.ec2.model.TerminateInstancesResult;

/**
 * @goal terminateinstance
 */
public class TerminateInstanceMojo extends AbstractEC2Mojo {

    /**
     * The Maven project object
     *
     * @parameter expression="${project}"
     * @readonly
     */
    private MavenProject project;

    /**
     * The id of the instance to terminate
     *
     * @parameter expression="${ec2.id}"
     * @required
     */
    private String id;

    @Override
    public void execute() throws MojoExecutionException {
        AmazonEC2 client = getEC2Client();
        TerminateInstancesRequest request = new TerminateInstancesRequest();
        request.setInstanceIds(Collections.singletonList(id));
        TerminateInstancesResult result = client.terminateInstances(request);
        List<InstanceStateChange> instances = result.getTerminatingInstances();
        InstanceStateChange isc = instances.get(0);
        getLog().info(
                isc.getInstanceId() + " Current State:" + isc.getCurrentState() + " Previous State: "
                        + isc.getPreviousState());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MavenProject getProject() {
        return project;
    }
}
