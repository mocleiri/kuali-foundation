package org.kuali.maven.plugins.spring;

public interface SpringMojoService {

	void loadSpring(AbstractSpringMojo mojo);

	void executeMojo(LoadMojo mojo);

	void executeMojo(LoadXmlMojo mojo);

}
