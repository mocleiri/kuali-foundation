package org.kuali.maven.plugins.dnsme.mojo;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.kuali.maven.plugins.dnsme.DNSMEClient;
import org.kuali.maven.plugins.dnsme.beans.Domain;
import org.kuali.maven.plugins.dnsme.beans.Record;

/**
 * @author Jeff Caddel
 * @goal deleterecord
 */
public class DeleteRecordMojo extends BaseDNSMEMojo {
    /**
     * The domain to delete a record from
     *
     * @parameter expression="${dnsme.domainName}"
     * @required
     */
    String domainName;

    /**
     * The name of the record to delete
     *
     * @parameter expression="${dnsme.recordName}"
     */
    String recordName;

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
        getLog().info("Deleting a record from '" + domainName + "'");
        getLog().info("[" + getLog(record) + "]");
        client.deleteRecord(domain, record.getId());
    }

    protected Record getRecord(DNSMEClient client, Domain domain) throws MojoExecutionException {
        if (recordId != null) {
            client.getRecord(domain, recordId);
        }
        if (recordName != null) {
            return client.getRecord(domain, recordName);
        }
        throw new MojoExecutionException("Must supply a value for either recordName or recordId");
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
