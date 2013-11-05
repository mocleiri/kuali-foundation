package org.kuali.common.deploy.aws.spring;

import org.kuali.common.deploy.aws.model.AwsContext;
import org.kuali.common.deploy.aws.model.EC2Context;
import org.kuali.common.deploy.aws.model.S3Context;
import org.kuali.common.deploy.aws.model.SesContext;
import org.kuali.common.deploy.aws.model.SesCredentials;
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
		EC2Context ec2 = getEC2Context();
		S3Context s3 = getS3Context();
		SesContext ses = getSesContext();
		return new AwsContext(ec2, s3, ses);
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

	protected SesContext getSesContext() {
		String username = env.getString("ses.username");
		String password = env.getString("ses.password");
		SesCredentials credentials = new SesCredentials(username, password);
		String host = env.getString("ses.host");

		// These next four need to come from the environment (even though the pojo has default values for them)
		// This is because the environment properties used to setup the pojo are also used to filter config files
		boolean debug = env.getBoolean("ses.debug");
		boolean sslEnable = env.getBoolean("ses.ssl.enable");
		boolean auth = env.getBoolean("ses.auth");
		int port = env.getInteger("ses.port");

		// Build a context object from the configuration
		return new SesContext.Builder(host, credentials).debug(debug).sslEnable(sslEnable).auth(auth).port(port).build();
	}

}