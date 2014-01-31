/**
 * Copyright 2004-2014 The Kuali Foundation
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
