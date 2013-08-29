package org.kuali.common.deploy.spring;

import org.kuali.common.deploy.DefaultDeployService;
import org.kuali.common.deploy.DeployService;
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
		DefaultDeployService dds = new DefaultDeployService();
		dds.setChannel(baseConfig.kdoSecureChannel());
		dds.setMonitoring(baseConfig.getMonitoring());
		dds.setAppServer(baseConfig.getApplicationServer());
		dds.setDatabaseResetExecutable(sqlControllerConfig.sqlExecutable());
		dds.setContext(baseConfig.getDeployContext());
		dds.setSysAdminExecutable(baseConfig.getSysAdminExecutable());
		return dds;
	}

}