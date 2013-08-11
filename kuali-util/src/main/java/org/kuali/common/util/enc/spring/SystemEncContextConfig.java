package org.kuali.common.util.enc.spring;

import org.kuali.common.util.enc.EncryptionServiceContext;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SpringServiceConfig.class })
public class SystemEncContextConfig implements EncContextConfig {

	@Autowired
	EnvironmentService env;

	@Override
	@Bean
	public EncryptionServiceContext encryptionContext() {
		return null;
	}

	protected String getProperty(String key) {
		return null;
	}

}
