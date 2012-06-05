package org.kuali.maven.ec2;

import java.util.Collections;
import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.StringUtils;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Filter;
import com.amazonaws.services.ec2.model.Instance;

/**
 * Connect to EC2 and find a single instance
 *
 * @goal findinstance
 */
public class FindInstanceMojo extends AbstractEC2Mojo {

    /**
     * The Maven project object
     *
     * @parameter expression="${project}"
     * @readonly
     */
    private MavenProject project;

    /**
     * The name of the tag to search for
     *
     * @parameter expression="${ec2.tag}" default-value="Name"
     */
    private String tag;

    /**
     * The value that tag needs to have
     *
     * @parameter expression="${ec2.value}"
     */
    private String value;

    /**
     * If true, fail the build if no matching instance is found
     *
     * @parameter expression="${ec2.failIfNotFound}" default-value="true"
     */
    private boolean failIfNotFound;

    /**
     * If set, the id of the instance located by the plugin is stored as this project property
     *
     * @parameter expression="${ec2.instanceIdProperty}" default-value="ec2.instance.id"
     */
    private String instanceIdProperty;

    @Override
    public void execute() throws MojoExecutionException {
        AmazonEC2 client = getEC2Client();
        DescribeInstancesRequest request = getRequest();
        DescribeInstancesResult result = client.describeInstances(request);
        List<Instance> instances = getInstances(result.getReservations());
        int size = instances.size();
        if (size > 1) {
            throw new MojoExecutionException(tag + "=" + value + " matched " + size + " instances");
        }
        if (size == 0 && failIfNotFound) {
            throw new MojoExecutionException(tag + "=" + value + " matched no instances");
        } else if (size == 0) {
            getLog().info("No instance matching " + tag + "=" + value + " was located");
            return;
        }

        // If we get here, there is exactly one matching instance
        Instance i = instances.get(0);
        String id = i.getInstanceId();
        if (!StringUtils.isBlank(instanceIdProperty)) {
            project.getProperties().setProperty(instanceIdProperty, id);
        }
        getLog().info("EC2 Instance: " + id);
    }

    protected DescribeInstancesRequest getRequest() {
        DescribeInstancesRequest request = new DescribeInstancesRequest();
        Filter filter = getFilter(tag, value);
        request.setFilters(Collections.singletonList(filter));
        return request;
    }

    protected Filter getFilter(String tag, String value) {
        Filter filter = new Filter();
        filter.setName("tag:" + tag);
        filter.setValues(Collections.singletonList(value));
        return filter;
    }

}
