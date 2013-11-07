package org.kuali.common.util.channel.spring;

import org.kuali.common.util.channel.api.SecureChannelService;
import org.kuali.common.util.channel.impl.DefaultSecureChannelService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultSecureChannelServiceConfig implements SecureChannelConfig {

	@Override
	@Bean
	public SecureChannelService secureChannelService() {
		return new DefaultSecureChannelService();
	}
}
