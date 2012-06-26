package org.kuali.maven.ec2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.kuali.maven.ec2.state.StateRetriever;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.CreateSnapshotRequest;
import com.amazonaws.services.ec2.model.CreateSnapshotResult;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.DescribeSnapshotsRequest;
import com.amazonaws.services.ec2.model.DescribeSnapshotsResult;
import com.amazonaws.services.ec2.model.Filter;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;
import com.amazonaws.services.ec2.model.Snapshot;
import com.amazonaws.services.ec2.model.Tag;
import com.amazonaws.services.ec2.model.TerminateInstancesRequest;

public class EC2Utils {

    private static final Logger logger = LoggerFactory.getLogger(EC2Utils.class);

    AmazonEC2Client client;

    private EC2Utils(AWSCredentials credentials) {
        this.client = new AmazonEC2Client(credentials);
    }

    public static EC2Utils getInstance(String accessKey, String secretKey) {
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        return getInstance(credentials);
    }

    public static EC2Utils getInstance(AWSCredentials credentials) {
        return new EC2Utils(credentials);
    }

    public void terminate(String instanceId, WaitControl wc) {
        TerminateInstancesRequest request = new TerminateInstancesRequest();
        request.setInstanceIds(Collections.singletonList(instanceId));
        client.terminateInstances(request);
        if (wc.isWait()) {
            logger.info("Waiting up to " + wc.getTimeout() + " seconds for " + instanceId + " to terminate");
            waitForState(instanceId, wc.getState(), wc.getTimeout());
        } else {
            logger.info("Terminated " + instanceId);
        }
    }

    public Instance wait(Instance i, WaitControl wc, Properties props) {
        if (wc.isWait()) {
            logger.info("Waiting up to " + wc.getTimeout() + " seconds for " + i.getInstanceId() + " to start");
            waitForState(i.getInstanceId(), wc.getState(), wc.getTimeout());
            Instance running = getEC2Instance(i.getInstanceId());
            String id = i.getInstanceId();
            String dns = running.getPublicDnsName();
            String name = getTagValue(running, "Name");
            logger.info("EC2 Instance: " + name + " (" + id + ") " + dns);
            props.setProperty("ec2.instance.dns", running.getPublicDnsName());
            return running;
        } else {
            logger.info("Launched " + i.getInstanceId());
            return i;
        }
    }

    public Instance getEC2Instance(RunInstancesRequest request) {
        RunInstancesResult result = client.runInstances(request);
        Reservation r = result.getReservation();
        List<Instance> instances = r.getInstances();
        return instances.get(0);
    }

    public void createTags(Instance instance, List<Tag> tags) {
        if (isEmpty(tags)) {
            return;
        }
        CreateTagsRequest request = new CreateTagsRequest();
        request.setResources(Collections.singletonList(instance.getInstanceId()));
        request.setTags(tags);
        client.createTags(request);
    }

    public Instance getSingleEC2Instance(RunInstancesRequest request) {
        RunInstancesResult result = client.runInstances(request);
        Reservation r = result.getReservation();
        List<Instance> instances = r.getInstances();
        return instances.get(0);
    }

    protected Filter getFilterFromTag(String tag, String value) {
        Filter filter = new Filter();
        filter.setName("tag:" + tag);
        filter.setValues(Collections.singletonList(value));
        return filter;
    }

    protected DescribeInstancesRequest getDescribeInstancesRequest(Tag tag) {
        DescribeInstancesRequest request = new DescribeInstancesRequest();
        Filter filter = getFilterFromTag(tag.getKey(), tag.getValue());
        request.setFilters(Collections.singletonList(filter));
        return request;
    }

    protected int validate(List<Instance> instances, Tag tag, boolean failIfNotFound) {
        int size = instances.size();
        String msg = tag.getKey() + "=" + tag.getValue() + " matched " + size + " instances";
        if (size == 1) {
            return size;
        }
        if (size > 1) {
            throw new IllegalStateException(msg);
        }
        // size == 0
        if (failIfNotFound) {
            throw new IllegalStateException(msg);
        } else {
            logger.info(msg);
        }
        return size;
    }

    public Instance findInstanceFromTag(Tag tag, boolean failIfNotFound) {
        DescribeInstancesRequest request = getDescribeInstancesRequest(tag);
        DescribeInstancesResult result = client.describeInstances(request);
        List<Instance> instances = getAllInstances(result.getReservations());
        int size = validate(instances, tag, failIfNotFound);
        if (size == 1) {
            return instances.get(0);
        } else {
            return null;
        }

    }

    public List<Instance> getInstances(List<String> instanceIds) {
        DescribeInstancesRequest request = new DescribeInstancesRequest();
        request.setInstanceIds(instanceIds);
        DescribeInstancesResult result = client.describeInstances(request);
        return getAllInstances(result.getReservations());
    }

