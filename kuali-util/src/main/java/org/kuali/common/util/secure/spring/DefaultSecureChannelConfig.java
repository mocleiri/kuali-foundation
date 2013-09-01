package org.kuali.common.util.secure.spring;

import org.kuali.common.util.secure.DefaultSecureChannel;
import org.kuali.common.util.secure.SecureChannel;
import org.kuali.common.util.spring.SpringUtils;
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

		// Setup username/hostname
		dsc.setUsername(env.getString(USERNAME_KEY));
		dsc.setHostname(env.getString(HOSTNAME_KEY));

		// Turn off strict host key checking
		dsc.setStrictHostKeyChecking(env.getBoolean("channel.strictHostKeyChecking", false));

		// Turn off use of ~/.ssh/config
		dsc.setUseConfigFile(env.getBoolean("channel.useConfigFile", false));

		// Do not check file system locations for private keys
		dsc.setIncludeDefaultPrivateKeyLocations(env.getBoolean("channel.includeDefaultPrivateKeyLocations", false));

		// They must supply at least one string representing a private key
		dsc.setPrivateKeyStrings(SpringUtils.getNoneSensitiveListFromCSV(env, "channel.privateKeyStrings"));

		// Return the configured channel
		return dsc;
	}

}