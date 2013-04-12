package org.kuali.common.deploy.spring;

import org.kuali.common.jdbc.spring.ResetController;
import org.kuali.common.util.execute.Executable;
import org.springframework.context.annotation.Bean;

public class DatabaseResetController extends ResetController {

	@Override
	@Bean
	public Executable jdbcResetExecutable() {
		return super.jdbcResetExecutable();
	}

}
