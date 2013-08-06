package org.kuali.common.util.metainf.spring;

import java.util.List;

import org.kuali.common.util.metainf.model.MetaInfContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MpxContextsConfig implements MetaInfContextsConfig {

	@Override
	@Bean
	public List<MetaInfContext> metaInfContexts() {
		return null;
	}

}
