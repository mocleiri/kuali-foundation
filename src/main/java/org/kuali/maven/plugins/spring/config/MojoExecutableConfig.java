package org.kuali.maven.plugins.spring.config;

import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.spring.config.annotation.Execute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ MojoConfig.class })
public class MojoExecutableConfig {

	@Autowired
	MojoConfig mojoConfig;

	@Execute
	public Executable getExecutable() {
		return mojoConfig.mojoExecutable();
	}
}
