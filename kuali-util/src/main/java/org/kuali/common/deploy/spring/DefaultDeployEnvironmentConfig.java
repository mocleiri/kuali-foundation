package org.kuali.common.deploy.spring;

import org.kuali.common.deploy.model.DeployEnvironment;
import org.kuali.common.dns.model.DnsContext;
import org.kuali.common.dns.spring.DefaultDnsContextConfig;
import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.project.spring.AutowiredProjectConfig;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SpringServiceConfig.class, AutowiredProjectConfig.class, DefaultDnsContextConfig.class })
public class DefaultDeployEnvironmentConfig implements DeployEnvironmentConfig {

	private final String ID_KEY = "deploy.env";
	private final String NAME_KEY = "deploy.env.name";
	private final String PUBLIC_URL = "public.url";

	private final String DEFAULT_ENV_PREFIX = "env";

	@Autowired
	EnvironmentService env;

	@Autowired
	Project project;

	@Autowired
	DnsContext context;

	@Override
	public DeployEnvironment deployEnvironment() {
		String id = env.getString(ID_KEY); // No default value, they must supply "deploy.env"
		String defaultName = DEFAULT_ENV_PREFIX + id;
		String name = env.getString(NAME_KEY, defaultName);
		String defaultPublicUrl = "http://" + context.getHostname();
		String publicUrl = env.getString(PUBLIC_URL, defaultPublicUrl);
		return new DeployEnvironment(project, id, name, publicUrl);
	}
}
