package org.kuali.maven.plugins.spring.config;

import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.spring.ExecutableConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ MojoConfig.class })
public class MojoExecutableConfig extends ExecutableConfig {

	@Autowired
	MojoConfig mojoConfig;

	@Override
	protected Executable getExecutable() {
		return mojoConfig.mojoExecutable();
	}
}
