package org.kuali.common.util.channel.spring;

import org.kuali.common.util.channel.api.ChannelService;
import org.kuali.common.util.channel.impl.DefaultChannelService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultSecureChannelServiceConfig implements SecureChannelConfig {

	@Override
	@Bean
	public ChannelService secureChannelService() {
		return new DefaultChannelService();
	}
}
