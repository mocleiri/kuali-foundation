package org.kuali.maven.plugins.spring.config;

import java.io.File;
import java.util.Properties;

import org.apache.maven.model.Build;
import org.apache.maven.model.CiManagement;
import org.apache.maven.model.IssueManagement;
import org.apache.maven.project.MavenProject;
import org.kuali.common.util.LocationUtils;
import org.kuali.maven.plugins.spring.TestableMavenProject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BaseMojoTestConfig {

	@Bean
	public CiManagement ci() {
		CiManagement ci = new CiManagement();
		ci.setUrl("http://ci.rice.kuali.org");
		ci.setSystem("jenkins");
		return ci;
	}

	@Bean
	public File basedir() {
		return new File(".");
	}

	@Bean
	public Build build() {
		Build build = new Build();
		build.setSourceDirectory(LocationUtils.getCanonicalPath(new File(basedir(), "src/main")));
		build.setDirectory(LocationUtils.getCanonicalPath(new File(basedir(), "target")));
		build.setOutputDirectory(LocationUtils.getCanonicalPath(new File(basedir(), "target/classes")));
		build.setTestOutputDirectory(LocationUtils.getCanonicalPath(new File(basedir(), "target/test-classes")));
		build.setScriptSourceDirectory(LocationUtils.getCanonicalPath(new File(basedir(), "src/scripts")));
		build.setTestSourceDirectory(LocationUtils.getCanonicalPath(new File(basedir(), "src/test")));
		return build;
	}

	@Bean
	public IssueManagement im() {
		IssueManagement im = new IssueManagement();
		im.setSystem("jira");
		im.setUrl("http://jira.kuali.org");
		return im;
	}

	@Bean
	public Properties properties() {
		Properties properties = new Properties();
		properties.setProperty("spring.message", "Hello");
		return properties;
	}

	@Bean
	public MavenProject mavenProject() {
		TestableMavenProject project = new TestableMavenProject();
		project.setGroupId("org.kuali.maven.plugins");
		project.setArtifactId("spring-maven-plugin");
		project.setProperties(properties());
		project.setPackaging("jar");
		project.setDescription("description");
		project.setInceptionYear("2013");
		project.setCiManagement(ci());
		project.setIssueManagement(im());
		project.setBasedir(basedir());
		project.setBuild(build());
		return project;
	}

}
