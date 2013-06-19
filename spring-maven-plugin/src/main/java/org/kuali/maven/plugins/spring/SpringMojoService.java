package org.kuali.maven.plugins.spring;

public interface SpringMojoService {

	void loadSpring(AbstractSpringMojo mojo);

	void mojoCallback(LoadMojo mojo);

	void mojoCallback(LoadXmlMojo mojo);

}
