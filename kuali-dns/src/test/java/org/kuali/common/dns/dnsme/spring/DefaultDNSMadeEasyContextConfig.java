package org.kuali.common.dns.dnsme.spring;

import org.kuali.common.dns.dnsme.URLS;
import org.kuali.common.dns.dnsme.model.DNSMadeEasyCredentials;
import org.kuali.common.dns.dnsme.model.DNSMadeEasyServiceContext;
import org.kuali.common.dns.spring.DomainNameConfig;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SpringServiceConfig.class })
public class DefaultDNSMadeEasyContextConfig implements DNSMadeEasyContextConfig, DomainNameConfig {

	private static final String DOMAIN_NAME_KEY = "dns.domain";
	private static final String DEFAULT_DOMAIN_NAME = "kuali.org";

	@Autowired
	EnvironmentService env;

	@Override
	@Bean
	public String domainName() {
		return env.getString(DOMAIN_NAME_KEY, DEFAULT_DOMAIN_NAME);
	}

	@Override
	@Bean
	public DNSMadeEasyServiceContext dnsMadeEasyServiceContext() {
		DNSMadeEasyCredentials credentials = Credentials.PRODUCTION.getCredentials();
		String restApiURL = URLS.PRODUCTION;
		return new DNSMadeEasyServiceContext(credentials, restApiURL, domainName());
	}

}
