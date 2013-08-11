package org.kuali.common.util.enc.spring;

import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.util.enc.DefaultEncryptionService;
import org.kuali.common.util.enc.EncUtils;
import org.kuali.common.util.enc.EncryptionService;
import org.kuali.common.util.enc.EncryptionServiceContext;
import org.kuali.common.util.enc.NoOpEncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EncryptionServiceConfig {

	@Autowired
	EncryptionServiceContextConfig contextConfig;

	@Bean
	public EncryptionService encryptionService() {
		EncryptionServiceContext context = contextConfig.encryptionServiceContext();
		TextEncryptor encryptor = EncUtils.getTextEncryptor(context.getPassword(), context.getStrength());
		if (context.isEnabled()) {
			return new DefaultEncryptionService(encryptor);
		} else {
			return new NoOpEncryptionService();
		}
	}

}
