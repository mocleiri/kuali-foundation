package org.kuali.common.jdbc.spring;

import org.kuali.common.jdbc.listener.SqlListener;
import org.kuali.common.jdbc.listener.SummaryListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResetListener {

	@Bean
	public SqlListener jdbcSummaryListener() {
		return new SummaryListener();
	}
}
