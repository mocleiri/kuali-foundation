package org.kuali.common.deploy.spring;

import org.kuali.common.deploy.DefaultDeployService;
import org.kuali.common.deploy.DeployContext;
import org.kuali.common.deploy.DeployExecutable;
import org.kuali.common.deploy.DeployService;
import org.kuali.common.deploy.appserver.ApplicationServer;
import org.kuali.common.deploy.appserver.spring.TomcatConfig;
import org.kuali.common.deploy.monitoring.Monitoring;
import org.kuali.common.deploy.monitoring.spring.AppDynamicsConfig;
import org.kuali.common.jdbc.reset.JdbcResetConfig;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SpringServiceConfig.class, AppDynamicsConfig.class, TomcatConfig.class, DefaultDeployContextConfig.class, DefaultSysAdminConfig.class })
public class DefaultDeployConfig implements DeploymentConfig {

	private static final String SKIP_KEY = "deploy.skip";

	@Autowired
	JdbcResetConfig jdbcResetConfig;

	@Autowired
	EnvironmentService env;

	@Autowired
	SysAdminConfig sysAdminConfig;

	@Autowired
	Monitoring monitoring;

	@Autowired
	ApplicationServer appServer;

	@Autowired
	DeployContext context;

	@Override
	@Bean
	public DeployExecutable deployExecutable() {
		boolean skip = env.getBoolean(SKIP_KEY, false);
		Executable databaseResetExec = jdbcResetConfig.jdbcResetExecutable();
		Executable sysAdmin = sysAdminConfig.sysAdminExecutable();
		DeployService service = new DefaultDeployService(context, sysAdmin, monitoring, appServer, databaseResetExec);
		return new DeployExecutable(service, skip);
	}

}