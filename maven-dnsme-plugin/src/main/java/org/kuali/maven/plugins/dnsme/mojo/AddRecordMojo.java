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

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.kuali.maven.plugins.dnsme.DNSMEClient;
import org.kuali.maven.plugins.dnsme.beans.Domain;
import org.kuali.maven.plugins.dnsme.beans.GTDLocation;
import org.kuali.maven.plugins.dnsme.beans.Record;
import org.kuali.maven.plugins.dnsme.beans.RecordType;

/**
 * Add a dns record to a domain name managed by a DNSME account
 *
 * @author Jeff Caddel
 * @goal addrecord
 */
public class AddRecordMojo extends BaseDNSMEMojo {
    /**
     * The domain to add a record to eg "kuali.org"
     *
     * @parameter expression="${dnsme.domainName}"
     * @required
     */
    String domainName;

    /**
     * DEFAULT, US_EAST, US_WEST, ASIA
     *
     * @parameter expression="${dnsme.gtdLocation}"
     */
    GTDLocation gtdLocation;

    /**
     * A, CNAME, MX, NS, PTR, SRV, AAAA, HTTPRED, TXT
     *
     * @parameter expression="${dnsme.recordType}"
     * @required
     */
    RecordType recordType;

    /**
     * The record name eg "myurl.mykualiproject". "domainName" is appended to this value
     *
     * @parameter expression="${dnsme.recordName}"
     * @required
     */
    String recordName;

    /**
     * The record value eg "xyz.amazonaws.com"
     *
     * @parameter expression="${dnsme.recordValue}"
     * @required
     */
    String recordValue;

    /**
     * Time to live in seconds
     *
     * @parameter expression="${dnsme.ttl}"
     * @required
     */
    int ttl;

    @Override
    public void performTasks(DNSMEClient client) throws MojoExecutionException, MojoFailureException {
        Record record = new Record();
        record.setData(recordValue);
        record.setType(recordType);
        record.setGtdLocation(gtdLocation);
        record.setName(recordName);
        record.setTtl(ttl);

        Domain domain = client.getDomain(domainName);
        Record addedRecord = client.addRecord(domain, record);
        getLog().info("Adding a record to '" + domainName + "'");
        getLog().info(getLog(addedRecord));
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
        sb.append("Id:" + record.getId());
        sb.append(" Name:" + record.getName());
        sb.append(" Value:" + record.getData());
        sb.append(" Type:" + record.getType());
        sb.append(" TTL:" + record.getTtl());
        sb.append(" GTD:" + record.getGtdLocation());
        return sb.toString();
    }

}
