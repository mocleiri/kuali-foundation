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

import org.kuali.common.dns.api.DnsService;
import org.kuali.common.dns.dnsme.model.Domain;
import org.kuali.common.dns.dnsme.model.Record;
import org.kuali.common.dns.model.RecordType;
import org.kuali.common.dns.spring.DNSMadeEasyConfig;
import org.kuali.common.dns.spring.DnsConfig;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SpringServiceConfig.class, DNSMadeEasyConfig.class })
public class TestDnsConfig {

	@Autowired
	DnsConfig config;

	@Bean
	public Executable main() {
		try {
			DnsService service = config.dnsService();
			Record record = new Record();
			record.setData("www.yahoo.com."); // Note the trailing dot. Without it, dnsme appends kuali.org
			record.setType(RecordType.CNAME);
			// record.setGtdLocation(GTDLocation.DEFAULT);
			record.setName("delete-me-now.devops");
			record.setTtl(60);
			Domain domain = service.getDomain("kuali.org");
			boolean exists = service.exists(domain, record.getName());
			if (exists) {
				System.out.println("deleting existing record");
				service.deleteRecord(domain, record.getName());
			} else {
				System.out.println("no existing record");
			}
			System.out.println("adding a record");
			Record added = service.addRecord(domain, record);
			String log = getLog(added);
			System.out.println(log);
			System.out.println("removing record we just created");
			service.deleteRecord(domain, added.getId());
			System.out.println("record removed");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
