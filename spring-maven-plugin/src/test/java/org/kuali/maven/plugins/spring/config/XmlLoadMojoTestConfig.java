package org.kuali.maven.plugins.spring.config;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.project.MavenProject;
import org.kuali.maven.plugins.spring.XmlLoadMojo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XmlLoadMojoTestConfig extends BaseMojoTestConfig {

	@Bean(initMethod = "execute")
	public AbstractMojo mojo() {
		MavenProject project = mavenProject();

		XmlLoadMojo mojo = new XmlLoadMojo();
		mojo.setProject(project);
		mojo.setLocation("classpath:" + project.getArtifactId() + "-context.xml");
		return mojo;
	}
}
