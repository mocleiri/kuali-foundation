package org.kuali.common.util.spring.main;

import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.spring.ExecutableConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ MainConfig.class })
public class MainExecutableConfig extends ExecutableConfig {

	@Autowired
	MainConfig mainConfig;

	@Override
	protected Executable getExecutable() {
		return mainConfig.mainExecutable();
	}

}
