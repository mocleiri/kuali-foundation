package org.kuali.maven.ec2;

import java.util.Collections;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.TerminateInstancesRequest;

/**
 * @goal terminate
 */
public class TerminateMojo extends AbstractEC2Mojo {

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

    /**
     * If true, the build will wait until EC2 reports that the instance has reached the state of "running"
     *
     * @parameter expression="${ec2.wait}" default-value="true"
     */
    private boolean wait;

    /**
     * The number of seconds to wait for the instance to start before timing out and failing the build
     *
     * @parameter expression="${ec2.waitTimeout}" default-value="300"
     */
    private int waitTimeout;

    /**
     * The state the instance needs to be in before the plugin considers it to be started.
     *
     * @parameter expression="${ec2.state}" default-value="terminated"
     */
    private String state;

    @Override
    public void execute() throws MojoExecutionException {
        AmazonEC2 client = getEC2Client();
        TerminateInstancesRequest request = new TerminateInstancesRequest();
        request.setInstanceIds(Collections.singletonList(id));
        client.terminateInstances(request);
        if (wait) {
            getLog().info("Waiting up to " + waitTimeout + " seconds for " + id + "  to terminate");
            waitForState(client, id, state, waitTimeout);
        } else {
            getLog().info("Terminated " + id);
        }
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

    public boolean isWait() {
        return wait;
    }

    public void setWait(boolean wait) {
        this.wait = wait;
    }

    public int getWaitTimeout() {
        return waitTimeout;
    }

    public void setWaitTimeout(int waitTimeout) {
        this.waitTimeout = waitTimeout;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
