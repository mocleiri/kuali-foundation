package org.kuali.maven.plugins.spring.config;

import org.kuali.common.util.execute.Executable;
import org.kuali.maven.plugins.spring.XmlLoadMojoExecutable;
import org.kuali.maven.plugins.spring.SpringMojoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XmlLoadMojoConfig {

	@Bean
	public SpringMojoService service() {
		return new SpringMojoService();
	}

	@Bean(initMethod = "execute")
	public Executable executable() {
		XmlLoadMojoExecutable executable = new XmlLoadMojoExecutable();
		executable.setService(service());
		return executable;
	}

}
