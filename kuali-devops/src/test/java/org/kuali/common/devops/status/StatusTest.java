package org.kuali.common.devops.status;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.kuali.common.devops.util.Auth;
import org.kuali.common.dns.api.DnsService;
import org.kuali.common.dns.dnsme.DNSMadeEasyDnsService;
import org.kuali.common.dns.dnsme.URLS;
import org.kuali.common.dns.dnsme.model.DNSMadeEasyServiceContext;
import org.kuali.common.dns.model.DnsRecord;
import org.kuali.common.dns.model.DnsRecordSearchCriteria;
import org.kuali.common.dns.model.DnsRecordType;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

public class StatusTest {

	private static final Logger logger = LoggerUtils.make();
	private static final String DOMAIN = "kuali.org";

	@Test
	public void test() {
		try {
			Map<String, String> dns = getDnsMap();
			for (String key : dns.keySet()) {
				logger.info(String.format("[%s]=[%s]", key, dns.get(key)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected Map<String, String> getDnsMap() {
		DNSMadeEasyServiceContext context = new DNSMadeEasyServiceContext(Auth.getDnsmeCredentials(), URLS.PRODUCTION, DOMAIN);
		DnsService dns = new DNSMadeEasyDnsService(context);
		DnsRecordSearchCriteria criteria = new DnsRecordSearchCriteria(DnsRecordType.CNAME);
		List<DnsRecord> records = dns.getRecords(criteria);
		logger.info(String.format("Located %s dns records for [%s]", records.size(), DOMAIN));
		Map<String, String> map = Maps.newHashMap();
		for (DnsRecord record : records) {
			String key = record.getValue();
			if (key.endsWith(".")) {
				key = key.substring(0, key.length() - 1);
			}
			String value = record.getName() + "." + DOMAIN;
			map.put(key, value);
		}
		return ImmutableMap.copyOf(map);
	}

}
