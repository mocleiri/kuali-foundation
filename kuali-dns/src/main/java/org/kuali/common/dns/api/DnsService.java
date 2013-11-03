package org.kuali.common.dns.api;

import java.util.List;

import org.kuali.common.dns.model.DnsRecord;
import org.kuali.common.dns.model.Search;

public interface DnsService {

	DnsRecord createCNAMERecord(String alias, String canonical, int timeToLiveInSeconds);

	boolean exists(String fqdn);

	void delete(String fqdn);

	DnsRecord getRecord(String fqdn);

	List<DnsRecord> getRecords();

	List<DnsRecord> getRecords(Search search);

}
