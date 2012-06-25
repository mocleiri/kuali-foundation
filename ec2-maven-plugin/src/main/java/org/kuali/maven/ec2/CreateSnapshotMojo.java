package org.kuali.maven.ec2;

import java.util.Collections;
import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.CreateSnapshotRequest;
import com.amazonaws.services.ec2.model.CreateSnapshotResult;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.Tag;

/**
 * @goal createsnapshot
 */
public class CreateSnapshotMojo extends AbstractEC2Mojo {

    /**
     * List of tags to associate with the snapshot. Tags are key value pairs and can be supplied in the plugin
     * configuration like this:<br>
     *
     * <pre>
     *   &lt;tags&gt;
     *     &lt;tag&gt;
     *       &lt;key&gt;Name&lt;/key&gt;
     *       &lt;value&gt;production&lt;/value&gt;
     *     &lt;/tag&gt;
     *     &lt;tag&gt;
     *       &lt;key&gt;Category&lt;/key&gt;
     *       &lt;value&gt;networking&lt;/value&gt;
     *     &lt;/tag&gt;
     *   &lt;/tags&gt;
     * </pre>
     *
     * @parameter
     */
    private List<Tag> tags;

    /**
     * The volume to take a snapshot of
     *
     * @parameter expression="${ec2.volumeId}"
     * @required
     */
    private String volumeId;

    /**
     * The description of the snapshot (if any)
     *
     * @parameter expression="${ec2.description}"
     */
    private String description;

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
     * The state the plugin will wait for the snapshot to be in before continuing
     *
     * @parameter expression="${ec2.state}" default-value="completed"
     */
    private String state;

    @Override
    public void execute() throws MojoExecutionException {
        if (Constants.NONE.equals(volumeId)) {
            getLog().info("volumeId=" + Constants.NONE + " Skipping execution");
            return;
        }
        AmazonEC2 client = getEC2Client();

        CreateSnapshotRequest request = new CreateSnapshotRequest(volumeId, description);
        CreateSnapshotResult result = client.createSnapshot(request);
        String id = result.getSnapshot().getSnapshotId();
        if (wait) {
            getLog().info("Waiting up to " + waitTimeout + " seconds for " + id + " to complete");
            waitForSnapshotState(client, result.getSnapshot().getSnapshotId(), state, waitTimeout);
        } else {
            getLog().info("Completed " + id);
        }
        if (tags != null && tags.size() > 0) {
            CreateTagsRequest ctr = new CreateTagsRequest();
            ctr.setResources(Collections.singletonList(id));
            ctr.setTags(tags);
            client.createTags(ctr);
            getLog().info("Tagged snapshot " + id + " with " + tags.size() + " tags");
        }
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

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getVolumeId() {
        return volumeId;
    }

    public void setVolumeId(String volumeId) {
        this.volumeId = volumeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
