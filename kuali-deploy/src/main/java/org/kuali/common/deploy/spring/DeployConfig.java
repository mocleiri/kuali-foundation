package org.kuali.common.deploy.spring;

import org.kuali.common.deploy.DefaultDeployService;
import org.kuali.common.deploy.DeployContext;
import org.kuali.common.deploy.DeployService;
import org.kuali.common.deploy.appserver.ApplicationServer;
import org.kuali.common.deploy.appserver.spring.TomcatConfig;
import org.kuali.common.deploy.monitoring.Monitoring;
import org.kuali.common.deploy.monitoring.spring.AppDynamicsConfig;
import org.kuali.common.util.execute.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @deprecated
 */
@Configuration
@Import({ DeploySqlControllerConfig.class, DefaultSysAdminConfig.class, AppDynamicsConfig.class, TomcatConfig.class, DefaultDeployContextConfig.class })
@Deprecated
public class DeployConfig {

	@Autowired
	DeploySqlControllerConfig sqlControllerConfig;

	@Autowired
	SysAdminConfig sysAdminConfig;

	@Autowired
	Monitoring monitoring;

	@Autowired
	ApplicationServer appServer;

	@Autowired
	DeployContext context;

	@Bean(initMethod = "deploy")
	public DeployService kdoDeployService() {
		Executable dbReset = sqlControllerConfig.sqlExecutable();
		Executable sysAdmin = sysAdminConfig.sysAdminExecutable();
		return new DefaultDeployService(context, sysAdmin, monitoring, appServer, dbReset);
	}

}