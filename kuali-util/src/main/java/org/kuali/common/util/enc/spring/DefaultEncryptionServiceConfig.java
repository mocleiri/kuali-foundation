package org.kuali.common.util.enc.spring;

import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.util.enc.DefaultEncryptionService;
import org.kuali.common.util.enc.EncStrength;
import org.kuali.common.util.enc.EncUtils;
import org.kuali.common.util.enc.EncryptionService;
import org.kuali.common.util.enc.NoOpEncryptionService;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SpringServiceConfig.class })
public class DefaultEncryptionServiceConfig implements EncryptionServiceConfig {

	private static final String ENABLED_KEY = "enc.enabled";
	private static final String PASSWORD_KEY = "enc.password";
	private static final String STRENGTH_KEY = "enc.strength";

	@Autowired
	EnvironmentService env;

	@Override
	@Bean
	public EncryptionService encryptionService() {
		boolean enabled = env.getBoolean(ENABLED_KEY, false);
		if (!enabled) {
			return NoOpEncryptionService.INSTANCE;
		}
		String password = env.getString(PASSWORD_KEY);
		EncStrength strength = getStrength();
		TextEncryptor encryptor = EncUtils.getTextEncryptor(password, strength);
		return new DefaultEncryptionService(encryptor);
	}

	protected EncStrength getStrength() {
		String strength = env.getString(STRENGTH_KEY, EncStrength.BASIC.name());
		return EncStrength.valueOf(strength.toUpperCase());
	}

}
