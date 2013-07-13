package org.kuali.common.util.feature.spring;

import org.kuali.common.util.feature.DefaultFeatureService;
import org.kuali.common.util.feature.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class FeatureConfig {

	@Autowired
	Environment env;

	@Bean
	public FeatureService featureService() {
		return new DefaultFeatureService();
	}
}
