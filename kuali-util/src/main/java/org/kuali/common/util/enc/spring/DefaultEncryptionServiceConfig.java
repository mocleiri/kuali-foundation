package org.kuali.common.util.enc.spring;

import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.util.enc.DefaultEncryptionService;
import org.kuali.common.util.enc.EncStrength;
import org.kuali.common.util.enc.EncUtils;
import org.kuali.common.util.enc.EncryptionContext;
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

	@Autowired
	EnvironmentService env;

	@Override
	@Bean
	public EncryptionService encryptionService() {
		EncryptionContext context = encryptionContext();
		if (!context.isEnabled()) {
			return NoOpEncryptionService.INSTANCE;
		}
		String password = context.getPassword().get();
		EncStrength strength = context.getStrength();
		TextEncryptor encryptor = EncUtils.getTextEncryptor(password, strength);
		return new DefaultEncryptionService(encryptor);
	}

	@Bean
	public EncryptionContext encryptionContext() {
		return EncUtils.getEncryptionContext(env);
	}
}
