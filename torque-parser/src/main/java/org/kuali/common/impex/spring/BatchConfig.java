package org.kuali.common.impex.spring;

import org.kuali.common.util.FormatUtils;
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
		String size = SpringUtils.getProperty(env, "impex.batch.size");
		return new Long(FormatUtils.getBytes(size)).intValue();
	}

	@Bean
	public int impexBatchRows() {
		String rows = SpringUtils.getProperty(env, "impex.batch.rows");
		return new Integer(rows);
	}
}
