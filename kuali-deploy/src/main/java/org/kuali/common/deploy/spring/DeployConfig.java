package org.kuali.common.deploy.spring;

import org.kuali.common.deploy.ApplicationServer;
import org.kuali.common.deploy.DefaultDeployService;
import org.kuali.common.deploy.DeployContext;
import org.kuali.common.deploy.DeployService;
import org.kuali.common.deploy.Monitoring;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.secure.SecureChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @deprecated
 */
@Configuration
@Import({ DeploySqlControllerConfig.class, BaseDeployConfig.class })
@Deprecated
public class DeployConfig {

	@Autowired
	ConfigurableEnvironment env;

	@Autowired
	DeploySqlControllerConfig sqlControllerConfig;

	@Autowired
	BaseDeployConfig baseConfig;

	@Bean(initMethod = "deploy")
	public DeployService kdoDeployService() {
		SecureChannel channel = baseConfig.kdoSecureChannel();
		Monitoring monitoring = baseConfig.getMonitoring();
		ApplicationServer appServer = baseConfig.getApplicationServer();
		Executable dbReset = sqlControllerConfig.sqlExecutable();
		DeployContext context = baseConfig.getDeployContext();
		Executable sysAdmin = baseConfig.getSysAdminExecutable();
		return new DefaultDeployService(context, channel, sysAdmin, monitoring, appServer, dbReset);
	}

}