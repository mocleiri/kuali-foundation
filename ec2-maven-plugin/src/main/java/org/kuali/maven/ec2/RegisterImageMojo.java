package org.kuali.maven.ec2;

import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;

import com.amazonaws.services.ec2.model.BlockDeviceMapping;
import com.amazonaws.services.ec2.model.RegisterImageRequest;

/**
 * @goal registerimage
 */
public class RegisterImageMojo extends AbstractEC2Mojo {

    /**
     * @parameter
     * @required
     */
    RegisterImageRequest image;

    /**
     * @parameter
     * @required
     */
    List<BlockDeviceMapping> blockDeviceMappings;

    /**
     * If true, the build will wait until EC2 reports that the AMI has reached the state of "completed"
     *
     * @parameter expression="${ec2.wait}" default-value="true"
     */
    private boolean wait;

    /**
     * The number of seconds to wait for the AMI to complete
     *
     * @parameter expression="${ec2.waitTimeout}" default-value="300"
     */
    private int waitTimeout;

    /**
     * The state the plugin will wait for the AMI to be in before continuing
     *
     * @parameter expression="${ec2.state}" default-value="completed"
     */
    private String state;

    @Override
    public void execute(EC2Utils ec2Utils) throws MojoExecutionException {
        getLog().info(image.getName());
        getLog().info(blockDeviceMappings.get(0).getEbs().getSnapshotId());
        image.setBlockDeviceMappings(blockDeviceMappings);
        ec2Utils.registerImage(image);

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

    public RegisterImageRequest getImage() {
        return image;
    }

    public void setImage(RegisterImageRequest image) {
        this.image = image;
    }

    public List<BlockDeviceMapping> getBlockDeviceMappings() {
        return blockDeviceMappings;
    }

    public void setBlockDeviceMappings(List<BlockDeviceMapping> blockDeviceMappings) {
        this.blockDeviceMappings = blockDeviceMappings;
    }
}
