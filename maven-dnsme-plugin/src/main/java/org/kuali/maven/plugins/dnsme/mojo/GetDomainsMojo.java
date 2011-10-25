package org.kuali.maven.plugins.dnsme.mojo;

import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.kuali.maven.plugins.dnsme.DNSMEClient;
import org.kuali.maven.plugins.dnsme.beans.Domain;

/**
 * @author Jeff Caddel
 * @goal getdomains
 */
public class GetDomainsMojo extends BaseDNSMEMojo {

    @Override
    public void performTasks(DNSMEClient client) throws MojoExecutionException, MojoFailureException {
        List<Domain> domains = client.getDomains();
        for (Domain domain : domains) {
            getLog().info("Domain: " + domain.getName());
        }
    }

}
