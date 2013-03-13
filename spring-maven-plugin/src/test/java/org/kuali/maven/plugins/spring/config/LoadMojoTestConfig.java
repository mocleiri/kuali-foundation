package org.kuali.maven.plugins.spring.config;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.project.MavenProject;
import org.kuali.maven.plugins.spring.LoadMojo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadMojoTestConfig extends BaseMojoTestConfig {

	@Bean(initMethod = "execute")
	public AbstractMojo mojo() {
		MavenProject project = mavenProject();

		LoadMojo mojo = new LoadMojo();
		mojo.setProject(project);
		return mojo;
	}
}
