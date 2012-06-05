package org.kuali.maven.ec2;

import java.util.Collections;
import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;

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

    @Override
    public void execute() throws MojoExecutionException {
        AmazonEC2 client = getEC2Client();
        DescribeInstancesRequest request = getRequest();
        DescribeInstancesResult result = client.describeInstances(request);
        List<Instance> instances = getInstances(result.getReservations());
        int size = instances.size();
        if (size > 1) {
            throw new MojoExecutionException(tag + "=" + value + " matched more than one instance");
        }
        if (size == 0) {
            throw new MojoExecutionException(tag + "=" + value + " matched no instances");
        }
        Instance i = instances.get(0);
        getLog().info(i.getInstanceId());
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
