package org.kuali.maven.ec2;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

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

    protected boolean isSkip() {
        return false;
    }

    public abstract void execute(EC2Utils ec2Utils) throws MojoExecutionException;

    @Override
    public void execute() throws MojoExecutionException {
        if (isSkip()) {
            getLog().info("Skipping execution");
            return;
        }
        EC2Utils ec2Utils = EC2Utils.getInstance(accessKey, secretKey);
        execute(ec2Utils);
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
