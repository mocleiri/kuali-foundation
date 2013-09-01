package org.kuali.common.deploy.spring;

import java.util.Arrays;

import org.kuali.common.deploy.DeployContext;
import org.kuali.common.util.secure.DefaultSecureChannel;
import org.kuali.common.util.secure.SecureChannel;
import org.kuali.common.util.secure.channel.spring.SecureChannelConfig;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SpringServiceConfig.class, DefaultDeployContextConfig.class })
public class DefaultSecureChannelConfig implements SecureChannelConfig {

	@Autowired
	DeployContext ctx;

	@Autowired
	EnvironmentService env;

	@Override
	@Bean
	public SecureChannel secureChannel() {
		DefaultSecureChannel dsc = new DefaultSecureChannel();
		dsc.setUsername(ctx.getUsername());
		dsc.setHostname(ctx.getHostname());
		dsc.setStrictHostKeyChecking(env.getBoolean("kdo.channel.strictHostKeyChecking", false));
		dsc.setUseConfigFile(env.getBoolean("kdo.channel.useConfigFile", false));
		dsc.setIncludeDefaultPrivateKeyLocations(env.getBoolean("kdo.channel.includeDefaultPrivateKeyLocations", false));
		dsc.setPrivateKeyStrings(Arrays.asList(env.getString("kdo.channel.privateKey")));
		return dsc;
	}

}