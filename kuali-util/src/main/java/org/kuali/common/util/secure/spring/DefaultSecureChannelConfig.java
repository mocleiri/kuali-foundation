package org.kuali.common.util.secure.spring;

import java.util.Arrays;

import org.kuali.common.util.secure.DefaultSecureChannel;
import org.kuali.common.util.secure.SecureChannel;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SpringServiceConfig.class })
public class DefaultSecureChannelConfig implements SecureChannelConfig {
	
	private static final String USERNAME_KEY = "channel.username";
	private static final String HOSTNAME_KEY = "channel.hostname";

	@Autowired
	EnvironmentService env;

	@Override
	@Bean
	public SecureChannel secureChannel() {
		DefaultSecureChannel dsc = new DefaultSecureChannel();
		dsc.setUsername(env.getString(USERNAME_KEY));
		dsc.setHostname(env.getString(HOSTNAME_KEY));
		dsc.setStrictHostKeyChecking(env.getBoolean("channel.strictHostKeyChecking", false));
		dsc.setUseConfigFile(env.getBoolean("channel.useConfigFile", false));
		dsc.setIncludeDefaultPrivateKeyLocations(env.getBoolean("channel.includeDefaultPrivateKeyLocations", false));
		dsc.setPrivateKeyStrings(Arrays.asList(env.getString("channel.privateKey")));
		return dsc;
	}

}