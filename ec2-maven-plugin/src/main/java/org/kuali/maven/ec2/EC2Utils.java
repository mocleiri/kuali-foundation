package org.kuali.maven.ec2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.Snapshot;
import com.amazonaws.services.ec2.model.Tag;

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

    public WaitControl getWaitControl(boolean wait, int waitTimeout, String state) {
        return new WaitControl(wait, waitTimeout, state);
    }

    public Snapshot createSnapshot(AmazonEC2Client client, String volumeId, String description, WaitControl wait) {
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

    public void tag(AmazonEC2Client client, String id, String name, String value) {
        tag(client, id, new Tag(name, value));
    }

    public void tag(AmazonEC2Client client, String id, Tag tag) {
        tag(client, id, Collections.singletonList(tag));
    }

    public void tag(AmazonEC2Client client, String id, List<Tag> tags) {
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

    protected void waitForState(AmazonEC2 client, String instanceId, String state, int waitTimeout) {
        long now = System.currentTimeMillis();
        long timeout = now + waitTimeout * 1000;
        // Wait a few seconds before we query AWS for the state of the instance
        // If you query immediately it can sometimes flake out
        sleep(5000);
        while (true) {
            long remaining = (timeout - now) / 1000;
            Instance i = getInstance(client, instanceId);
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

    public List<Instance> getInstances(List<Reservation> reservations) {
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

    public Instance getInstance(AmazonEC2 client, String instanceId) {
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
