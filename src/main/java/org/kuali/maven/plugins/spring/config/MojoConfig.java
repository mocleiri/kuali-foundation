package org.kuali.maven.plugins.spring.config;

import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.impl.ExecutablesExecutable;
import org.kuali.maven.plugins.spring.AbstractSpringMojo;
import org.kuali.maven.plugins.spring.MojoExecutable;
import org.kuali.maven.plugins.spring.SpringMojoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SpringMojoServiceConfig.class })
public class MojoConfig {

	// The mojo gets wired in by registering it as a bean in the parent context
	// The execute() method on AbstractSpringMojo sets that up
	@Autowired
	AbstractSpringMojo mojo;

	@Autowired
	SpringMojoService springMojoService;

	@Bean
	public Executable mojoExecutable() {
		return new ExecutablesExecutable(mojoServiceExecutable());
	}

	@Bean
	public Executable mojoServiceExecutable() {
		return new MojoExecutable(mojo, springMojoService);
	}

}
