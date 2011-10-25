package org.kuali.maven.plugins.dnsme.mojo;

import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.kuali.maven.plugins.dnsme.DNSMEClient;
import org.kuali.maven.plugins.dnsme.beans.Domain;
import org.kuali.maven.plugins.dnsme.beans.GTDLocation;
import org.kuali.maven.plugins.dnsme.beans.Record;
import org.kuali.maven.plugins.dnsme.beans.RecordType;
import org.kuali.maven.plugins.dnsme.beans.Search;

/**
 * @author Jeff Caddel
 * @goal showrecords
 */
public class ShowRecordsMojo extends BaseDNSMEMojo {
    /**
     * The domain to show records for
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
     */
    RecordType recordType;

    /**
     * Matches a single record with this exact name
     *
     * @parameter expression="${dnsme.recordName}"
     */
    String recordName;

    /**
     * Matches any record with a name that contains this value
     *
     * @parameter expression="${dnsme.recordNameContains}"
     */
    String recordNameContains;

    /**
     * Matches any record with this exact value
     *
     * @parameter expression="${dnsme.recordValue}"
     */
    String recordValue;

    /**
     * Matches any record with a value that contains this value
     *
     * @parameter expression="${dnsme.recordValueContains}"
     */
    String recordValueContains;

    @Override
    public void performTasks(DNSMEClient client) throws MojoExecutionException, MojoFailureException {
        Search search = new Search();
        search.setGtdLocation(gtdLocation);
        search.setType(recordType);
        search.setName(recordName);
        search.setNameContains(recordNameContains);
        search.setValue(recordValue);
        search.setValueContains(recordValueContains);

        Domain domain = client.getDomain(domainName);
        List<Record> records = client.getRecords(domain, search);
        getLog().info("Showing records for: " + domainName);
        for (Record record : records) {
            getLog().info(getCompactLog(record));
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
        sb.append("Id:" + record.getId());
        sb.append(" Name:" + record.getName());
        sb.append(" Value:" + record.getData());
        sb.append(" Type:" + record.getType());
        sb.append(" TTL:" + record.getTtl());
        sb.append(" GTD:" + record.getGtdLocation());
        return sb.toString();
    }

}
