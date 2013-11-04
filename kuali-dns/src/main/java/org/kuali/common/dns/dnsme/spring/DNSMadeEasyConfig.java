package org.kuali.common.dns.dnsme.spring;

import org.kuali.common.dns.api.DnsService;
import org.kuali.common.dns.dnsme.DNSMadeEasyDnsService;
import org.kuali.common.dns.dnsme.model.DNSMadeEasyServiceContext;
import org.kuali.common.dns.spring.DnsServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DNSMadeEasyConfig implements DnsServiceConfig {

	@Autowired
	DNSMadeEasyContextConfig config;

	@Override
	@Bean
	public DnsService dnsService() {
		DNSMadeEasyServiceContext context = config.dnsMadeEasyServiceContext();
		return new DNSMadeEasyDnsService(context);
	}

}
