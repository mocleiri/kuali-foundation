package org.kuali.common.util.spring;

import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.HelloWorldExecutable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloWorldConfig {

	@Bean(initMethod = "execute")
	public Executable helloWorld() {
		return new HelloWorldExecutable();
	}

}
