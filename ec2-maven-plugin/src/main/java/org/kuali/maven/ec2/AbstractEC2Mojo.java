package org.kuali.maven.ec2;

import org.apache.maven.plugin.AbstractMojo;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;

public abstract class AbstractEC2Mojo extends AbstractMojo {

    /**
     * @parameter expression="${ec2.accessKey}"
     * @required
     */
    private String accessKey;

    /**
     * @parameter expression="${ec2.secretKey}"
     * @required
     */
    private String secretKey;

    protected AWSCredentials getCredentials() {
        return new BasicAWSCredentials(accessKey, secretKey);
    }

    protected AmazonEC2 getEC2Client() {
        AWSCredentials credentials = getCredentials();
        return new AmazonEC2Client(credentials);
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
