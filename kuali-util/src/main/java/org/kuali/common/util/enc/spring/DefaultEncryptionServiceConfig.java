package org.kuali.common.util.enc.spring;

import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.util.enc.DefaultEncryptionService;
import org.kuali.common.util.enc.EncStrength;
import org.kuali.common.util.enc.EncUtils;
import org.kuali.common.util.enc.EncryptionContext;
import org.kuali.common.util.enc.EncryptionService;
import org.kuali.common.util.enc.NoOpEncryptionService;
import org.kuali.common.util.spring.SpringUtils;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.google.common.base.Optional;

@Configuration
@Import({ SpringServiceConfig.class })
public class DefaultEncryptionServiceConfig implements EncryptionServiceConfig {

	private static final String PASSWORD_KEY = "enc.password";
	private static final String STRENGTH_KEY = "enc.strength";

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
		Optional<String> password = SpringUtils.getString(env, PASSWORD_KEY, EncryptionContext.DEFAULT.getPassword());
		EncStrength strength = getStrength(EncryptionContext.DEFAULT.getStrength());
		return new EncryptionContext(password, strength);
	}

	protected EncStrength getStrength(EncStrength provided) {
		String strength = env.getString(STRENGTH_KEY, provided.name());
		return EncStrength.valueOf(strength.toUpperCase());
	}

}
