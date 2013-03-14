package org.kuali.maven.plugins.spring;

public interface SpringMojoService {

	void loadSpring(AbstractSpringMojo mojo);

	void executeCallback(LoadMojo mojo);

	void executeCallback(LoadXmlMojo mojo);

}
