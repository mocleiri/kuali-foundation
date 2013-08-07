package org.kuali.common.util.spring.main;

import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.HelloWorldExecutable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainRunnerConfig {

	@Autowired
	@Qualifier(MainUtils.MAIN_CONTEXT_BEAN_NAME)
	MainContext main;

	@Bean
	public Executable mainExecutable() {
		System.out.println(main);
		return new HelloWorldExecutable();
	}

}
