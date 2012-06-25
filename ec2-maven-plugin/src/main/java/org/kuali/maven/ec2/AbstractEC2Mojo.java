package org.kuali.maven.ec2;

import org.apache.maven.plugin.AbstractMojo;

public abstract class AbstractEC2Mojo extends AbstractMojo {
    EC2Utils ec2Utils = new EC2Utils();

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
