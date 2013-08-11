package org.kuali.common.util.enc.spring;

import org.kuali.common.util.enc.EncStrength;
import org.kuali.common.util.enc.EncryptionServiceContext;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SpringServiceConfig.class })
public class DefaultEncryptionServiceContextConfig implements EncryptionServiceContextConfig {

	private static final String PASSWORD_KEY = "system.enc.password";
	private static final String STRENGTH_KEY = "system.enc.strength";
	private static final String ENABLED_KEY = "system.enc.enabled";

	@Autowired
	EnvironmentService env;

	@Override
	@Bean
	public EncryptionServiceContext encryptionServiceContext() {
		boolean enabled = env.getBoolean(ENABLED_KEY, EncryptionServiceContext.DEFAULT_ENABLED);
		if (enabled) {
			String password = env.getString(PASSWORD_KEY);
			EncStrength strength = EncConfigUtils.getStrength(env, STRENGTH_KEY);
			return new EncryptionServiceContext(password, strength, enabled);
		} else {
			return EncryptionServiceContext.DISABLED;
		}
	}

}
