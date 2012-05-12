package org.kuali.maven.ec2;

import java.util.ArrayList;
import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;

/**
 *
 * @goal describeinstances
 */
public class DescribeInstancesMojo extends AbstractEC2Mojo {

    protected List<Instance> getInstances(List<Reservation> reservations) {
        List<Instance> instances = new ArrayList<Instance>();
        for (Reservation r : reservations) {
            instances.addAll(r.getInstances());
        }
        return instances;
    }

    @Override
    public void execute() throws MojoExecutionException {
        AmazonEC2 client = getEC2Client();
        DescribeInstancesResult result = client.describeInstances();
        List<Instance> instances = getInstances(result.getReservations());
        for (Instance i : instances) {
            getLog().info(i.getInstanceId());
        }
    }

}
