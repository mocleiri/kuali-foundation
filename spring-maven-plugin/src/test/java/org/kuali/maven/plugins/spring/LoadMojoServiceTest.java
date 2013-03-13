package org.kuali.maven.plugins.spring;

import java.util.Properties;

import org.apache.maven.project.MavenProject;

public class LoadMojoServiceTest {

	public void test() {
		Properties properties = new Properties();
		properties.setProperty("foo", "bar");

		MavenProject project = new TestableMavenProject(properties);

		LoadMojo mojo = new LoadMojo();
	}

}
