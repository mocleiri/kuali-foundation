package org.kuali.common.deploy.spring;

import org.kuali.common.deploy.ApplicationServer;
import org.kuali.common.deploy.DefaultDeployService;
import org.kuali.common.deploy.DeployContext;
import org.kuali.common.deploy.DeployExecutable;
import org.kuali.common.deploy.DeployService;
import org.kuali.common.deploy.Monitoring;
import org.kuali.common.jdbc.reset.JdbcResetConfig;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.secure.SecureChannel;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SpringServiceConfig.class, BaseDeployConfig.class })
public class DefaultDeployConfig implements DeploymentConfig {

	private static final String SKIP_KEY = "deploy.skip";

	@Autowired
	JdbcResetConfig config;

	@Autowired
	BaseDeployConfig baseConfig;

	@Autowired
	EnvironmentService env;

	@Override
	@Bean
	public DeployExecutable deployExecutable() {
		boolean skip = env.getBoolean(SKIP_KEY, false);
		SecureChannel channel = baseConfig.kdoSecureChannel();
		Monitoring monitoring = baseConfig.getMonitoring();
		ApplicationServer appServer = baseConfig.getApplicationServer();
		Executable databaseResetExec = config.jdbcResetExecutable();
		DeployContext context = baseConfig.getDeployContext();
		Executable sysAdmin = baseConfig.getSysAdminExecutable();
		DeployService service = new DefaultDeployService(context, channel, sysAdmin, monitoring, appServer, databaseResetExec);
		return new DeployExecutable(service, skip);
	}

}