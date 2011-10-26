package org.kuali.maven.plugins.dnsme.mojo;

import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.kuali.maven.plugins.dnsme.DNSMEClient;
import org.kuali.maven.plugins.dnsme.beans.Domain;

/**
 * Show all domains belonging to this DNSME account
 *
 * @author Jeff Caddel
 * @goal showdomains
 */
public class ShowDomainsMojo extends BaseDNSMEMojo {

    @Override
    public void performTasks(DNSMEClient client) throws MojoExecutionException, MojoFailureException {
        List<Domain> domains = client.getDomains();
        for (Domain domain : domains) {
            getLog().info("Domain: " + domain.getName());
        }
    }

}
