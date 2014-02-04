package org.kuali.common.jdbc.reset;

import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.spring.ExecutableConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdbcResetExecConfig implements ExecutableConfig {

	@Autowired
	JdbcResetConfig resetConfig;

	@Override
	@Bean(initMethod = "execute")
	public Executable executable() {
		return resetConfig.jdbcResetExecutable();
	}

}
