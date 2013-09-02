package org.kuali.common.deploy.dns.spring;

import org.kuali.common.deploy.dns.model.DnsContext;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SpringServiceConfig.class })
public class DefaultDnsContextConfig implements DnsContextConfig {
	
	public static final String KUALI_DOMAIN = "kuali.org";

	private final String DOMAIN_KEY = "dns.domain";
	private final String PREFIX_KEY = "dns.prefix";
	private final String SUBDOMAIN_KEY = "dns.subdomain";
	private final String HOSTNAME_KEY = "dns.hostname";

	@Autowired
	EnvironmentService env;

	@Override
	public DnsContext dnsContext() {
		String domain = env.getString(DOMAIN_KEY, KUALI_DOMAIN);
		// dns.domain=kuali.org
		// dns.prefix=${deploy.env.name}
		// dns.subdomain=${project.groupId.code}
		// dns.hostname=${dns.prefix}.${dns.subdomain}.${dns.domain}

		return null;
	}

}
