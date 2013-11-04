package org.kuali.common.dns.dnsme.spring;

import org.kuali.common.dns.api.DnsService;
import org.kuali.common.dns.dnsme.DNSMadeEasyDnsService;
import org.kuali.common.dns.dnsme.model.DNSMadeEasyServiceContext;
import org.kuali.common.dns.spring.DnsServiceConfig;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SpringServiceConfig.class })
public class DNSMadeEasyConfig implements DnsServiceConfig {

	@Autowired
	DNSMadeEasyServiceContext context;

	@Override
	@Bean
	public DnsService dnsService() {
		return new DNSMadeEasyDnsService(context);
	}

}
