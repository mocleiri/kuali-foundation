package org.kuali.maven.plugins.spring.config;

import org.kuali.common.util.execute.Executable;
import org.kuali.maven.plugins.spring.LoadMojoExecutable;
import org.kuali.maven.plugins.spring.LoadMojoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadMojoConfig {

	@Bean
	public LoadMojoService service() {
		return new LoadMojoService();
	}

	@Bean(initMethod = "execute")
	public Executable executable() {
		LoadMojoExecutable lme = new LoadMojoExecutable();
		lme.setService(service());
		return lme;
	}

}
