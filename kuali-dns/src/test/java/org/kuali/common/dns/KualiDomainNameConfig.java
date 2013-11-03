package org.kuali.common.dns;

import org.kuali.common.dns.spring.DomainNameConfig;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SpringServiceConfig.class })
public class KualiDomainNameConfig implements DomainNameConfig {

	private static final String DOMAIN_NAME_KEY = "dns.domainName";
	private static final String DEFAULT_DOMAIN_NAME = "kuali.org";

	@Autowired
	EnvironmentService env;

	@Override
	public String domainName() {
		return env.getString(DOMAIN_NAME_KEY, DEFAULT_DOMAIN_NAME);
	}

}
