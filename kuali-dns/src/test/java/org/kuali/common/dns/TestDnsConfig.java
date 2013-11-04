/**
 * Copyright 2010-2013 The Kuali Foundation
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
package org.kuali.common.dns;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.dns.api.DnsService;
import org.kuali.common.dns.dnsme.model.DnsMadeEasyDnsRecord;
import org.kuali.common.dns.dnsme.spring.DNSMadeEasyConfig;
import org.kuali.common.dns.dnsme.spring.DefaultDNSMadeEasyContextConfig;
import org.kuali.common.dns.model.DnsRecord;
import org.kuali.common.dns.model.DnsRecordSearchCriteria;
import org.kuali.common.dns.spring.DnsServiceConfig;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.log.LoggerUtils;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.google.common.collect.ImmutableList;

@Configuration
@Import({ SpringServiceConfig.class, DefaultDNSMadeEasyContextConfig.class, DNSMadeEasyConfig.class })
public class TestDnsConfig {

	private final Logger logger = LoggerFactory.getLogger(TestDnsConfig.class);

	@Autowired
	DnsServiceConfig config;

	@Bean
	public Executable main() {
		try {
			DnsRecordSearchCriteria criteria = new DnsRecordSearchCriteria();
			DnsService service = config.dnsService();
			List<DnsRecord> records = service.getRecords(criteria);
			List<String> columns = ImmutableList.of("Name", "Type", "Value");
			List<Object[]> rows = new ArrayList<Object[]>();
			for (DnsRecord record : records) {
				Object[] row = { record.getName(), record.getType(), record.getValue() };
				rows.add(row);
			}
			LoggerUtils.logTable(columns, rows);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Bean
	public Executable main2() {
		try {
			DnsService service = config.dnsService();
			String aliasFQDN = "delete-me-now.devops.kuali.org";
			String fqdn = "www.yahoo.com";
			int ttl = 60;
			boolean exists = service.isExistingCNAMERecord(aliasFQDN);
			if (exists) {
				logger.info("deleting existing record");
				service.deleteCNAMERecord(aliasFQDN);
			} else {
				logger.info("no existing record");
			}
			logger.info("adding a record");
			DnsRecord added = service.createCNAMERecord(aliasFQDN, fqdn, ttl);
			String log = getLog(added);
			logger.info(log);
			logger.info("removing record we just created");
			service.deleteCNAMERecord(aliasFQDN);
			logger.info("record removed");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	protected String getLog(DnsRecord record) {
		StringBuilder sb = new StringBuilder();
		sb.append(" Name:" + record.getName());
		sb.append(" Type:" + record.getType());
		sb.append(" Value:" + record.getValue());
		return sb.toString();
	}

	protected String getLog(DnsMadeEasyDnsRecord record) {
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
