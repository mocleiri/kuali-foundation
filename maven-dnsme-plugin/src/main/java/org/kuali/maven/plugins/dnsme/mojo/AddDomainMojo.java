package org.kuali.maven.plugins.dnsme.mojo;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.kuali.maven.plugins.dnsme.DNSMEClient;
import org.kuali.maven.plugins.dnsme.beans.Domain;

/**
 * @author Jeff Caddel
 * @goal adddomain
 */
public class AddDomainMojo extends BaseDNSMEMojo {

    /**
     * The domain name to add
     *
     * @parameter expression="${dnsme.domainName}"
     * @required
     */
    String domainName;

    @Override
    public void performTasks(DNSMEClient client) throws MojoExecutionException, MojoFailureException {
        Domain domain = new Domain();
        domain.setName(domainName);
        Domain addedDomain = client.addDomain(domain);
        getLog().info("Added: " + addedDomain.getName());
    }

}