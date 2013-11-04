package org.kuali.common.devops.dnsme;

import org.kuali.common.dns.dnsme.URLS;
import org.kuali.common.dns.dnsme.model.DNSMadeEasyCredentials;
import org.kuali.common.dns.dnsme.model.DNSMadeEasyServiceContext;
import org.kuali.common.dns.dnsme.spring.DNSMEServiceContextConfig;
import org.kuali.common.dns.dnsme.spring.DNSMadeEasyUtils;
import org.kuali.common.dns.spring.DomainNameConfig;
import org.kuali.common.util.enc.EncryptionService;
import org.kuali.common.util.enc.spring.DefaultEncryptionServiceConfig;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SpringServiceConfig.class, DefaultEncryptionServiceConfig.class })
public class ProductionDNSMEContextConfig implements DNSMEServiceContextConfig {

	@Autowired
	EnvironmentService env;

	@Autowired
	EncryptionService enc;

	@Autowired
	DomainNameConfig config;

	@Override
	@Bean
	public DNSMadeEasyServiceContext dnsMadeEasyServiceContext() {
		DNSMadeEasyCredentials credentials = Credentials.PRODUCTION.getCredentials();
		String restApiURL = URLS.PRODUCTION;
		DNSMadeEasyServiceContext provided = new DNSMadeEasyServiceContext(credentials, restApiURL, config.domainName());
		return DNSMadeEasyUtils.getServiceContext(env, enc, provided);
	}

}
