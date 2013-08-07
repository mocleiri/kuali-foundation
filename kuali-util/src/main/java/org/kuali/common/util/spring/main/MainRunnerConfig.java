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
	@Qualifier(MainUtils.ARGS_BEAN_NAME)
	String[] args;

	@Autowired
	@Qualifier(MainUtils.MAIN_CLASS_BEAN_NAME)
	Class<?> mainClass;

	@Bean
	public Executable mainExecutable() {
		Object ma = args;
		if (ma instanceof String[]) {
			System.out.println("yo");
		}
		System.out.println(ma);
		return new HelloWorldExecutable();
	}

}
