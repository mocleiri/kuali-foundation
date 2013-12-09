package org.kuali.common.deploy.spring;

import org.kuali.common.util.execute.Executable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @deprecated
 */
@Configuration
@Deprecated
public class DeploySqlControllerConfig extends org.kuali.common.jdbc.spring.AbstractSqlController {

	@Override
	@Bean
	public Executable sqlExecutable() {
		return getSqlExecutable();
	}
}
