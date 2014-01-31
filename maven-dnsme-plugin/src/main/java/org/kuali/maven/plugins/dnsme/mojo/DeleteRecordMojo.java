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
import org.kuali.maven.plugins.dnsme.DNSMEException;
import org.kuali.maven.plugins.dnsme.beans.Domain;
import org.kuali.maven.plugins.dnsme.beans.Record;
import org.kuali.maven.plugins.dnsme.beans.Search;

/**
 * Delete a dns record from a domain managed by a DNSME account. Must provide either a name or an id
 *
 * @author Jeff Caddel
 * @goal deleterecord
 */
public class DeleteRecordMojo extends BaseDNSMEMojo {

    /**
     * The domain to add a record to eg "kuali.org"
     *
     * @parameter expression="${dnsme.domainName}"
     * @required
     */
    String domainName;

    /**
     * The name of the record to delete eg "env6.rice"
     *
     * @parameter expression="${dnsme.recordName}"
     */
    String recordName;

    /**
     * If true, the Maven build will fail if the plugin is deleting by <code>recordName</code> and there is no match for
     * the name.
     *
     * @parameter expression="${dnsme.failIfRecordNameHasNoMatch}" default-value="false"
     */
    boolean failIfRecordNameHasNoMatch;

    /**
     * The id of the record to delete
     *
     * @parameter expression="${dnsme.recordId}"
     */
    Integer recordId;

    @Override
    public void performTasks(DNSMEClient client) throws MojoExecutionException, MojoFailureException {
        Domain domain = client.getDomain(domainName);
        Record record = getRecord(client, domain);
        if (record != null) {
            getLog().info("Deleting a record from '" + domainName + "'");
            getLog().info("[" + getLog(record) + "]");
            client.deleteRecord(domain, record.getId());
        } else {
            getLog().info("No match for '" + recordName + "." + domainName + "'");
        }
    }

    protected Record getRecord(DNSMEClient client, Domain domain) throws MojoExecutionException {
        if (recordId != null) {
            return client.getRecord(domain, recordId);
        }
        if (recordName == null) {
            throw new MojoExecutionException("Must supply a value for either recordName or recordId");
        }
        Search search = client.getSearch(recordName);
        List<Record> records = client.getRecords(domain, search);
        validateRecords(records);
        if (records.size() == 0) {
            return null;
        } else {
            return records.get(0);
        }
    }

    protected void validateRecords(List<Record> records) {
        if (records.size() > 1) {
            throw new DNSMEException("Search criteria must match exactly 1 record but it matched " + records.size()
                    + " records");
        }
        if (records.size() == 0 && failIfRecordNameHasNoMatch) {
            throw new DNSMEException("No match for " + recordName);
        }
    }

    protected String getCompactLog(Record record) {
        StringBuilder sb = new StringBuilder();
        sb.append(record.getName());
        sb.append("->" + record.getData());
        sb.append("," + record.getType());
        sb.append(",ttl=" + record.getTtl() + "s");
        return sb.toString();
    }

    protected String getLog(Record record) {
        StringBuilder sb = new StringBuilder();
        sb.append(record.getId());
        sb.append("," + record.getName());
        sb.append("->" + record.getData());
        sb.append("," + record.getType());
        sb.append(",ttl=" + record.getTtl() + "s");
        sb.append(",gtd=" + record.getGtdLocation());
        return sb.toString();
    }

}
