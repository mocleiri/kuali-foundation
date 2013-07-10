package org.kuali.common.deploy.spring;

import org.kuali.common.jdbc.spring.AbstractSqlController;
import org.kuali.common.util.execute.Executable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Deprecated
public class DeploySqlControllerConfig extends AbstractSqlController {

	@Bean(initMethod = "execute")
	public Executable sqlExecutable() {
		return getSqlExecutable();
	}
}
