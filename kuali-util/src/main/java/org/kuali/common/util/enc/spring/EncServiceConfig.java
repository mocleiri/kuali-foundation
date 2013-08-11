package org.kuali.common.util.enc.spring;

import org.kuali.common.util.enc.DefaultEncryptionService;
import org.kuali.common.util.enc.EncryptionContext;
import org.kuali.common.util.enc.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class EncServiceConfig {

	@Autowired
	Environment env;

	@Autowired
	EncryptionContext context;

	@Bean
	public EncryptionService encryptionService() {
		return new DefaultEncryptionService(context);
	}

}
