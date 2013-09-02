package org.kuali.common.deploy.aws.spring;

import org.kuali.common.deploy.aws.model.AwsContext;
import org.kuali.common.deploy.aws.model.EC2Context;
import org.kuali.common.deploy.aws.model.S3Context;
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

	protected EC2Context getEC2Context() {
		String ami = env.getString("ec2.ami");
		String securityGroup = env.getString("ec2.securityGroup");
		String type = env.getString("ec2.type");
		return new EC2Context(ami, securityGroup, type);
	}

	protected S3Context getS3Context() {
		String accessKey = env.getString("s3.accessKey");
		String secretKey = env.getString("s3.secretKey");
		return new S3Context(accessKey, secretKey);
	}

}