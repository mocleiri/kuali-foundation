package org.kuali.common.util.enc.spring;

import org.kuali.common.util.enc.EncStrength;
import org.kuali.common.util.enc.EncryptionServiceContext;
import org.kuali.common.util.spring.env.EnvContext;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SpringServiceConfig.class })
public class SystemEncContextConfig implements EncryptionServiceContextConfig {

	@Autowired
	EnvironmentService env;

	@Override
	@Bean
	public EncryptionServiceContext encryptionServiceContext() {
		boolean enabled = env.getBoolean("system.enc.enabled", EncryptionServiceContext.DEFAULT_ENABLED);
		if (enabled) {
			String password = env.getString("system.enc.password");
			EncStrength strength = getStrength("system.enc.strength");
			return new EncryptionServiceContext(password, strength, enabled);
		} else {
			return EncryptionServiceContext.DISABLED;
		}
	}

	protected EncStrength getStrength(String key) {
		EncStrength defaultStrength = EncryptionServiceContext.DEFAULT_STRENGTH;
		EnvContext<EncStrength> ctx = EnvContext.<EncStrength> newCtx(key, EncStrength.class, defaultStrength);
		return env.getProperty(ctx);
	}

}
