package org.kuali.maven.ec2;

import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;

import com.amazonaws.services.ec2.model.Snapshot;
import com.amazonaws.services.ec2.model.Tag;

/**
 * Create a snapshot of a volume. If successful, the project property <code>ec2.snapshot.id</code> will contain the id
 * of the snapshot that was created.
 *
 * @goal createsnapshot
 */
public class CreateSnapshotMojo extends AbstractEC2Mojo {

    /**
     * The Maven project object
     *
     * @parameter expression="${project}"
     * @readonly
     */
    private MavenProject project;

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
     * If true, the build will wait until EC2 reports that the snapshot has reached the state of "completed"
     *
     * @parameter expression="${ec2.wait}" default-value="true"
     */
    private boolean wait;

    /**
     * The number of seconds to wait for the instance to terminate before timing out and failing the build
     *
     * @parameter expression="${ec2.waitTimeout}" default-value="3600"
     */
    private int waitTimeout;

    /**
     * The number of seconds to sleep in between status checks on the snapshot
     *
     * @parameter expression="${ec2.sleep}" default-value="30"
     */
    private int sleep;

    /**
     * The state the plugin will wait for the snapshot to be in before continuing
     *
     * @parameter expression="${ec2.state}" default-value="completed"
     */
    private String state;

    @Override
    protected boolean isSkip() {
        if (Constants.NONE.equals(volumeId)) {
            getLog().info("volumeId=" + volumeId);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void execute(EC2Utils ec2Utils) throws MojoExecutionException {
        WaitControl wc = new WaitControl(wait, waitTimeout, state);
        wc.setSleep(sleep * 1000);
        long start = System.currentTimeMillis();
        Snapshot snapshot = ec2Utils.createSnapshot(volumeId, description, wc);
        long millis = System.currentTimeMillis() - start;
        getLog().info("Elapsed: " + millis / 1000);
        getLog().info("Setting ec2.snapshot.id=" + snapshot.getSnapshotId());
        project.getProperties().setProperty("ec2.snapshot.id", snapshot.getSnapshotId());
        ec2Utils.tag(snapshot.getSnapshotId(), tags);
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

    public int getSleep() {
        return sleep;
    }

    public void setSleep(int sleep) {
        this.sleep = sleep;
    }

    public MavenProject getProject() {
        return project;
    }
}
