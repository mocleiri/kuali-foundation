package org.kuali.maven.ec2;

import org.apache.maven.plugin.AbstractMojo;

public abstract class AbstractEc2Mojo extends AbstractMojo {

    /**
     * @parameter expression="${ec2.accessKeyId}"
     * @required
     */
    private String accessKeyId;

    /**
     * @parameter expression="${ec2.secretAccessKey}"
     * @required
     */
    private String secretAccessKey;

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setSecretAccessKey(String secretAccessKey) {
        this.secretAccessKey = secretAccessKey;
    }

    public String getSecretAccessKey() {
        return secretAccessKey;
    }

}
