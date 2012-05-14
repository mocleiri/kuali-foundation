package org.kuali.maven.ec2;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.Tag;

public abstract class AbstractEC2Mojo extends AbstractMojo {

    /**
     * The AWS Access Key Id for an account on EC2
     *
     * @parameter expression="${ec2.accessKey}"
     * @required
     */
    private String accessKey;

    /**
     * The AWS Secret Access Key for an account on EC2
     *
     * @parameter expression="${ec2.secretKey}"
     * @required
     */
    private String secretKey;

    protected AWSCredentials getCredentials() {
        return new BasicAWSCredentials(accessKey, secretKey);
    }

    protected AmazonEC2 getEC2Client() {
        AWSCredentials credentials = getCredentials();
        return new AmazonEC2Client(credentials);
    }

    protected String getTagValue(Instance i, String tag) {
        List<Tag> tags = i.getTags();
        for (Tag t : tags) {
            if (t.getKey().equals(tag)) {
                return t.getValue();
            }
        }
        return "";
    }

    protected Instance getInstance(AmazonEC2 client, String instanceId) {
        DescribeInstancesRequest request = new DescribeInstancesRequest();
        request.setInstanceIds(Collections.singletonList(instanceId));
        DescribeInstancesResult result = client.describeInstances(request);
        List<Reservation> reservations = result.getReservations();
        Reservation r = reservations.get(0);
        List<Instance> instances = r.getInstances();
        return instances.get(0);
    }

    protected boolean isEmpty(Collection<?> c) {
        return c == null || c.size() == 0;
    }

    protected void waitForState(AmazonEC2 client, String instanceId, String state, int waitTimeout)
            throws MojoExecutionException {
        long now = System.currentTimeMillis();
        long timeout = now + waitTimeout * 1000;
        while (true) {
            long remaining = (timeout - now) / 1000;
            Instance i = getInstance(client, instanceId);
            String newState = i.getState().getName();
            getLog().info(newState + " - " + remaining + "s");
            if (state.equals(newState)) {
                break;
            } else {
                sleep(3000);
            }
            now = System.currentTimeMillis();
            if (now > timeout) {
                throw new MojoExecutionException("Timed out waiting for state '" + state + "'");
            }
        }
    }

    protected void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

}
