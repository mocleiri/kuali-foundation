package edu.calpoly.records;

import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.HelloWorldExecutable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PowerWebappConfig {

	@Bean(initMethod = "execute")
	public Executable helloWorldExecutable() {
		return new HelloWorldExecutable();
	}

}
