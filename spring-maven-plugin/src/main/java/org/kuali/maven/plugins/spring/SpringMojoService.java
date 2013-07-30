package org.kuali.maven.plugins.spring;

public interface SpringMojoService {

	void callback(LoadMojo mojo);

	void callback(LoadXmlMojo mojo);

	boolean isDebugLoggingEnabled(AbstractSpringMojo mojo);

}
