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
public class PropertiesESCConfig implements EncryptionServiceContextConfig {

	private static final String PASSWORD_KEY = "properties.enc.password";
	private static final String STRENGTH_KEY = "properties.enc.strength";
	private static final String ENABLED_KEY = "properties.decrypt";

	@Autowired
	EnvironmentService env;

	@Override
	@Bean
	public EncryptionServiceContext encryptionServiceContext() {
		return EncConfigUtils.getEncryptionServiceContext(env, PASSWORD_KEY, ENABLED_KEY, STRENGTH_KEY);
	}

	@Override
	@Bean
	public String encryptionPasswordKey() {
		return PASSWORD_KEY;
	}

}
