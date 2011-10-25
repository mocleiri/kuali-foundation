package org.kuali.maven.plugins.dnsme.mojo;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.kuali.maven.plugins.dnsme.DNSMEClient;
import org.kuali.maven.plugins.dnsme.beans.Account;

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

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        Account account = new Account();
        account.setApiKey(apiKey);
        account.setSecretKey(secretKey);
        DNSMEClient client = DNSMEClient.getInstance(account, restApiUrl);
        performTasks(client);
    }

    protected abstract void performTasks(DNSMEClient client) throws MojoExecutionException, MojoFailureException;

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
