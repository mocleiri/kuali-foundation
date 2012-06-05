package org.kuali.maven.ec2;

import java.util.Collections;
import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Tag;

/**
 * Connect to EC2 and tag an instance with name/value pairs. Each instance can have up to 10 tags
 *
 * @goal taginstance
 */
public class TagInstanceMojo extends AbstractEC2Mojo {

    /**
     * The id of the instance to tag. Set this to <code>NONE</code> to skip tagging any instances
     *
     * @parameter expression="${ec2.instanceId}"
     * @required
     */
    private String instanceId;

    /**
     * If true, fail the build if no matching instance is found
     *
     * @parameter expression="${ec2.failIfNotFound}" default-value="false"
     */
    private boolean failIfNotFound;

    /**
     * List of tags to associate with the instance. Tags are key value pairs and can be supplied in the plugin
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

    @Override
    public void execute() throws MojoExecutionException {
        if (Constants.NONE.equals(instanceId)) {
            getLog().info("instanceId=" + Constants.NONE + " Skipping execution");
            return;
        }
        if (tags == null || tags.size() == 0) {
            getLog().info("No tags to apply.  Skipping execution");
            return;
        }
        AmazonEC2 client = getEC2Client();
        DescribeInstancesRequest request = getRequest();
        DescribeInstancesResult result = client.describeInstances(request);
        List<Instance> instances = getInstances(result.getReservations());
        int size = validate(instances);
        if (size != 1) {
            return;
        }

        // If we get here, there is exactly one matching instance
        Instance i = instances.get(0);
        CreateTagsRequest ctr = new CreateTagsRequest();
        ctr.setResources(Collections.singletonList(i.getInstanceId()));
        ctr.setTags(tags);
        client.createTags(ctr);
        getLog().info("Tagged instance " + instanceId + " with " + tags.size() + " tags");
    }

    protected int validate(List<Instance> instances) throws MojoExecutionException {
        int size = instances.size();
        String msg = instanceId + " matched " + size + " instances";
        if (size == 1) {
            return size;
        }
        if (size > 1) {
            throw new MojoExecutionException(msg);
        }
        // size == 0
        if (failIfNotFound) {
            throw new MojoExecutionException(msg);
        } else {
            getLog().info(msg);
        }
        return size;
    }

    protected DescribeInstancesRequest getRequest() {
        DescribeInstancesRequest request = new DescribeInstancesRequest();
        request.setInstanceIds(Collections.singletonList(instanceId));
        return request;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public boolean isFailIfNotFound() {
        return failIfNotFound;
    }

    public void setFailIfNotFound(boolean failIfNotFound) {
        this.failIfNotFound = failIfNotFound;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

}
