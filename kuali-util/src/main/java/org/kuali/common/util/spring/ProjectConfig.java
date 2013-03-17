package org.kuali.common.util.spring;

import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.HelloWorldExecutable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

@Configuration
public class ProjectConfig {

	@Autowired
	ConfigurableEnvironment env;

	@Bean(initMethod = "execute")
	public Executable helloWorld() {
		return new HelloWorldExecutable();
	}

}
