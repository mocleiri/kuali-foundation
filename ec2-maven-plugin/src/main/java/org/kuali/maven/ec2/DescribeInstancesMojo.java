package org.kuali.maven.ec2;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.maven.plugin.MojoExecutionException;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.Tag;

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
            getLog().info(getInfo(i));
        }
    }

    protected String getHeader() {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.rightPad("Name", 15, " "));
        sb.append(StringUtils.rightPad("Instance ID", 15, " "));
        return sb.toString();
    }

    protected String getInfo(Instance i) {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.rightPad(getName(i), 15, " "));
        sb.append(StringUtils.rightPad(i.getInstanceId(), 15, " "));
        return sb.toString();
    }

    protected String getName(Instance i) {
        List<Tag> tags = i.getTags();
        for (Tag t : tags) {
            if (t.getKey().equalsIgnoreCase("name")) {
                return t.getValue();
            }
        }
        return "";
    }

}
