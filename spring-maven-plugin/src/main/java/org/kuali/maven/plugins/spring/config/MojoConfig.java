package org.kuali.maven.plugins.spring.config;

import org.kuali.common.util.execute.Executable;
import org.kuali.maven.plugins.spring.AbstractSpringMojo;
import org.kuali.maven.plugins.spring.MojoExecutable;
import org.kuali.maven.plugins.spring.SpringMojoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MojoConfig {

	@Autowired
	AbstractSpringMojo mojo;

	@Autowired
	SpringMojoService service;

	@Bean
	public Executable mojoExecutable() {
		MojoExecutable executable = new MojoExecutable();
		executable.setService(service);
		executable.setMojo(mojo);
		return executable;
	}
}
