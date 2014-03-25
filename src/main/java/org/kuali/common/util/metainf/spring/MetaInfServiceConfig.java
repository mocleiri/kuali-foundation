package org.kuali.common.util.metainf.spring;

import org.kuali.common.util.metainf.service.DefaultMetaInfService;
import org.kuali.common.util.metainf.service.MetaInfService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetaInfServiceConfig {

	@Bean
	public MetaInfService metaInfService() {
		return new DefaultMetaInfService();
	}

}
