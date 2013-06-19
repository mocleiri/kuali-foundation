package org.kuali.maven.plugins.spring.config;

import org.kuali.common.util.execute.Executable;
import org.kuali.maven.plugins.spring.AbstractSpringMojo;
import org.kuali.maven.plugins.spring.MavenConstants;
import org.kuali.maven.plugins.spring.MojoExecutable;
import org.kuali.maven.plugins.spring.SpringMojoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MojoConfig {

	@Autowired
	@Qualifier(MavenConstants.DEFAULT_MAVEN_MOJO_BEAN_NAME)
	AbstractSpringMojo mojo;

	@Autowired
	@Qualifier(MavenConstants.SPRING_MOJO_SERVICE_BEAN_NAME)
	SpringMojoService service;

	@Bean(initMethod = "execute")
	public Executable executable() {
		MojoExecutable executable = new MojoExecutable();
		executable.setService(service);
		executable.setMojo(mojo);
		return executable;
	}
}
