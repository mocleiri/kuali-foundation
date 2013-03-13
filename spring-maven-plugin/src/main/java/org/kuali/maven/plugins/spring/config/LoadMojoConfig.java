package org.kuali.maven.plugins.spring.config;

import org.kuali.common.util.execute.Executable;
import org.kuali.maven.plugins.spring.LoadMojo;
import org.kuali.maven.plugins.spring.LoadMojoExecutable;
import org.kuali.maven.plugins.spring.MavenConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(BaseConfig.class)
public class LoadMojoConfig {

	@Autowired
	BaseConfig baseConfig;

	@Autowired
	@Qualifier(MavenConstants.AUTOWIRED_MOJO_QUALIFIER)
	LoadMojo mojo;

	@Bean(initMethod = "execute")
	public Executable executable() {
		LoadMojoExecutable executable = new LoadMojoExecutable();
		executable.setService(baseConfig.service());
		executable.setMojo(mojo);
		return executable;
	}

}
