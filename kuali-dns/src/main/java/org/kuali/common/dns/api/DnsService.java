package org.kuali.common.dns.api;

import java.util.List;

import org.kuali.common.dns.dnsme.model.Search;
import org.kuali.common.dns.model.DnsRecord;

public interface DnsService {

	boolean exists(String fqdn);

	void createCNAME(String aliasDnsName, String canonicalDnsName, int timeToLiveInSeconds);

	void delete(String fqdn);

	DnsRecord getRecord(String fqdn);

	List<DnsRecord> getRecords();

	List<DnsRecord> getRecords(Search search);

}
