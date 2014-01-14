package org.kuali.common.deploy.channel.spring;

import java.util.List;

import org.kuali.common.deploy.env.spring.DefaultDeployEnvironmentConfig;
import org.kuali.common.deploy.env.spring.DeployEnvironmentConfig;
import org.kuali.common.util.secure.channel.DefaultSecureChannel;
import org.kuali.common.util.secure.channel.SecureChannel;
import org.kuali.common.util.secure.channel.spring.SecureChannelConfig;
import org.kuali.common.util.spring.SpringUtils;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.Assert;

@Configuration
@Import({ DefaultDeployEnvironmentConfig.class, SpringServiceConfig.class })
public class DefaultSecureChannelConfig implements SecureChannelConfig {

	private static final String USERNAME_KEY = "channel.username";
	private static final String HOSTNAME_KEY = "channel.hostname";

	private static final String ROOT = "root";

	@Autowired
	DeployEnvironmentConfig envConfig;

	@Autowired
	EnvironmentService env;

	@Override
	@Bean
	public SecureChannel secureChannel() {

		// User to connect as
		String username = env.getString(USERNAME_KEY, ROOT);

		// Hostname to connect to
		String hostname = env.getString(HOSTNAME_KEY, envConfig.deployEnvironment().getDns().getHostname());

		// Turn off strict host key checking by default
		boolean strictHostKeyChecking = env.getBoolean("channel.strictHostKeyChecking", false);

		// Turn off use of ~/.ssh/config
		boolean useConfigFile = env.getBoolean("channel.useConfigFile", false);

		// Don't check file system locations for private keys
		boolean includeDefaultPrivateKeyLocations = env.getBoolean("channel.includeDefaultPrivateKeyLocations", false);

		// Require at least one private key string
		List<String> privateKeyStrings = SpringUtils.getNoneSensitiveListFromCSV(env, "channel.privateKeyStrings");

		Assert.isTrue(privateKeyStrings.size() > 0, "no private key strings");

		// Setup the channel
		return new DefaultSecureChannel.Builder(username, hostname).strictHostKeyChecking(strictHostKeyChecking).useConfigFile(useConfigFile)
				.includeDefaultPrivateKeyLocations(includeDefaultPrivateKeyLocations).privateKeyStrings(privateKeyStrings).build();

	}

}