package org.kuali.common.deploy.aws.spring;

import org.kuali.common.deploy.aws.model.AwsContext;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SpringServiceConfig.class })
public class DefaultAwsContextConfig implements AwsContextConfig {

	@Autowired
	EnvironmentService env;

	@Override
	@Bean
	public AwsContext awsContext() {
		return null;
	}

}