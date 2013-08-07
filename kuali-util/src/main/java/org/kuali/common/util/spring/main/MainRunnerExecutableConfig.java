package org.kuali.common.util.spring.main;

import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.spring.ExecutableConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ MainRunnerConfig.class })
public class MainRunnerExecutableConfig extends ExecutableConfig {

	@Autowired
	MainRunnerConfig mainRunnerConfig;

	@Override
	protected Executable getExecutable() {
		return mainRunnerConfig.mainExecutable();
	}

}
