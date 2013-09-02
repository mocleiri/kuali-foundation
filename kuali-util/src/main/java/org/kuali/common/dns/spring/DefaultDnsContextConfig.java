package org.kuali.common.dns.spring;

import org.kuali.common.deploy.model.DeployEnvironment;
import org.kuali.common.deploy.spring.DefaultDeployEnvironmentConfig;
import org.kuali.common.dns.model.DnsContext;
import org.kuali.common.util.Assert;
import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.project.spring.AutowiredProjectConfig;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SpringServiceConfig.class, AutowiredProjectConfig.class, DefaultDeployEnvironmentConfig.class })
public class DefaultDnsContextConfig implements DnsContextConfig {

	public static final String KUALI_DOMAIN = "kuali.org";

	private final String DOMAIN_KEY = "dns.domain";
	private final String PREFIX_KEY = "dns.prefix";
	private final String SUBDOMAIN_KEY = "dns.subdomain";
	private final String HOSTNAME_KEY = "dns.hostname";

	private final String GROUP_ID_CODE_KEY = "project.groupId.code";

	@Autowired
	EnvironmentService env;

	@Autowired
	Project project;

	@Autowired
	DeployEnvironment deployEnvironment;

	@Override
	public DnsContext dnsContext() {
		String prefix = env.getString(PREFIX_KEY, deployEnvironment.getName());
		String defaultSubdomain = project.getProperties().getProperty(GROUP_ID_CODE_KEY);
		Assert.noBlanks(defaultSubdomain);
		String subdomain = env.getString(SUBDOMAIN_KEY, defaultSubdomain);
		String domain = env.getString(DOMAIN_KEY, KUALI_DOMAIN);
		String defaultHostname = prefix + "." + subdomain + "." + domain;
		String hostname = env.getString(HOSTNAME_KEY, defaultHostname);
		return new DnsContext.Builder(prefix, subdomain, domain).hostname(hostname).build();
	}

}
