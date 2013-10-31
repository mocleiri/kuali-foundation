package org.kuali.common.util.enc.spring;

import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.util.enc.DefaultEncryptionService;
import org.kuali.common.util.enc.EncStrength;
import org.kuali.common.util.enc.EncUtils;
import org.kuali.common.util.enc.EncryptionContext;
import org.kuali.common.util.enc.EncryptionService;
import org.kuali.common.util.enc.NoOpEncryptionService;
import org.kuali.common.util.property.processor.DecryptingProcessor;
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
	private static final String PASSWORD_REQUIRED_KEY = "enc.password.required";

	// Old key's
	private static final String LEGACY_PASSWORD_KEY = DecryptingProcessor.DEFAULT_PASSWORD_KEY;
	private static final String LEGACY_STRENGTH_KEY = DecryptingProcessor.DEFAULT_STRENGTH_KEY;
	private static final String LEGACY_PASSWORD_REQUIRED_KEY = DecryptingProcessor.DEFAULT_DECRYPT_KEY;

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
		Optional<String> legacyPassword = SpringUtils.getString(env, LEGACY_PASSWORD_KEY, EncryptionContext.DEFAULT.getPassword());

		// Always use the new property if it exists, but support using the old property as well
		if (!env.containsProperty(PASSWORD_KEY) && env.containsProperty(LEGACY_PASSWORD_KEY)) {
			password = legacyPassword;
		}

		boolean passwordRequired = isPasswordRequired();

		EncStrength strength = getStrength(EncryptionContext.DEFAULT.getStrength());
		return new EncryptionContext(passwordRequired, password, strength);
	}

	protected boolean isPasswordRequired() {
		boolean required = env.getBoolean(PASSWORD_REQUIRED_KEY, false);
		boolean legacyRequired = env.getBoolean(LEGACY_PASSWORD_REQUIRED_KEY, false);
		return required || legacyRequired;
	}

	protected EncStrength getStrength(EncStrength provided) {
		String strength = env.getString(STRENGTH_KEY, provided.name());
		String legacyStrength = env.getString(LEGACY_STRENGTH_KEY, provided.name());

		// Always use the new property if it exists, but support using the old property as well
		if (!env.containsProperty(STRENGTH_KEY) && env.containsProperty(LEGACY_STRENGTH_KEY)) {
			strength = legacyStrength;
		}

		// Convert the string value to an enum
		return EncStrength.valueOf(strength.toUpperCase());
	}

}
