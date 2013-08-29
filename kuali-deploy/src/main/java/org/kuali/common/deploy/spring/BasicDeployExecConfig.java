package org.kuali.common.deploy.spring;

import org.kuali.common.deploy.ApplicationServer;
import org.kuali.common.deploy.DefaultDeployService;
import org.kuali.common.deploy.DeployContext;
import org.kuali.common.deploy.DeployExecutable;
import org.kuali.common.deploy.DeployService;
import org.kuali.common.deploy.Monitoring;
import org.kuali.common.jdbc.sql.spring.JdbcResetExecutableConfig;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.secure.SecureChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ BaseDeployConfig.class })
public class BasicDeployExecConfig implements DeployExecConfig {

	@Autowired
	JdbcResetExecutableConfig config;

	@Autowired
	BaseDeployConfig baseConfig;

	@Override
	@Bean(initMethod = "execute")
	public DeployExecutable deployExecutable() {
		SecureChannel channel = baseConfig.kdoSecureChannel();
		Monitoring monitoring = baseConfig.getMonitoring();
		ApplicationServer appServer = baseConfig.getApplicationServer();
		Executable databaseResetExec = config.jdbcResetExecutable();
		DeployContext context = baseConfig.getDeployContext();
		Executable sysAdmin = baseConfig.getSysAdminExecutable();
		DeployService service = new DefaultDeployService(context, channel, sysAdmin, monitoring, appServer, databaseResetExec);
		return new DeployExecutable(service);
	}

}