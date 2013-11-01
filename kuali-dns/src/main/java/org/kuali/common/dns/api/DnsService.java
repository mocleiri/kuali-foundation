package org.kuali.common.dns.api;

import org.kuali.common.dns.dnsme.beans.Domain;
import org.kuali.common.dns.dnsme.beans.Record;

public interface DnsService {

	Record addRecord(Domain domain, Record record);

}
