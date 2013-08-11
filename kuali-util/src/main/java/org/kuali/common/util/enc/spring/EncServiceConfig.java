package org.kuali.common.util.enc.spring;

import org.kuali.common.util.enc.EncryptionService;
import org.kuali.common.util.sys.SystemService;
import org.kuali.common.util.sys.spring.SystemServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

@Configuration
@Import({ SystemServiceConfig.class })
public class EncServiceConfig {

	@Autowired
	Environment env;

	@Autowired
	SystemService service;

	@Bean
	public EncryptionService encryptionService() {
		return null;
	}

}
