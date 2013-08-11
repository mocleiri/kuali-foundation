package org.kuali.common.util.enc.spring;

import org.kuali.common.util.enc.EncContext;
import org.kuali.common.util.sys.SystemService;
import org.kuali.common.util.sys.spring.SystemServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

@Configuration
@Import({ SystemServiceConfig.class })
public class SystemEncContextConfig implements EncContextConfig {

	@Autowired
	Environment env;

	@Autowired
	SystemService service;

	@Override
	@Bean
	public EncContext encryptionContext() {
		return null;
	}

}
