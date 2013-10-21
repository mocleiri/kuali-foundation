package org.kuali.maven.plugins.spring.config;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.project.MavenProject;
import org.kuali.maven.plugins.spring.LoadXmlMojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(BaseMojoTestConfig.class)
public class XmlLoadMojoTestConfig {

	@Autowired
	BaseMojoTestConfig baseConfig;

	@Bean(initMethod = "execute")
	public AbstractMojo mojo() {
		MavenProject project = baseConfig.mavenProject();

		LoadXmlMojo mojo = new LoadXmlMojo();
		mojo.setProject(project);
		return mojo;
	}
}
