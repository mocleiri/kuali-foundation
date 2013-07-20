package org.kuali.common.util.spring.profile;

import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.spring.ExecutableConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ShowDatabaseConfig.class)
public class ShowDatabaseExecutableConfig extends ExecutableConfig {

	@Autowired
	ShowDatabaseConfig showDatabaseConfig;

	@Override
	protected Executable getExecutable() {
		return showDatabaseConfig.showDatabaseConfigExecutable();
	}

}
