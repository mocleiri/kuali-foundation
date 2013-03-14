package org.kuali.maven.plugins.spring;

public interface SpringMojoService {

	void loadSpring(LoadMojo mojo);

	void loadSpring(LoadXmlMojo mojo);

	void executeMojo(LoadMojo mojo);

	void executeMojo(LoadXmlMojo mojo);

}
