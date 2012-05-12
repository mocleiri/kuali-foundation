package org.kuali.maven.ec2;

import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Reservation;

/**
 *
 * @goal describeinstances
 */
public class DescribeInstancesMojo extends AbstractEC2Mojo {

    @Override
    public void execute() throws MojoExecutionException {
        AmazonEC2 client = getEC2Client();
        DescribeInstancesResult result = client.describeInstances();
        List<Reservation> reservations = result.getReservations();
        for (Reservation r : reservations) {
            getLog().info(r.getReservationId());
        }
    }

}
