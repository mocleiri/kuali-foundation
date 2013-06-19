package org.kuali.common.impex.spring;

import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class BatchConfig {

	@Autowired
	Environment env;

	@Bean
	public int impexBatchSize() {
		return (int) SpringUtils.getBytes(env, "impex.batch.size", "50k");
	}

	@Bean
	public int impexBatchRows() {
		return SpringUtils.getInteger(env, "impex.batch.rows", 50);
	}
}
