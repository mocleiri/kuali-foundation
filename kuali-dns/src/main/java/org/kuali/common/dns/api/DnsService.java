package org.kuali.common.dns.api;

import org.kuali.common.dns.dnsme.model.Domain;
import org.kuali.common.dns.dnsme.model.Record;

public interface DnsService {

	Record addRecord(Domain domain, Record record);

	Domain getDomain(String domainName);

}
