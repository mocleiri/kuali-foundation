package org.kuali.maven.plugins.dnsme.mojo;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.kuali.maven.plugins.dnsme.DNSMEClient;
import org.kuali.maven.plugins.dnsme.beans.Domain;

/**
 * Delete a domain from a DNSME account
 *
 * @author Jeff Caddel
 * @goal deletedomain
 */
public class DeleteDomainMojo extends BaseDNSMEMojo {

    /**
     * The domain name
     *
     * @parameter expression="${dnsme.domainName}"
     * @required
     */
    String domainName;

    @Override
    public void performTasks(DNSMEClient client) throws MojoExecutionException, MojoFailureException {
        Domain domain = new Domain();
        domain.setName(domainName);
        client.deleteDomain(domain);
        getLog().info("Deleted: " + domainName);
    }

}