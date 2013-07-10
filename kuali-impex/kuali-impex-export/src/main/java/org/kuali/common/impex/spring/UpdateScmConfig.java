package org.kuali.common.impex.spring;

import org.kuali.common.util.execute.BuildScmExecutable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

@Configuration
@Import({ ProjectPrepareScmConfig.class })
public class UpdateScmConfig {

	@Autowired
	Environment env;

	@Autowired
	ProjectPrepareScmConfig projectPrepareScmConfig;

	@Bean
	public BuildScmExecutable buildScmExecutable() {
		BuildScmExecutable exec = new BuildScmExecutable();
		exec.setExecutables(projectPrepareScmConfig.prepareScmDirExecutables());
		return exec;
	}

}