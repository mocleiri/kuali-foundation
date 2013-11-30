package org.kuali.common.util.enc.spring;

import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.util.enc.DefaultEncryptionService;
import org.kuali.common.util.enc.EncContext;
import org.kuali.common.util.enc.EncryptionService;
import org.kuali.common.util.enc.NoOpEncryptionService;
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

	@Autowired
	EnvironmentService env;

	@Override
	@Bean
	public EncryptionService encryptionService() {
		EncContext context = new EncContext.Builder(env).build();
		Optional<TextEncryptor> optional = context.getTextEncryptor();
		if (optional.isPresent()) {
			return NoOpEncryptionService.INSTANCE;
		} else {
			return new DefaultEncryptionService(optional.get());
		}
	}
}
