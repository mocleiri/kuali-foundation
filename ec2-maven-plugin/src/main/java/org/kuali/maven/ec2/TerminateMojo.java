package org.kuali.maven.ec2;

import java.util.Collections;

import org.apache.maven.plugin.MojoExecutionException;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.TerminateInstancesRequest;

/**
 * Connect to EC2 and terminate the indicated instance.
 *
 * @goal terminate
 */
public class TerminateMojo extends AbstractEC2Mojo {

    /**
     * The id of the instance to terminate. Set this to <code>NONE</code> to skip attempting to terminate an instance
     *
     * @parameter expression="${ec2.instanceId}"
     * @required
     */
    private String instanceId;

    /**
     * If true, the build will wait until EC2 reports that the instance has reached the state of "terminated"
     *
     * @parameter expression="${ec2.wait}" default-value="false"
     */
    private boolean wait;

    /**
     * The number of seconds to wait for the instance to terminate before timing out and failing the build
     *
     * @parameter expression="${ec2.waitTimeout}" default-value="300"
     */
    private int waitTimeout;

    /**
     * The state the instance needs to be in before the plugin considers it to be terminated.
     *
     * @parameter expression="${ec2.state}" default-value="terminated"
     */
    private String state;

    @Override
    public void execute() throws MojoExecutionException {
        if (Constants.NONE.equals(instanceId)) {
            getLog().info("instanceId=" + Constants.NONE + " Skipping execution");
            return;
        }
        AmazonEC2 client = getEC2Client();
        TerminateInstancesRequest request = new TerminateInstancesRequest();
        request.setInstanceIds(Collections.singletonList(instanceId));
        client.terminateInstances(request);
        if (wait) {
            getLog().info("Waiting up to " + waitTimeout + " seconds for " + instanceId + " to terminate");
            waitForState(client, instanceId, state, waitTimeout);
        } else {
            getLog().info("Terminated " + instanceId);
        }
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String id) {
        this.instanceId = id;
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