    public Snapshot createSnapshot(String volumeId, String description, WaitControl wait) {
        CreateSnapshotRequest request = new CreateSnapshotRequest(volumeId, description);
        CreateSnapshotResult result = client.createSnapshot(request);
        String id = result.getSnapshot().getSnapshotId();
        if (wait.isWait()) {
            logger.info("Waiting up to " + wait.getTimeout() + " seconds for snapshot '" + id + "' to complete");
            waitForSnapshotState(client, id, wait.getState(), wait.getTimeout());
        } else {
            logger.info("Completed " + id);
        }
        return result.getSnapshot();
    }

    public void tag(String id, String name, String value) {
        tag(id, new Tag(name, value));
    }

    public void tag(String id, Tag tag) {
        tag(id, Collections.singletonList(tag));
    }

    /**
     * Adds or overwrites tags for the specified resource. <code>id</code> can be an EC2 instance id, snapshot id,
     * volume id, etc. Each resource can have a maximum of 10 tags. Each tag consists of a key-value pair. Tag keys must
     * be unique per resource.
     */
    public void tag(String id, List<Tag> tags) {
        if (isEmpty(tags)) {
            return;
        }
        CreateTagsRequest request = new CreateTagsRequest();
        request.setResources(Collections.singletonList(id));
        request.setTags(tags);
        client.createTags(request);
        logger.info("Tagged '" + id + "' with " + tags.size() + " tags");
    }

    public AWSCredentials getCredentials(String accessKey, String secretKey) {
        return new BasicAWSCredentials(accessKey, secretKey);
    }

    public AmazonEC2Client getEC2Client(String accessKey, String secretKey) {
        AWSCredentials credentials = getCredentials(accessKey, secretKey);
        return new AmazonEC2Client(credentials);
    }

    public String getTagValue(Instance i, String tag) {
        List<Tag> tags = i.getTags();
        for (Tag t : tags) {
            if (t.getKey().equals(tag)) {
                return t.getValue();
            }
        }
        return "";
    }

    protected void waitForSnapshotState(AmazonEC2 client, String snapshotId, String state, int waitTimeout) {
        long now = System.currentTimeMillis();
        long timeout = now + waitTimeout * 1000;
        // Wait a few seconds before we query AWS for the state of the instance
        // If you query immediately it can sometimes flake out
        sleep(5000);
        while (true) {
            long remaining = (timeout - now) / 1000;
            Snapshot ss = getSnapshot(client, snapshotId);
            String newState = ss.getState();
            logger.info(newState + " - " + remaining + "s");
            if (state.equals(newState)) {
                break;
            } else {
                sleep(5000);
            }
            now = System.currentTimeMillis();
            if (now > timeout) {
                throw new IllegalStateException("Timed out waiting for state '" + state + "'");
            }
        }
    }

    public void waitForState(StateRetriever retriever, WaitControl wc) {
        long now = System.currentTimeMillis();
        long timeout = now + wc.getTimeout() * 1000;
        // Wait a few seconds before we query AWS for the state of the instance
        // If you query immediately it can sometimes flake out
        sleep(wc.getSleep());
        while (true) {
            long remaining = (timeout - now) / 1000;
            String newState = retriever.getState();
            logger.info(newState + " - " + remaining + "s");
            if (newState.equals(wc.getState())) {
                break;
            } else {
                sleep(wc.getSleep());
            }
            now = System.currentTimeMillis();
            if (now > timeout) {
                throw new IllegalStateException("Timed out waiting for state '" + wc.getState() + "'");
            }
        }
    }

    public void waitForState(String instanceId, String state, int waitTimeout) {
        long now = System.currentTimeMillis();
        long timeout = now + waitTimeout * 1000;
        // Wait a few seconds before we query AWS for the state of the instance
        // If you query immediately it can sometimes flake out
        sleep(5000);
        while (true) {
            long remaining = (timeout - now) / 1000;
            Instance i = getEC2Instance(instanceId);
            String newState = i.getState().getName();
            logger.info(newState + " - " + remaining + "s");
            if (state.equals(newState)) {
                break;
            } else {
                sleep(5000);
            }
            now = System.currentTimeMillis();
            if (now > timeout) {
                throw new IllegalStateException("Timed out waiting for state '" + state + "'");
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

    public List<Instance> getAllInstances(List<Reservation> reservations) {
        List<Instance> instances = new ArrayList<Instance>();
        for (Reservation r : reservations) {
            instances.addAll(r.getInstances());
        }
        return instances;
    }

    public Snapshot getSnapshot(AmazonEC2 client, String snapshotId) {
        DescribeSnapshotsRequest request = new DescribeSnapshotsRequest();
        request.setSnapshotIds(Collections.singletonList(snapshotId));
        DescribeSnapshotsResult result = client.describeSnapshots(request);
        List<Snapshot> snapshots = result.getSnapshots();
        return snapshots.get(0);
    }

    public Instance getEC2Instance(String instanceId) {
        DescribeInstancesRequest request = new DescribeInstancesRequest();
        request.setInstanceIds(Collections.singletonList(instanceId));
        DescribeInstancesResult result = client.describeInstances(request);
        List<Reservation> reservations = result.getReservations();
        Reservation r = reservations.get(0);
        List<Instance> instances = r.getInstances();
        return instances.get(0);
    }

    public static final boolean isEmpty(Collection<?> c) {
        return c == null || c.size() == 0;
    }

}
