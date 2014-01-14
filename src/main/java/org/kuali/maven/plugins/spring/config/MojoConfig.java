package org.kuali.maven.plugins.spring.config;

import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.impl.ExecutablesExecutable;
import org.kuali.common.util.log.log4j.Log4JExecutable;
import org.kuali.common.util.log.log4j.Log4JService;
import org.kuali.common.util.log.log4j.model.Log4JConfiguration;
import org.kuali.common.util.log.log4j.spring.Log4JConfig;
import org.kuali.maven.plugins.spring.AbstractSpringMojo;
import org.kuali.maven.plugins.spring.MojoExecutable;
import org.kuali.maven.plugins.spring.SpringMojoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SpringMojoServiceConfig.class, Log4JConfig.class })
public class MojoConfig {

	// The mojo gets wired in by registering it as a bean in the parent context
	// The execute() method on AbstractSpringMojo sets that up
	@Autowired
	AbstractSpringMojo mojo;

	@Autowired
	SpringMojoService springMojoService;

	@Autowired
	Log4JConfig log4JConfig;

	@Autowired
	Log4JService log4JService;

	@Bean
	public Executable mojoExecutable() {
		return new ExecutablesExecutable(log4JExecutable(), mojoServiceExecutable());
	}

	@Bean
	public Executable log4JExecutable() {
		return new Log4JExecutable(log4JService, getLog4JConfiguration());
	}

	@Bean
	public Executable mojoServiceExecutable() {
		return new MojoExecutable(mojo, springMojoService);
	}

	protected Log4JConfiguration getLog4JConfiguration() {
		if (springMojoService.isDebugLoggingEnabled(mojo)) {
			return log4JConfig.log4JContextDebug();
		} else {
			return log4JConfig.log4JContextMaven();
		}
	}
}
