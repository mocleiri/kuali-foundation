/**
 * Copyright 2004-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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