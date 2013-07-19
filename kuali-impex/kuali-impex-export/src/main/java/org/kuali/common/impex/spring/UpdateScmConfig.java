package org.kuali.common.impex.spring;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.ExecutablesExecutable;
import org.kuali.common.util.scm.BuildScmConfig;
import org.kuali.common.util.scm.ScmConfig;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

@Configuration
@Import({ ScmConfig.class, ProjectStagingConfig.class, BuildScmConfig.class })
public class UpdateScmConfig {

	private static final String SKIP_KEY = "impex.scm.skip";

	@Autowired
	Environment env;

	@Autowired
	ProjectStagingConfig projectStagingConfig;

	@Autowired
	BuildScmConfig buildScmConfig;

	@Bean
	public Executable updateScmExecutable() {
		boolean skip = SpringUtils.getBoolean(env, SKIP_KEY, false);
		List<Executable> execs = new ArrayList<Executable>();
		execs.add(projectStagingConfig.projectStagingExecutable());
		execs.add(buildScmConfig.updateScmExecutable());
		return new ExecutablesExecutable(execs, skip);
	}

}
