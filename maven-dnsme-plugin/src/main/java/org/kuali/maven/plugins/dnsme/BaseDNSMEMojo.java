package org.kuali.maven.plugins.dnsme;

import org.apache.maven.plugin.AbstractMojo;

/**
 *
 */
public abstract class BaseDNSMEMojo extends AbstractMojo {
    /**
     * The url for the DNSME Rest API
     *
     * @parameter expression="${dnsme.restApiUrl}" default-value="http://api.dnsmadeeasy.com/V1.2"
     * @required
     */
    String restApiUrl;

    /**
     * @parameter expression="${dnsme.apiKey}"
     * @required
     */
    String apiKey;

    /**
     * @parameter expression="${dnsme.secretKey}"
     * @required
     */
    String secretKey;

    public String getRestApiUrl() {
        return restApiUrl;
    }

    public void setRestApiUrl(String restApiUrl) {
        this.restApiUrl = restApiUrl;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
