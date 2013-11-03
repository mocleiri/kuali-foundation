package org.kuali.common.dns.api;

import java.util.List;

import org.kuali.common.dns.dnsme.model.Domain;
import org.kuali.common.dns.dnsme.model.Record;
import org.kuali.common.dns.dnsme.model.Search;

public interface DnsService {

	Record addRecord(Domain domain, Record record);

	void deleteRecord(Domain domain, String name);

	void deleteRecord(Domain domain, int recordId);

	Domain getDomain(String domainName);

	Record getRecord(Domain domain, int recordId);

	Record getRecord(Domain domain, String name);

	boolean exists(Domain domain, String name);

	Record getRecord(Domain domain, Search search);

	List<Record> getRecords(Domain domain);

	List<Record> getRecords(Domain domain, Search search);

}
