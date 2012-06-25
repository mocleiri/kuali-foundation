package org.kuali.maven.ec2;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.Snapshot;

public abstract class AbstractEC2Mojo extends AbstractMojo {

    /**
     * The AWS Access Key Id for an account on EC2
     *
     * @parameter expression="${ec2.accessKey}"
     * @required
     */
    String accessKey;

    /**
     * The AWS Secret Access Key for an account on EC2
     *
     * @parameter expression="${ec2.secretKey}"
     * @required
     */
    String secretKey;

    @Override
    public void execute() throws MojoExecutionException {
        if (isSkip()) {
            getLog().info("Skipping execution");
            return;
        }
        AmazonEC2Client client = ec2Utils.getEC2Client(accessKey, secretKey);
        if (Constants.NONE.equals(volumeId)) {
            return;
        }
        WaitControl waitControl = ec2Utils.getWaitControl(wait, waitTimeout, state);
        Snapshot snapshot = ec2Utils.createSnapshot(client, volumeId, description, waitControl);
        ec2Utils.tag(client, snapshot.getSnapshotId(), tags);
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

}
