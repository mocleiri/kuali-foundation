package org.kuali.maven.plugins.spring.config;

import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.log4j.Log4JExecutable;
import org.kuali.common.util.log4j.spring.Log4JCommonConfig;
import org.kuali.common.util.log4j.spring.Log4JServiceConfig;
import org.kuali.maven.plugins.spring.AbstractSpringMojo;
import org.kuali.maven.plugins.spring.MavenConstants;
import org.kuali.maven.plugins.spring.MojoExecutable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SpringMojoServiceConfig.class, Log4JServiceConfig.class, Log4JCommonConfig.class })
public class MojoConfig {

	// The mojo gets wired in by registering it as a bean in the parent context
	// See the execute() method on AbstractSpringMojo for how that is done
	@Autowired
	@Qualifier(MavenConstants.DEFAULT_MAVEN_MOJO_BEAN_NAME)
	AbstractSpringMojo mojo;

	@Autowired
	SpringMojoServiceConfig springMojoServiceConfig;

	@Autowired
	Log4JServiceConfig log4JServiceConfig;

	@Autowired
	Log4JCommonConfig log4JCommonConfig;

	@Bean
	public Executable mojoExecutable() {
		MojoExecutable executable = new MojoExecutable();
		executable.setService(springMojoServiceConfig.springMojoService());
		executable.setMojo(mojo);
		return executable;
	}

	@Bean
	public Executable log4JExecutable() {
		Log4JExecutable exec = new Log4JExecutable();
		exec.setService(log4JServiceConfig.log4jService());
		exec.setContext(log4JCommonConfig.log4JContextMaven());
		return exec;
	}
}
