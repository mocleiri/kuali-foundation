package org.kuali.maven.plugins.spring.config;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.project.MavenProject;
import org.kuali.common.util.PropertyUtils;
import org.kuali.maven.plugins.spring.LoadMojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(BaseMojoTestConfig.class)
public class LoadMojoTestConfig {

	@Autowired
	BaseMojoTestConfig baseConfig;

	@Bean(initMethod = "execute")
	public AbstractMojo mojo() {
		MavenProject project = baseConfig.mavenProject();

		LoadMojo mojo = new LoadMojo();
		mojo.setProject(project);
		mojo.setProperties(PropertyUtils.EMPTY);
		return mojo;
	}
}
