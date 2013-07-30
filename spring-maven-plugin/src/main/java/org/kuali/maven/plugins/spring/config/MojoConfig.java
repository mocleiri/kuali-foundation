package org.kuali.maven.plugins.spring.config;

import java.util.Arrays;

import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.ExecutablesExecutable;
import org.kuali.common.util.log4j.Log4JExecutable;
import org.kuali.common.util.log4j.model.Log4JContext;
import org.kuali.common.util.log4j.spring.Log4JConfig;
import org.kuali.maven.plugins.spring.AbstractSpringMojo;
import org.kuali.maven.plugins.spring.MavenConstants;
import org.kuali.maven.plugins.spring.MojoExecutable;
import org.kuali.maven.plugins.spring.SpringMojoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SpringMojoServiceConfig.class, Log4JConfig.class })
public class MojoConfig {

	// The mojo gets wired in by registering it as a bean in the parent context
	// See the execute() method on AbstractSpringMojo for how that is done
	@Autowired
	@Qualifier(MavenConstants.DEFAULT_MAVEN_MOJO_BEAN_NAME)
	AbstractSpringMojo mojo;

	@Autowired
	SpringMojoServiceConfig springMojoServiceConfig;

	@Autowired
	Log4JConfig log4JConfig;

	@Bean
	public Executable mojoExecutable() {
		return new ExecutablesExecutable(Arrays.asList(log4JExecutable(), mojoServiceExecutable()));
	}

	@Bean
	public Executable log4JExecutable() {
		Log4JExecutable exec = new Log4JExecutable();
		exec.setService(log4JConfig.log4jService());
		exec.setContext(getLog4JContext());
		return exec;
	}

	@Bean
	public Executable mojoServiceExecutable() {
		MojoExecutable executable = new MojoExecutable();
		executable.setService(springMojoServiceConfig.springMojoService());
		executable.setMojo(mojo);
		return executable;
	}

	protected Log4JContext getLog4JContext() {
		SpringMojoService service = springMojoServiceConfig.springMojoService();
		if (service.isDebugLoggingEnabled(mojo)) {
			return log4JConfig.log4JContextDebug();
		} else {
			return log4JConfig.log4JContextMaven();
		}
	}
}
